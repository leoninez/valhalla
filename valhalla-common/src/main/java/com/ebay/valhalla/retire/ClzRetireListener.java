package com.ebay.valhalla.retire;


import com.ebay.valhalla.api.ElasticExecutor;
import com.ebay.valhalla.schema.ElasticSearchSchema;
import com.ebay.valhalla.schema.annotation.ElasticSearchRollingGranularity;
import com.ebay.valhalla.util.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

public class ClzRetireListener implements RetireListener {

    private static final Logger log = LoggerFactory.getLogger(ClzRetireListener.class);

    private ElasticExecutor executor;
    public ElasticSearchRollingGranularity granularity = ElasticSearchRollingGranularity.DAY;
    public Class clz;

    // fetch granularity based on clz
    public ClzRetireListener(Class clz, ElasticExecutor executor) {
        this(ElasticSearchSchema.getOrBuild(clz).granularity, clz, executor);
    }

    // specify the target granularity
    public ClzRetireListener(ElasticSearchRollingGranularity granularity, Class clz, ElasticExecutor executor) {
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
