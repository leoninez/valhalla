package io.hybrid.valhalla.high;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import io.hybrid.valhalla.common.conf.ElasticClientConf;
import io.hybrid.valhalla.common.schema.ElasticSearchSchema;
import io.hybrid.valhalla.common.util.CodecUtil;
import io.hybrid.valhalla.rest.util.ConfUtil;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ElasticHighClzNativeExecutor implements ElasticClzNativeExecutor {

    private static final Logger log = LoggerFactory.getLogger(ElasticHighClzNativeExecutor.class);
    private RestHighLevelClient client;

    public ElasticHighClzNativeExecutor(ElasticClientConf conf) {
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
    public boolean createIndex(Class clz) {
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(schema.index);

        createIndexRequest.settings(schema.toIndexSettingMap());
        createIndexRequest.mapping(schema.toTypeMapping(), XContentType.JSON);

        CreateIndexResponse createIndexResponse = null;
        try {
            createIndexResponse = client.indices().create(createIndexRequest);
        } catch (IOException e) {
            log.error("Unable to execute create index action for {}", schema.index);
            return false;
        }

        return createIndexResponse.isShardsAcknowledged();
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
    public boolean insertByNoId(Object object) {
        return false;
    }

    @Override
    public boolean insert(Object object) {
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
