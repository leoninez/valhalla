package com.ebay.valhalla.api.aggregate;


import com.ebay.valhalla.type.ElasticAggregateType;

public class ElasticNestedAggregate extends ElasticAggregateFieldAbstract {

    public ElasticNestedAggregate(String name, String field) {
        super(name, field);
    }

    @Override
    public ElasticAggregateType type() {
        return ElasticAggregateType.nested;
    }
}
