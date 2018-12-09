package com.tang.valhalla.high.executor;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.List;

// Unable to provide one common
public interface ElasitcNativeExecutor {

    // auto generate based on clz
    boolean createIndex(Class clz);

    boolean createIndex(Class clz, long timeout);

    // remove index
    boolean deleteIndex(Class clz);

    // open index
    boolean openIndex(Class clz);

    // close index
    boolean closeIndex(Class clz);

    // test if index is existed
    boolean existedIndex(Class clz);

    // count documents in index
    long countIndex(Class clz);

    // clear index data
    boolean clearIndexType(Class clz);

    // insert with id
    boolean insertById(Object object, String id);

    boolean insertById(Object object, String id, long timeout);

    // insert with no id
    boolean insertByNoId(Object object);
    boolean insertByNoId(Object object, long timeout);

    // normal insert, id is judged by @ElasticSearchMeta
    boolean insert(Object object);
    boolean insert(Object object, long timeout);

    //delete by id
    boolean delete(Class clz, String id);

    // get one specified object with id
    <T extends Object> T get(Class<T> clz, String id);
    JsonElement rawGet(Class clz, String id);
    JsonElement rawGet(String index, String type, String id);

    // bulkInsert multi values, values may not in same type
    <T> boolean bulkInsert(T[] objects);
    <T> boolean bulkInsert(List<T> objects);

    <T extends Object> T[] search(Class<T[]> arrayClz, QueryBuilder[] filters);
    <T extends Object> T[] search(Class<T[]> arrayClz, QueryBuilder[] filters, int size);
    <T extends Object> T[] search(Class<T[]> arrayClz, QueryBuilder[] filters, int size, SortOrder[] sorts);

    JsonArray rawSearch(Class clz, QueryBuilder[] filters, int size, String[] includeFields);
    JsonArray rawSearch(String index, String type, QueryBuilder[] filters, int size, String[] includeFields);

    <T extends Object> T[] scroll(Class<T[]> arrayClz, QueryBuilder[] filters, int size, SortOrder[] sorts, int keepMin);

    JsonArray rawScroll(Class arrayClz, QueryBuilder[] filters, int batchSize, String[] includeFields, int keepMin);
    JsonArray rawScroll(String index, String type, QueryBuilder[] filters, int batchSize, String[] includeFields, int keepMin);

    <T extends Object> Object aggregate(Class<T[]> arrayClz, QueryBuilder[] filters, AggregationBuilder[] aggregates);

    JsonElement rawAggregate(Class clz, QueryBuilder[] filters, AggregationBuilder[] aggregate, String[] includeFields);
    JsonElement rawAggregate(String index, String type, QueryBuilder[] filters, AggregationBuilder[] aggregate, String[] includeFields);

    // special request
    <T extends Object> T[] nativeSearchRequest(Class<T[]> arrayClz, SearchRequest searchRequest);
    JsonArray rawNativeSearchRequest(String index, String type, SearchRequest searchRequest);
    JsonArray rawNativeSearchRequest(Class clz, SearchRequest searchRequest);

    // directly client provide
    RestHighLevelClient nativeClient();
}
