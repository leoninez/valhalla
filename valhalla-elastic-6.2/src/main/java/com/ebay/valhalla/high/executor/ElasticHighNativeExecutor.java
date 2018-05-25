package com.ebay.valhalla.high.executor;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.List;

public class ElasticHighNativeExecutor implements ElasitcNativeExecutor{

    @Override
    public boolean createIndex(Class clz) {
        return false;
    }

    @Override
    public boolean createIndex(Class clz, long timeout) {
        return false;
    }

    @Override
    public boolean deleteIndex(Class clz) {
        return false;
    }

    @Override
    public boolean openIndex(Class clz) {
        return false;
    }

    @Override
    public boolean closeIndex(Class clz) {
        return false;
    }

    @Override
    public boolean existedIndex(Class clz) {
        return false;
    }

    @Override
    public long countIndex(Class clz) {
        return 0;
    }

    @Override
    public boolean clearIndexType(Class clz) {
        return false;
    }

    @Override
    public boolean insertById(Object object, String id) {
        return false;
    }

    @Override
    public boolean insertById(Object object, String id, long timeout) {
        return false;
    }

    @Override
    public boolean insertByNoId(Object object) {
        return false;
    }

    @Override
    public boolean insertByNoId(Object object, long timeout) {
        return false;
    }

    @Override
    public boolean insert(Object object) {
        return false;
    }

    @Override
    public boolean insert(Object object, long timeout) {
        return false;
    }

    @Override
    public boolean delete(Class clz, String id) {
        return false;
    }

    @Override
    public <T> T get(Class<T> clz, String id) {
        return null;
    }

    @Override
    public JsonElement rawGet(Class clz, String id) {
        return null;
    }

    @Override
    public JsonElement rawGet(String index, String type, String id) {
        return null;
    }

    @Override
    public <T> boolean bulkInsert(T[] objects) {
        return false;
    }

    @Override
    public <T> boolean bulkInsert(List<T> objects) {
        return false;
    }

    @Override
    public <T> T[] search(Class<T[]> arrayClz, QueryBuilder[] filters) {
        return null;
    }

    @Override
    public <T> T[] search(Class<T[]> arrayClz, QueryBuilder[] filters, int size) {
        return null;
    }

    @Override
    public <T> T[] search(Class<T[]> arrayClz, QueryBuilder[] filters, int size, SortOrder[] sorts) {
        return null;
    }

    @Override
    public JsonArray rawSearch(Class clz, QueryBuilder[] filters, int size, String[] includeFields) {
        return null;
    }

    @Override
    public JsonArray rawSearch(String index, String type, QueryBuilder[] filters, int size, String[] includeFields) {
        return null;
    }

    @Override
    public <T> T[] scroll(Class<T[]> arrayClz, QueryBuilder[] filters, int size, SortOrder[] sorts, int keepMin) {
        return null;
    }

    @Override
    public JsonArray rawScroll(Class arrayClz, QueryBuilder[] filters, int batchSize, String[] includeFields, int keepMin) {
        return null;
    }

    @Override
    public JsonArray rawScroll(String index, String type, QueryBuilder[] filters, int batchSize, String[] includeFields, int keepMin) {
        return null;
    }

    @Override
    public <T> Object aggregate(Class<T[]> arrayClz, QueryBuilder[] filters, AggregationBuilder[] aggregates) {
        return null;
    }

    @Override
    public JsonElement rawAggregate(Class clz, QueryBuilder[] filters, AggregationBuilder[] aggregate, String[] includeFields) {
        return null;
    }

    @Override
    public JsonElement rawAggregate(String index, String type, QueryBuilder[] filters, AggregationBuilder[] aggregate, String[] includeFields) {
        return null;
    }

    @Override
    public <T> T[] nativeSearchRequest(Class<T[]> arrayClz, SearchRequest searchRequest) {
        return null;
    }

    @Override
    public JsonArray rawNativeSearchRequest(String index, String type, SearchRequest searchRequest) {
        return null;
    }

    @Override
    public JsonArray rawNativeSearchRequest(Class clz, SearchRequest searchRequest) {
        return null;
    }

    @Override
    public RestHighLevelClient nativeClient() {
        return null;
    }
}
