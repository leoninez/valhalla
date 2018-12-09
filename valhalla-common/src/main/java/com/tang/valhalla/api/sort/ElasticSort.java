package com.tang.valhalla.api.sort;

import com.tang.valhalla.type.ElasticSortMode;
import com.tang.valhalla.type.ElasticSortOrder;
import com.tang.valhalla.type.ElasticSortType;

public interface ElasticSort {
    ElasticSortType type();

    ElasticSortOrder order();

    ElasticSortMode mode();
}
