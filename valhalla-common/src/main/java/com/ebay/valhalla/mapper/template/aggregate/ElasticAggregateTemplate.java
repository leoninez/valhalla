package com.ebay.valhalla.mapper.template.aggregate;

import com.ebay.valhalla.api.ElasticExecutor;
import com.ebay.valhalla.api.aggregate.ElasticAggregate;
import com.ebay.valhalla.mapper.template.ElasticTemplate;

public interface ElasticAggregateTemplate extends ElasticTemplate {
    @Override
    public ElasticAggregate apply(ElasticExecutor executor, Object[] args);
}
