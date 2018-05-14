package io.hybrid.valhalla.rest.aggregate;


import io.hybrid.valhalla.rest.type.ElasticAggregateType;

public class ElasticNestedAggregate extends ElasticAggregateFieldAbstract {

    public ElasticNestedAggregate(String name, String field) {
        super(name, field);
    }

    @Override
    public ElasticAggregateType type() {
        return ElasticAggregateType.nested;
    }
}
