package com.ebay.valhalla.api.sort;

import com.ebay.valhalla.type.ElasticSortMode;
import com.ebay.valhalla.type.ElasticSortOrder;
import com.ebay.valhalla.type.ElasticSortType;

public interface ElasticSort {
    ElasticSortType type();

    ElasticSortOrder order();

    ElasticSortMode mode();
}
