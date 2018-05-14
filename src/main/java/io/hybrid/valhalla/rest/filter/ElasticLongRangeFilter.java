package io.hybrid.valhalla.rest.filter;

import io.hybrid.valhalla.rest.type.ElasticFilterType;

public class ElasticLongRangeFilter implements ElasticFilter {
    public String field;
    public long start;
    public long end;

    public ElasticLongRangeFilter(String field, long start, long end) {
        this.field = field;
        this.start = start;
        this.end = end;
    }

    @Override
    public ElasticFilterType type() {
        return ElasticFilterType.RANGE;
    }
}
