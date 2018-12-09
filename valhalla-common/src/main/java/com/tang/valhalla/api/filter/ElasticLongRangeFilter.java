package com.tang.valhalla.api.filter;

import com.tang.valhalla.type.ElasticFilterType;

public class ElasticLongRangeFilter implements ElasticFilter {
    public String field;
    public long start;
    public long end;
    public CompareOP op;

    public ElasticLongRangeFilter(String field, long start, long end) {
        this.field = field;
        this.start = start;
        this.end = end;
        this.op = CompareOP.BOTH;
    }

    public ElasticLongRangeFilter(String field, long start, CompareOP op) {
        this.field = field;
        this.start = start;
        this.op = op;
    }

    @Override
    public ElasticFilterType type() {
        return ElasticFilterType.RANGE;
    }

    public static enum CompareOP {
        LARGER,
        SMALLER,
        BOTH
    }
}
