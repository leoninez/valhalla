package com.ebay.valhalla.mapper.annotation.aggregation;

import com.ebay.valhalla.type.ElasticAggregateType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticSearchAggregate {
    String name();

    String key();

    ElasticAggregateType type();
}
