package io.hybrid.valhalla.rest.aggregate;


public class ElasticAggregateSort {
    public String field;
    public SortOrder order;
    public ElasticAggregateSort(String field, SortOrder order) {
        this.field = field;
        this.order = order;
    }

    public enum SortOrder {
        desc,
        asc
    }
}
