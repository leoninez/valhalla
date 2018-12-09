package com.tang.valhalla.api.aggregate;

public abstract class ElasticAggregateFieldAbstract extends ElasticAggregateAbstract {
    private String field;

    public ElasticAggregateFieldAbstract(String name, String field) {
        super(name);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
