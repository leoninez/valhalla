package com.ebay.valhalla.api.aggregate;


public class ElasticAggregateOrder {
    public enum OrderTarget {
        _key,
        _count
    }

    public enum OrderValue {
        desc,
        asc
    }

    // just hint to no order
    public static final ElasticAggregateOrder DUMMY_ORDER = new ElasticAggregateOrder(OrderTarget._key, OrderValue.desc);

    public OrderTarget target;
    public OrderValue value;

    public ElasticAggregateOrder(OrderTarget target, OrderValue value) {
        this.target = target;
        this.value = value;
    }
}
