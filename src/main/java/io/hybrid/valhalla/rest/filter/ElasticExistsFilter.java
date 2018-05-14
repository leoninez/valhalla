package io.hybrid.valhalla.rest.filter;

import io.hybrid.valhalla.rest.type.ElasticFilterType;

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
