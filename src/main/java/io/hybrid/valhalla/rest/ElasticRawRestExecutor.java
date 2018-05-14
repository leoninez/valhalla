package io.hybrid.valhalla.rest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.hybrid.valhalla.rest.aggregate.ElasticAggregate;
import io.hybrid.valhalla.rest.alias.ElasticAliasAction;
import io.hybrid.valhalla.rest.filter.ElasticFilter;
import io.hybrid.valhalla.rest.sort.ElasticSort;

import java.io.Closeable;
import java.util.Calendar;
import java.util.List;

//@TODO LI, should provide the String index/type directly access logic?
//          should provide the ElasticSearchSchema directly access logic?
public interface ElasticRawRestExecutor extends Closeable {

    // auto generate based on clz
    // if retired == 0 ignore date information
    // otherwise treat it as createIndex(clz, today)
    boolean createIndex(Class clz);

    // create with specified data information
    boolean createIndex(Class clz, Calendar day);

    // remove index
    boolean deleteIndex(Class clz);

    // remove with specified data information
    boolean deleteIndex(Class clz, Calendar day);

    boolean deleteIndex(String index);

    // test if index is existed
    boolean existedIndex(Class clz);

    // test if index is existed
    boolean existedIndex(Class clz, Calendar day);

    // test if index is existed
    boolean existedIndex(String index, String type);

    // count documents in index
    long countIndex(Class clz);

    // clear index data
    boolean clearIndex(Class clz);

    boolean existAlias(Class clz, Calendar day);

    // create up alias
    boolean addAlias(Class clz);

    // create up alias with specified day
    boolean addAlias(Class clz, Calendar day);

    // remove up alias
    boolean removeAlias(Class clz);

    // remove up alias with specified day
    boolean removeAlias(Class clz, Calendar day);

    boolean removeAlias(String index, String alias);

    String[] allAliasIndex(Class clz);

    boolean aliasesAction(ElasticAliasAction[] actions);

    // insert with id
    boolean insertById(Object object, String id);

    boolean insertById(Object object, String id, Calendar day);

    // insert with no id
    boolean insertByNoId(Object object);

    boolean insertByNoId(Object object, Calendar day);

    // normal insert, id is judged by @ElasticSearchMeta
    boolean insert(Object object);

    boolean insert(Object object, Calendar day);

    //delete by id
    boolean delete(Class clz, String id);

    //delete by id
    boolean delete(Class clz, Calendar day, String id);

    // get one specifed object with id
    // @NOTE, elasticsearch does not support one id for alias get
    //        when the alias maps to multi index. In such case
    //        we will append Calendar information here
    //        However, search does not contain such limitation
    <T extends Object> T get(Class<T> clz, String id);

    <T extends Object> T get(Class<T> clz, Calendar day, String id);

    JsonElement rawGet(Class clz, String id);

    JsonElement rawGet(Class clz, Calendar day, String id);

    // bulkInsert multi values, values may not in same type, use today as day key
    <T> boolean bulkInsert(T[] objects);

    <T> boolean bulkInsert(List<T> objects);

    // bulkInsert multi values, values may not in same type
    <T> boolean bulkInsert(T[] objects, Calendar[] days);

    <T> boolean bulkInsert(List<T> objects, List<Calendar> days);

    <T extends Object> T[] search(Class<T[]> arrayClz, List<ElasticFilter> filters);

    <T extends Object> T[] search(Class<T[]> arrayClz, ElasticFilter[] filters);

    <T extends Object> T[] search(Class<T[]> arrayClz, List<ElasticFilter> filters, int size);

    <T extends Object> T[] search(Class<T[]> arrayClz, ElasticFilter[] filters, int size);

    <T extends Object> T[] search(Class<T[]> arrayClz, List<ElasticFilter> filters, int size, List<ElasticSort> sorts);

    <T extends Object> T[] search(Class<T[]> arrayClz, ElasticFilter[] filters, int size, List<ElasticSort> sorts);

    JsonArray rawSearch(Class clz, ElasticFilter[] filters, int size, String[] includeFields, ElasticSort[] sorts);
    JsonArray rawSearch(String index, String type, ElasticFilter[] filters, int size, String[] includeFields, ElasticSort[] sorts);

    <T extends Object> T[] scroll(Class<T[]> arrayClz, List<ElasticFilter> filters, int size, List<ElasticSort> sorts, int keepMin);

    <T extends Object> T[] scroll(Class<T[]> arrayClz, ElasticFilter[] filters, int size, List<ElasticSort> sorts, int keepMin);

    JsonArray rawScroll(Class arrayClz, ElasticFilter[] filters, int batchSize, String[] includeFields, ElasticSort[] sorts, int keepMin);

    JsonArray rawScroll(String index, String type, ElasticFilter[] filters, int batchSize, String[] includeFields, ElasticSort[] sorts, int keepMin);

    <T extends Object> Object aggregate(Class<T[]> arrayClz,
                                        List<ElasticFilter> filters,
                                        ElasticAggregate[] aggregates);

    <T extends Object> Object aggregate(Class<T[]> arrayClz,
                                        ElasticFilter[] filters,
                                        ElasticAggregate[] aggregates);

    JsonElement rawAggregate(Class clz, ElasticFilter[] filters, ElasticAggregate[] aggregates, String[] includeFields);
    JsonElement rawAggregate(String index, String type, ElasticFilter[] filters, ElasticAggregate[] aggregates, String[] includeFields);

    // special request
    JsonObject raw(String method, String path, String param);
}
