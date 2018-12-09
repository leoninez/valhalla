package com.tang.valhalla.mapper.template.mapping;

import com.tang.valhalla.mapper.annotation.ElasticSearchAggregation;
import com.tang.valhalla.mapper.template.ElasticTemplate;
import com.tang.valhalla.mapper.template.action.ElasticAggregationTemplate;
import com.tang.valhalla.mapper.template.mapping.aggregate.ElasticA2TAggregatePlainMapping;
import com.tang.valhalla.mapper.template.mapping.filter.ElasticA2TFilterPipelineMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class ElasticA2TAggregationMapping implements ElasticA2TMapping {
    private static final Logger log = LoggerFactory.getLogger(ElasticA2TAggregationMapping.class);

    @Override
    public ElasticTemplate apply(Method method) {

        ElasticSearchAggregation searchAnno = method.getAnnotation(ElasticSearchAggregation.class);

        if (searchAnno == null) {
            log.info("{} : There is no ElasticSearchAggregation annotation attached, ignore mapping", method);
            return null;
        }

        Class clz = searchAnno.clz();

        if (clz.equals(Object.class)) {
            log.error("{} : specified class type in ElasticSearchAggregation annotation should not be Object.class");
            return null;
        }

        return new ElasticAggregationTemplate(
            ElasticA2TFilterPipelineMapping.builtin().apply(method),
            ElasticA2TAggregatePlainMapping.instance().apply(method),
            clz);

    }
}
