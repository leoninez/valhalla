package com.tang.valhalla.mapper.template.filter;

import com.tang.valhalla.api.ElasticExecutor;
import com.tang.valhalla.api.filter.ElasticFilter;
import com.tang.valhalla.mapper.template.ElasticTemplate;

public interface ElasticFilterTemplate extends ElasticTemplate {
    @Override
    ElasticFilter apply(ElasticExecutor executor, Object[] args);

}
