package com.ebay.valhalla.api.aggregate;


public class ElasticAggregateSort {
    public enum SortOrder {
        desc,
        asc
    }

    public String field;
    public SortOrder order;

    public ElasticAggregateSort(String field, SortOrder order) {
        this.field = field;
        this.order = order;
    }
}
