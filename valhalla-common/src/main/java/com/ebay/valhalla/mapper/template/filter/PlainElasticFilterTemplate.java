package com.ebay.valhalla.mapper.template.filter;

import com.ebay.valhalla.api.ElasticExecutor;
import com.ebay.valhalla.api.filter.ElasticFilter;
import com.ebay.valhalla.mapper.annotation.filter.ElasticSearchFilter;
import com.ebay.valhalla.mapper.template.ElasticTemplate;

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
