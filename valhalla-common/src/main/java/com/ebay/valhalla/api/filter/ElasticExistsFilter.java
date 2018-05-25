package com.ebay.valhalla.api.filter;


import com.ebay.valhalla.type.ElasticFilterType;

public class ElasticExistsFilter implements ElasticFilter {
    public final String field;

    public ElasticExistsFilter(String field) {
        this.field = field;
    }

    @Override
    public ElasticFilterType type() {
        return ElasticFilterType.EXISTS;
    }
}
