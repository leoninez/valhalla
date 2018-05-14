package io.hybrid.valhalla.rest.sort;

import io.hybrid.valhalla.rest.type.ElasticSortMode;
import io.hybrid.valhalla.rest.type.ElasticSortOrder;
import io.hybrid.valhalla.rest.type.ElasticSortType;

public class ElasticFieldSort implements ElasticSort {

    private String field;
    private ElasticSortOrder order;
    private ElasticSortMode mode = ElasticSortMode.NON;

    public ElasticFieldSort(String field, ElasticSortOrder order) {
        this.field = field;
        this.order = order;
    }

    public ElasticFieldSort(String field, ElasticSortOrder order, ElasticSortMode mode) {
        this.field = field;
        this.order = order;
        this.mode = mode;
    }

    public String field() {
        return field;
    }

    @Override
    public ElasticSortType type() {
        return ElasticSortType.FIELD;
    }

    @Override
    public ElasticSortOrder order() {
        return this.order;
    }

    @Override
    public ElasticSortMode mode() {
        return mode;
    }
}
