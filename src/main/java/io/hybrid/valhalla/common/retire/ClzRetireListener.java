package io.hybrid.valhalla.common.retire;

import io.hybrid.valhalla.common.schema.ElasticSearchSchema;
import io.hybrid.valhalla.common.schema.annotation.ElasticSearchRollingGranularity;
import io.hybrid.valhalla.common.util.CalendarUtil;
import io.hybrid.valhalla.rest.ElasticRawRestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

public class ClzRetireListener implements RetireListener {

    private static final Logger log = LoggerFactory.getLogger(ClzRetireListener.class);
    public ElasticSearchRollingGranularity granularity = ElasticSearchRollingGranularity.DAY;
    public Class clz;
    private ElasticRawRestExecutor executor;

    // fetch granularity based on clz
    public ClzRetireListener(Class clz, ElasticRawRestExecutor executor) {
        this(ElasticSearchSchema.getOrBuild(clz).granularity, clz, executor);
    }

    // specify the target granularity
    public ClzRetireListener(ElasticSearchRollingGranularity granularity, Class clz, ElasticRawRestExecutor executor) {
        this.granularity = granularity;
        this.executor = executor;
        this.clz = clz;
    }

    @Override
    public void onChange(ElasticSearchRollingGranularity granularity, Calendar newDay) {
        if (granularity == this.granularity) {
            // fetch all index
            String[] indecies = executor.allAliasIndex(clz);
            ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

            for (String index : indecies) {
                log.info("Start to check index {} if need to be retired", index);

                if (schema.shouldRetired(index)) {
                    log.warn("Retire index {} since: today {}, min-day {}", index,
                        CalendarUtil.toDayKey(CalendarUtil.today()),
                        schema.lastDayIndexBeforeRetire());

                    executor.removeAlias(index, schema.toView());
                    executor.deleteIndex(index);
                }
            }
        }
    }

    @Override
    public void onRestore(Calendar day) {
        if (!executor.existedIndex(this.clz, day)) {
            executor.createIndex(this.clz, day);
            executor.addAlias(this.clz, day);
        }
    }
}
