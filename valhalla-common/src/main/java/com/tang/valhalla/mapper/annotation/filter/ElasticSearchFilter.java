package com.tang.valhalla.mapper.annotation.filter;

import com.tang.valhalla.type.ElasticFilterType;

public @interface ElasticSearchFilter {
    ElasticFilterType type();

    String[] parameters() default {};
}
