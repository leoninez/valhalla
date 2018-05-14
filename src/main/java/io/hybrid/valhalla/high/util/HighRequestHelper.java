package io.hybrid.valhalla.high.util;


import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

public class HighRequestHelper {
    public static SearchRequest buildSearchRequest(String index, QueryBuilder[] filters,
                                                   int size, String[] includeFields) {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        for (QueryBuilder builder : filters) {
            searchSourceBuilder.query(builder);
        }

        if (size > 0) {
            searchSourceBuilder.size(size);
        }

        if (includeFields != null && includeFields.length > 0) {
            searchSourceBuilder.fetchSource(includeFields, null);
        }

        request.source(searchSourceBuilder);

        return request;
    }

    public static SearchRequest buildSearchRequest(String index, QueryBuilder[] filters,
                                                   AggregationBuilder[] aggregations) {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        for (QueryBuilder builder : filters) {
            searchSourceBuilder.query(builder);
        }

        for (AggregationBuilder builder: aggregations) {
            searchSourceBuilder.aggregation(builder);
        }

        request.source(searchSourceBuilder);

        return request;
    }
}
