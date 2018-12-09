package com.tang.valhalla.mapper.template.mapping.aggregate;

import com.tang.valhalla.mapper.template.aggregate.ElasticAggregateTemplate;

import java.lang.reflect.Method;
import java.util.List;

public interface ElasticA2TAggregateMapping {
    List<ElasticAggregateTemplate> apply(Method method);
}
