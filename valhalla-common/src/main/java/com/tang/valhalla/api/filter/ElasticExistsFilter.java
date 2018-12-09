package com.tang.valhalla.api.filter;


import com.tang.valhalla.type.ElasticFilterType;

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
