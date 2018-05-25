package com.ebay.valhalla.mapper.annotation.filter;

import com.ebay.valhalla.type.ElasticFilterType;

public @interface ElasticSearchFilter {
    ElasticFilterType type();

    String[] parameters() default {};
}
