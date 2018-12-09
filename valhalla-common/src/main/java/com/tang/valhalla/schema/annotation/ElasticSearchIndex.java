package com.tang.valhalla.schema.annotation;

import com.tang.valhalla.schema.ElasticSearchFieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticSearchIndex {
    ElasticSearchFieldType type() default ElasticSearchFieldType.AUTO;
    boolean enabled() default true;
}
