package com.ebay.valhalla.mapper.template.filter;

import com.ebay.valhalla.api.ElasticExecutor;
import com.ebay.valhalla.api.filter.ElasticFilter;
import com.ebay.valhalla.mapper.template.ElasticTemplate;

public interface ElasticFilterTemplate extends ElasticTemplate {
    @Override
    ElasticFilter apply(ElasticExecutor executor, Object[] args);

}
