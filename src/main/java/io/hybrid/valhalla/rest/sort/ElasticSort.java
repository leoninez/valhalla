package io.hybrid.valhalla.rest.sort;

import io.hybrid.valhalla.rest.type.ElasticSortMode;
import io.hybrid.valhalla.rest.type.ElasticSortOrder;
import io.hybrid.valhalla.rest.type.ElasticSortType;

public interface ElasticSort {
    ElasticSortType type();

    ElasticSortOrder order();

    ElasticSortMode mode();
}
