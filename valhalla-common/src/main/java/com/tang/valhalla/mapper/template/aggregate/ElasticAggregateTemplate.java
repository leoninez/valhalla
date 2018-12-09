package com.tang.valhalla.mapper.template.aggregate;

import com.tang.valhalla.api.ElasticExecutor;
import com.tang.valhalla.api.aggregate.ElasticAggregate;
import com.tang.valhalla.mapper.template.ElasticTemplate;

public interface ElasticAggregateTemplate extends ElasticTemplate {
    @Override
    public ElasticAggregate apply(ElasticExecutor executor, Object[] args);
}
