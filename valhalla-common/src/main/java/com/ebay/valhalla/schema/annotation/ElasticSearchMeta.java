package com.ebay.valhalla.schema.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// however, if you use Mapper mode, this annotation could be overwritten or ignored

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticSearchMeta {
    String index();

    String type() default "data";

    boolean filedIndex() default true;

    String[] id() default {};

    int shards() default 13;

    int replicas() default 1;

    boolean retire() default false;

    String view() default "";

    ElasticSearchRollingGranularity granularity() default ElasticSearchRollingGranularity.DAY;

    int limit() default 0;
}
