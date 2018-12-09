package com.tang.valhalla.mapper.template.mapping.aggregate;

import com.tang.valhalla.mapper.annotation.aggregation.ElasticSearchAggregate;
import com.tang.valhalla.mapper.template.aggregate.ElasticAggregateTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ElasticA2TAggregatePlainMapping implements ElasticA2TAggregateMapping {

    private static final ElasticA2TAggregatePlainMapping instance = new ElasticA2TAggregatePlainMapping();

    public static ElasticA2TAggregatePlainMapping instance() {
        return instance;
    }

    @Override
    public List<ElasticAggregateTemplate> apply(Method method) {
        Annotation[] annos = method.getDeclaredAnnotations();

        if (annos == null || annos.length == 0) {
            return null;
        }

        List<ElasticAggregateTemplate> list = new ArrayList<>();

        for (Annotation anno : annos) {
            if (anno.annotationType().equals(ElasticSearchAggregate.class)) {
                ElasticSearchAggregate aggAnno = (ElasticSearchAggregate) anno;
                //list.add(new ElasticAggregatePlainTemplate(aggAnno));
            }
        }

        return list;
    }
}
