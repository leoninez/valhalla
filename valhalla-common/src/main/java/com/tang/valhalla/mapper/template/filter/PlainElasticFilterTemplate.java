package com.tang.valhalla.mapper.template.filter;

import com.tang.valhalla.api.ElasticExecutor;
import com.tang.valhalla.api.filter.ElasticFilter;
import com.tang.valhalla.mapper.annotation.filter.ElasticSearchFilter;
import com.tang.valhalla.mapper.template.ElasticTemplate;

public class PlainElasticFilterTemplate implements ElasticTemplate {
    ElasticSearchFilter filter;

    public PlainElasticFilterTemplate(ElasticSearchFilter filter) {
        this.filter = filter;
    }

    @Override
    public ElasticFilter apply(ElasticExecutor executor, Object[] args) {
        //return new ElasticPlainFilter(filter.type(), filter.parameters());
        return null;
    }
}
