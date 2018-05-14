package io.hybrid.valhalla.high;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.hybrid.valhalla.rest.alias.ElasticAliasAction;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilder;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface IElasticRawHighExecutor {

    // indices related
    boolean createIndex(String index, Map<String, Object> settings, String mappings);
    boolean createIndex(String index, Map<String, Object> settings, String mappings, String alias);

    boolean deleteIndex(String index);
    boolean openIndex(String index);
    boolean closeIndex(String index);

    //@TODO why IndicesAliasesRequest removed
    boolean existIndex(String index);

    // alias
    boolean existAlias(String index, String alias);

    boolean addAlias(String index, String alias);
    boolean removeAlias(String index, String alias);

    String[] indexAllAlias(String index);
    String[] aliasAllIndex(String alias);

    //@TODO why IndicesAliasesRequest removed
    boolean aliasesAction(ElasticAliasAction[] aliasActions);

    // CURD
    boolean insert(String index, String id, String value);
    boolean insert(String index, String id, JsonObject value);

    boolean delete(String index, String id);

    DocWriteResponse.Result update(UpdateRequest updateRequest);
    DocWriteResponse.Result update(String index, String type, String id, String script, Map<String, Object> params);
    DocWriteResponse.Result update(String index, String type, String id, String partialJson);
    DocWriteResponse.Result update(String index, String type, String id, JsonObject partialJson);
    boolean updateByQuery(String index, JsonObject query, JsonObject script);

    JsonElement get(String index, String id);
    JsonArray multiGet(MultiGetRequest request);

    BulkResponse bulk(BulkRequest request);

    JsonElement search(String index, QueryBuilder[] filters, int size, String[] includeFields);
    JsonElement search(SearchRequest searchRequest);

    // @NOTE by aware scroll contains time limit which may lead to empty value even hasNext == true
    Iterator<JsonElement> scroll(String index, QueryBuilder[] filters, int size,
                                 String[] includeFields, long timeValue, TimeUnit timeUnit);
    Iterator<JsonElement> scroll(SearchRequest searchRequest);
    JsonElement scrollToAll(String index, QueryBuilder[] filters, int size, String[] includeFields);
    JsonElement scrollToAll(SearchRequest searchRequest);

    //@TODO you need manually handle the response since the client RestHighLevelClient does
    //      not provide any easy way to fetch out aggregation results
    JsonElement aggregate(String index, QueryBuilder[] filters, AggregationBuilder[] aggregates);
    JsonElement aggregate(SearchRequest searchRequest);

    RestClient rawRestClient();
    RestHighLevelClient rawHighRestClient();
}
