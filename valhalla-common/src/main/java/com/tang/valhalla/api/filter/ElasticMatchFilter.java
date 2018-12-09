package com.tang.valhalla.api.filter;

import com.tang.valhalla.type.ElasticFilterType;

public class ElasticMatchFilter implements ElasticFilter {
    private String field;
    private Object value;

    public ElasticMatchFilter(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public ElasticFilterType type() {
        return ElasticFilterType.MATCH;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }

    public boolean isNumber() {
        return value instanceof Number;
    }

    public boolean isBoolean() {
        return value instanceof Boolean;
    }

    public boolean isString() {
        return value instanceof String;
    }

    public Number getNumberValue() {
        return (Number) value;
    }

    public Boolean getBooleanValue() {
        return (Boolean) value;
    }

    public String getStringValue() {
        return (String) value;
    }
}