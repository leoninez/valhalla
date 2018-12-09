package com.tang.valhalla.mapper.template.filter;

import com.tang.valhalla.api.ElasticExecutor;
import com.tang.valhalla.api.filter.ElasticFilter;

public class ElasticTermFilterTemplate implements ElasticFilterTemplate {
    private String field;
    private String valueParamName;

    public ElasticTermFilterTemplate(String field, String valueParamName) {
        this.field = field;
        this.valueParamName = valueParamName;
    }

    @Override
    public ElasticFilter apply(ElasticExecutor executor, Object[] args) {
        return null;
    }
}
