package io.hybrid.valhalla.high;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import io.hybrid.valhalla.common.conf.ElasticClientConf;
import io.hybrid.valhalla.common.util.CodecUtil;
import io.hybrid.valhalla.high.scroll.ScrollJsonIterator;
import io.hybrid.valhalla.high.scroll.SingleScrollJsonIteator;
import io.hybrid.valhalla.high.util.HighRequestHelper;
import io.hybrid.valhalla.high.util.HighResponseHelper;
import io.hybrid.valhalla.rest.alias.ElasticAliasAction;
import io.hybrid.valhalla.rest.util.ConfUtil;
import io.hybrid.valhalla.rest.util.ResponseHelper;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.close.CloseIndexRequest;
import org.elasticsearch.action.admin.indices.close.CloseIndexResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.*;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ElasticRawHighExecutor implements IElasticRawHighExecutor {

    private static final Logger log = LoggerFactory.getLogger(ElasticRawHighExecutor.class);
    private RestHighLevelClient client;

    public ElasticRawHighExecutor(ElasticClientConf conf) {
        HttpHost[] hosts = ConfUtil.parseHttpHosts(conf.hostPorts, conf.schema);

        for (HttpHost host : hosts) {
            log.info("Connect to {}", host.toString());
        }
        String authHeader = "";

        RestClientBuilder builder = RestClient.builder(hosts)
            .setMaxRetryTimeoutMillis(600000);

        if (conf.isAuth) {
            log.info("Init client with Auth Enabled");
            try {
                authHeader = CodecUtil.toBase64(conf.user + ":" + conf.password);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                log.error("Unable to init auth", e);
            }

            builder.setDefaultHeaders(new Header[]{
                new BasicHeader("Authorization", "Basic " + authHeader)
            });
        }

        this.client = new RestHighLevelClient(builder);
    }

    @Override
    public boolean createIndex(String index, Map<String, Object> settings, String mappings) {
        return createIndex(index, settings, mappings, null);
    }


    @Override
    public boolean createIndex(String index, Map<String, Object> settings, String mappings, String alias) {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);

        createIndexRequest.settings(settings);
        createIndexRequest.mapping(mappings, XContentType.JSON);

        if (alias != null) {
            createIndexRequest.alias(new Alias(alias));
        }

        CreateIndexResponse createIndexResponse = null;
        try {
            createIndexResponse = client.indices().create(createIndexRequest);
        } catch (IOException e) {
            log.error("Unable to execute create index action for {}", index, e);
            return false;
        }

        return createIndexResponse.isShardsAcknowledged();
    }

    @Override
    public boolean deleteIndex(String index) {
        DeleteIndexRequest request = new DeleteIndexRequest(index);

        DeleteIndexResponse deleteIndexResponse = null;
        try {
            deleteIndexResponse = client.indices().delete(request);
        } catch (IOException e) {
            log.error("Unable to delete index {}", index, e);
            return false;
        }

        return deleteIndexResponse.isAcknowledged();
    }

    @Override
    public boolean openIndex(String index) {
        OpenIndexRequest request = new OpenIndexRequest(index);

        OpenIndexResponse openIndexResponse = null;
        try {
            openIndexResponse = client.indices().open(request);
        } catch (IOException e) {
            log.error("Unable to open index {}", index, e);
            return false;
        }

        return openIndexResponse.isShardsAcknowledged();
    }

    @Override
    public boolean closeIndex(String index) {
        CloseIndexRequest request = new CloseIndexRequest("index");
        CloseIndexResponse closeIndexResponse = null;
        try {
            closeIndexResponse = client.indices().close(request);
        } catch (IOException e) {
            log.error("Unable to close index {}", index, e);
            return false;
        }

        return closeIndexResponse.isAcknowledged();
    }

    @Override
    public boolean existIndex(String index) {
        //@TODO, why IndicesExistsRequest was removed?
        Response response = null;
        try {
            response = this.client.getLowLevelClient().performRequest("HEAD", "/" + index);
        } catch (IOException e) {
            log.error("Unable to check existed index : {}", index, e);
            return false;
        }

        return ResponseHelper.response200Check(response);
    }

    @Override
    public boolean existAlias(String index, String alias) {
        String targetPath = String.format("/%s/_alias/%s", index, alias);

        Response response = null;
        try {
            response = this.client.getLowLevelClient().performRequest("HEAD", targetPath);
        } catch (IOException e) {
            log.error("Unable to check existed index for alias : {} - {} ", index, alias, e);
            return false;
        }

        return ResponseHelper.response200Check(response);
    }

    @Override
    public boolean addAlias(String index, String alias) {
        String targetPath = String.format("/%s/_alias/%s", index, alias);

        Response response = null;
        try {
            response = this.client.getLowLevelClient().performRequest("PUT", targetPath);
        } catch (IOException e) {
            log.error("Unable to add index for alias : {} - {} ", index, alias, e);
            return false;
        }

        return ResponseHelper.response200Check(response);
    }

    @Override
    public boolean removeAlias(String index, String alias) {
        String targetPath = String.format("/%s/_alias/%s", index, alias);

        Response response = null;
        try {
            response = this.client.getLowLevelClient().performRequest("DELETE", targetPath);
        } catch (IOException e) {
            log.error("Unable to delete index for alias : {} - {} ", index, alias, e);
            return false;
        }

        return ResponseHelper.response200Check(response);
    }

    @Override
    public String[] indexAllAlias(String index) {
        String queryPath = String.format("%s/_alias/*", index);

        Response response = null;
        try {
            response = this.client.getLowLevelClient().performRequest("GET", queryPath);
            JsonObject res = ResponseHelper.getJson(response);

            Set<String> keyset = res.keySet();

            String[] keys = new String[keyset.size()];
            int i = 0;

            for (String key : keyset) {
                keys[i++] = key;
            }

            return keys;
        } catch (IOException e) {
            log.error("Unable to find all aliases for index : {}", index, e);
            return new String[0];
        }
    }

    @Override
    public String[] aliasAllIndex(String alias) {
        String queryPath = String.format("/_alias/%s", alias);

        Response response = null;
        try {
            response = this.client.getLowLevelClient().performRequest("GET", queryPath);
            JsonObject res = ResponseHelper.getJson(response);

            Set<String> keyset = res.keySet();

            String[] keys = new String[keyset.size()];
            int i = 0;

            for (String key : keyset) {
                keys[i++] = key;
            }

            return keys;
        } catch (IOException e) {
            log.error("Unable to find all indices for alias : {}", alias, e);
            return new String[0];
        }
    }

    @Override
    public boolean aliasesAction(ElasticAliasAction[] aliasActions) {
        JsonArray actionArray = new JsonArray();

        for (ElasticAliasAction action : aliasActions) {
            JsonObject targetObj = new JsonObject();
            targetObj.addProperty("index", action.index);
            targetObj.addProperty("alias", action.alias);

            JsonObject actionObj = new JsonObject();
            actionObj.add(action.action.toString().toLowerCase(), targetObj);

            actionArray.add(actionObj);
        }

        JsonObject queryObj = new JsonObject();
        queryObj.add("actions", actionArray);

        Response response = null;
        try {
            response = this.client.getLowLevelClient().performRequest("POST", "/_aliases", Collections.EMPTY_MAP,
                new NStringEntity(queryObj.toString()),
                new BasicHeader("CONTENT-TYPE", "application/json"));
        } catch (IOException e) {
            log.error("Unable to do alias", e);
            return false;
        }

        return ResponseHelper.response200Check(response) && ResponseHelper.responseTimeoutCheck(response);
    }

    @Override
    public boolean insert(String index, String id, String value) {
        IndexRequest indexRequest = new IndexRequest(index);
        indexRequest.id(id);
        indexRequest.source(value, XContentType.JSON);

        IndexResponse response = null;
        try {
            response = this.client.index(indexRequest);
        } catch (IOException e) {
            log.error("Unable to insert document into {} with id {}", index, id, e);
            return false;
        }

        return response.getResult() == DocWriteResponse.Result.CREATED;
    }

    @Override
    public boolean insert(String index, String id, JsonObject value) {
        return insert(index, id, value.toString());
    }

    @Override
    public boolean delete(String index, String id) {
        DeleteRequest deleteRequest = new DeleteRequest(index);
        deleteRequest.id(id);

        DeleteResponse response = null;
        try {
            response = this.client.delete(deleteRequest);
        } catch (IOException e) {
            log.error("Unable to delete document from {} with id {}", index, id, e);
            return false;
        }

        return response.getResult() == DocWriteResponse.Result.DELETED;
    }

    @Override
    public DocWriteResponse.Result update(UpdateRequest updateRequest) {
        try {
            UpdateResponse response = this.client.update(updateRequest);
            return response.getResult();
        } catch (IOException e) {
            log.error("Unable to update", e);
            return DocWriteResponse.Result.NOOP;
        }
    }

    @Override
    public DocWriteResponse.Result update(String index, String type, String id, String script, Map<String, Object> params) {
        UpdateRequest request = new UpdateRequest(index, type, id);
        request.script(new Script(ScriptType.INLINE, "painless", script, params));

        return update(request);
    }

    @Override
    public DocWriteResponse.Result update(String index, String type, String id, String partialJson) {
        UpdateRequest request = new UpdateRequest(index, type, id);
        request.doc(partialJson);

        return update(request);
    }

    @Override
    public DocWriteResponse.Result update(String index, String type, String id, JsonObject partialJson) {
        return update(index, type, id, partialJson.toString());
    }

    @Override
    public boolean updateByQuery(String index, JsonObject query, JsonObject script) {
        String queryPath = String.format("%s/_update_by_query", index);

        JsonObject body = new JsonObject();
        body.add("query", query);
        body.add("script", script);

        Response response = null;
        try {
            response = this.client.getLowLevelClient().performRequest("POST", queryPath,
                Collections.emptyMap(), new NStringEntity(body.toString(), ContentType.APPLICATION_JSON));
        } catch (IOException e) {
            log.error("Unable to update by query {}", index, e);
            return false;
        }


        return ResponseHelper.response200Check(response);
    }

    @Override
    public JsonElement get(String index, String id) {
        GetRequest getRequest = new GetRequest(index);
        getRequest.id(id);

        GetResponse response = null;
        try {
            response = this.client.get(getRequest);
        } catch (IOException e) {
            log.error("Unable to get from {} with id = {}", index, id, e);
            return null;
        }

        return HighResponseHelper.jsonFromGetResponse(response);
    }

    @Override
    public JsonArray multiGet(MultiGetRequest request) {
        MultiGetResponse responses = null;
        try {
            responses = this.client.multiGet(request);
        } catch (IOException e) {
            log.error("Unable to do multi get", e);
            return null;
        }

        JsonArray jsonArray = new JsonArray();

        for (MultiGetItemResponse response : responses) {
            jsonArray.add(HighResponseHelper.jsonFromGetResponse(response.getResponse()));
        }

        return jsonArray;
    }

    @Override
    public BulkResponse bulk(BulkRequest request) {
        try {
            return this.client.bulk(request);
        } catch (IOException e) {
            log.error("Unable to do bulk action", e);
            return null;
        }
    }

    @Override
    public JsonElement search(String index, QueryBuilder[] filters, int size, String[] includeFields) {
        SearchRequest request = HighRequestHelper.buildSearchRequest(index, filters, size, includeFields);
        return search(request);
    }

    @Override
    public JsonElement search(SearchRequest searchRequest) {
        SearchResponse response = null;
        try {
            response = this.client.search(searchRequest);
        } catch (IOException e) {
            log.error("Unable to search request for {}", searchRequest.toString(), e);
            return null;
        }

        return HighResponseHelper.jsonArrayFromSearchHits(response.getHits());
    }

    @Override
    public Iterator<JsonElement> scroll(String index, QueryBuilder[] filters, int size, String[] includeFields, long timeValue, TimeUnit timeUnit) {
        SearchRequest request = HighRequestHelper.buildSearchRequest(index, filters, size, includeFields);
        request.scroll(new TimeValue(timeValue, timeUnit));

        return scroll(request);
    }

    @Override
    public Iterator<JsonElement> scroll(SearchRequest searchRequest) {
        SearchResponse response = null;
        try {
            response = this.client.search(searchRequest);
        } catch (IOException e) {
            log.error("Unable to search scroll : {}", searchRequest, e);
            return SingleScrollJsonIteator.EMPTY;
        }

        SearchHits hits = response.getHits();

        long total = hits.getTotalHits();
        long fetched = hits.getHits().length;

        if (fetched >= total) {
            return new SingleScrollJsonIteator(hits);
        } else {
            return new ScrollJsonIterator(response.getScrollId(), total, hits, this.client, searchRequest);
        }
    }


    @Override
    public JsonElement scrollToAll(String index, QueryBuilder[] filters, int size, String[] includeFields) {
        SearchRequest request = HighRequestHelper.buildSearchRequest(index, filters, size, includeFields);
        request.scroll(TimeValue.MINUS_ONE);

        return scrollToAll(request);
    }

    @Override
    public JsonElement scrollToAll(SearchRequest searchRequest) {
        Iterator<JsonElement> valueIter = scroll(searchRequest);
        JsonArray jsonArray = new JsonArray();

        while (valueIter.hasNext()) {
            JsonArray v = (JsonArray)valueIter.next();

            for (JsonElement ele: v) {
                jsonArray.add(ele);
            }
        }

        return jsonArray;
    }

    @Override
    public JsonElement aggregate(String index, QueryBuilder[] filters, AggregationBuilder[] aggregates) {
        SearchRequest request = HighRequestHelper.buildSearchRequest(index, filters, aggregates);

        AggregationBuilder build = aggregates[0];

        return aggregate(request);
    }

    @Override
    public JsonElement aggregate(SearchRequest searchRequest) {
        SearchResponse response = null;
        try {
            response = this.client.search(searchRequest);
        } catch (IOException e) {
            log.error("Unable to aggregate for request : {}", searchRequest, e);
            return JsonNull.INSTANCE;
        }

        Aggregations aggregations = response.getAggregations();
        JsonObject object = new JsonObject();

        return null;
    }

    @Override
    public RestClient rawRestClient() {
        return this.client.getLowLevelClient();
    }

    @Override
    public RestHighLevelClient rawHighRestClient() {
        return this.client;
    }
}
