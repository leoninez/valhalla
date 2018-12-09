package com.tang.valhalla.api.aggregate;

import com.tang.valhalla.type.ElasticAggregateType;

public class ElasticStatAggregate extends ElasticAggregateFieldAbstract {
    private ElasticAggregateType type;

    public ElasticStatAggregate(String name, String field, ElasticAggregateType type) {
        super(name, field);
        this.type = type;
    }

    @Override
    public ElasticAggregateType type() {
        return type;
    }


}
