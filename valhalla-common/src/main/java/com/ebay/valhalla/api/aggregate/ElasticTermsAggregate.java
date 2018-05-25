package com.ebay.valhalla.api.aggregate;

import com.ebay.valhalla.type.ElasticAggregateType;

public class ElasticTermsAggregate extends ElasticAggregateFieldAbstract {

    public ElasticTermsAggregate(String name, String field) {
        super(name, field);
    }

    @Override
    public ElasticAggregateType type() {
        return ElasticAggregateType.terms;
    }

    public int getSize() {
        return 10000;
    }
}
