package com.ebay.valhalla.rest;

import com.ebay.valhalla.api.ElasticExecutor;
import com.ebay.valhalla.api.aggregate.ElasticAggregate;
import com.ebay.valhalla.api.alias.ElasticAliasAction;
import com.ebay.valhalla.api.filter.ElasticFilter;
import com.ebay.valhalla.api.sort.ElasticSort;
import com.ebay.valhalla.conf.ElasticClientConf;
import com.ebay.valhalla.rest.aggregation.ElasitcRestAggregateParser;
import com.ebay.valhalla.rest.filter.ElasticRestSearchParser;
import com.ebay.valhalla.rest.sort.ElasticRestSortParser;
import com.ebay.valhalla.rest.util.RequestHelper;
import com.ebay.valhalla.rest.util.ResponseHelper;
import com.ebay.valhalla.schema.ElasticSearchSchema;
import com.ebay.valhalla.util.CalendarUtil;
import com.ebay.valhalla.util.CodecUtil;
import com.ebay.valhalla.util.ConfUtil;
import com.ebay.valhalla.util.StorageUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class ElasticRestExecutor implements ElasticExecutor {
    private static final Logger log = LoggerFactory.getLogger(ElasticRestExecutor.class);

    private final RestClient client;

    public ElasticRestExecutor(ElasticClientConf conf) {
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

//            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//            credentialsProvider.setCredentials(AuthScope.ANY,
//                new UsernamePasswordCredentials(conf.user, conf.password));

            builder.setDefaultHeaders(new Header[]{
                new BasicHeader("Authorization", "Basic " + authHeader)
            });

//            builder.setHttpClientConfigCallback(httpClientBuilder ->
//                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        }

        this.client = builder.build();
    }

    @Override
    public boolean createIndex(Class clz) {
        return createIndex(clz, CalendarUtil.today());
    }

    @Override
    public boolean createIndex(Class clz, Calendar day) {
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);
        Response response = null;

        String targetIndex = schema.getWriteIndex(day);

        try {
            response = this.client.performRequest("PUT", "/" + targetIndex, Collections.emptyMap(), new NStringEntity(
                schema.toIndexCreate(), ContentType.APPLICATION_JSON));
        } catch (IOException e) {
            log.error("Unable to performance index create request : {} - {}", targetIndex, schema.type, e);
            return false;
        }

        return ResponseHelper.responseAckCheck(response);
    }

    @Override
    public boolean deleteIndex(Class clz) {
        return deleteIndex(clz, CalendarUtil.today());
    }

    @Override
    public boolean deleteIndex(Class clz, Calendar day) {
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

        String targetIndex = schema.getWriteIndex(day);

        log.info("Delete Index {} for Class {}", targetIndex, clz.getName());

        return deleteIndex(targetIndex);
    }

    @Override
    public boolean deleteIndex(String index) {
        log.info("Delete Index {} ", index);

        Response response = null;
        try {
            response = this.client.performRequest("DELETE", "/" + index);
        } catch (IOException e) {
            log.error("Unable to delete index : {}", index, e);
            return false;
        }

        return ResponseHelper.responseAckCheck(response);
    }

    @Override
    public boolean existedIndex(Class clz) {
        return existedIndex(clz, CalendarUtil.today());
    }

    @Override
    public boolean existedIndex(Class clz, Calendar day) {
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

        String targetIndex = schema.getWriteIndex(day);

        return existedIndex(targetIndex, schema.type);
    }

    @Override
    public boolean existedIndex(String index, String type) {
        log.info("Checking existence for Index {} for type {}", index, type);

        Response response = null;
        try {
            response = this.client.performRequest("HEAD", "/" + index);
        } catch (IOException e) {
            log.error("Unable to check existed index : {}", index, e);
            return false;
        }

        return ResponseHelper.response200Check(response);
    }

    //@Use https://www.elastic.co/guide/en/elasticsearch/reference/current/search-count.html
    @Override
    public long countIndex(Class clz) {
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);
        Response response = null;
        try {
            response = this.client.performRequest("GET", "/" + schema.getReadIndex() + "/" + schema.type + "/_count");
        } catch (IOException e) {
            log.error("Unable to check count index : {}", schema.getReadIndex(), e);
            return -1;
        }

        if (!ResponseHelper.response200Check(response)) return -1;

        return ResponseHelper.getCountValue(response);
    }

    @Override
    public boolean clearIndex(Class clz) {
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

        Response response = null;
        try {
            response = this.client.performRequest("POST", "/" + schema.getReadIndex() + "/_delete_by_query", Collections.EMPTY_MAP,
                new NStringEntity("{}"));
        } catch (IOException e) {
            log.error("Unable to check clear index : {}", schema.getReadIndex(), e);
            return false;
        }

        return ResponseHelper.response200Check(response) && ResponseHelper.responseTimeoutCheck(response);
    }

    @Override
    public boolean existAlias(Class clz, Calendar day) {
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);
        String targetIndex = schema.getWriteIndex(day);
        String view = schema.getReadIndex();

        String targetPath = String.format("/%s/_alias/%s", targetIndex, view);

        Response response = null;
        try {
            response = this.client.performRequest("HEAD", targetPath);
        } catch (IOException e) {
            log.error("Unable to check existed index for view : {} - {} ", targetIndex, view, e);
            return false;
        }

        return ResponseHelper.response200Check(response);
    }

    @Override
    public boolean addAlias(Class clz) {
        return addAlias(clz, CalendarUtil.today());
    }

    @Override
    public boolean addAlias(Class clz, Calendar day) {
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);
        String targetIndex = schema.getWriteIndex(day);
        String view = schema.getReadIndex();

        return aliasesAction(new ElasticAliasAction[]{
            new ElasticAliasAction(targetIndex, view, ElasticAliasAction.Action.ADD)
        });
    }

    @Override
    public boolean removeAlias(Class clz) {
        return removeAlias(clz, CalendarUtil.today());
    }

    @Override
    public boolean removeAlias(Class clz, Calendar day) {
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);
        String targetIndex = schema.getWriteIndex(day);
        String view = schema.getReadIndex();

        return aliasesAction(new ElasticAliasAction[]{
            new ElasticAliasAction(targetIndex, view, ElasticAliasAction.Action.REMOVE)
        });
    }

    @Override
    public boolean removeAlias(String index, String alias) {
        return aliasesAction(new ElasticAliasAction[]{
            new ElasticAliasAction(index, alias, ElasticAliasAction.Action.REMOVE)
        });
    }

    @Override
    public String[] allAliasIndex(Class clz) {
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

        if (!schema.retire) {
            return null; // no alias at all
        }

        String queryPath = String.format("%s/_alias", schema.toView());

        Response response = null;
        try {
            response = this.client.performRequest("GET", queryPath);
            JsonObject res = ResponseHelper.getJson(response);

            Set<String> keyset = res.keySet();

            String[] keys = new String[keyset.size()];
            int i = 0;

            for (String key : keyset) {
                keys[i++] = key;
            }

            return keys;
        } catch (IOException e) {
            log.error("Unable to check existed index for view : {}", schema.toView(), e);
            return null;
        }
    }

    @Override
    public boolean aliasesAction(ElasticAliasAction[] actions) {
        JsonArray actionArray = new JsonArray();

        for (ElasticAliasAction action : actions) {
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
            response = this.client.performRequest("POST", "/_aliases", Collections.EMPTY_MAP,
                new NStringEntity(queryObj.toString()),
                new BasicHeader("CONTENT-TYPE", "application/json"));
        } catch (IOException e) {
            log.error("Unable to do alias", e);
            return false;
        }

        return ResponseHelper.response200Check(response) && ResponseHelper.responseTimeoutCheck(response);
    }

    @Override
    public boolean insertById(Object object, String id) {
        return insertById(object, id, CalendarUtil.today());
    }

    @Override
    public boolean insertById(Object object, String id, Calendar day) {
        Class clz = object.getClass();
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

        String targetIndex = schema.getWriteIndex(day);

        try {
            Response response = this.client.performRequest("PUT", targetIndex + "/" + schema.type + "/" + id,
                schema.insertParams, new NStringEntity(StorageUtil.gson.toJson(object), ContentType.APPLICATION_JSON));
            return ResponseHelper.documentCreatedCheck(response);
        } catch (IOException e) {
            log.error("Unable to insert into index : {} - {} id = {}", targetIndex, schema.type, id, e);
            return false;
        }
    }

    @Override
    public boolean insertByNoId(Object object) {
        return insertByNoId(object, CalendarUtil.today());
    }

    @Override
    public boolean insertByNoId(Object object, Calendar day) {
        Class clz = object.getClass();
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

        String targetIndex = schema.getWriteIndex(day);

        try {
            Response response = this.client.performRequest("PUT", targetIndex + "/" + schema.type,
                schema.insertParams, new NStringEntity(StorageUtil.gson.toJson(object), ContentType.APPLICATION_JSON));
            return ResponseHelper.documentCreatedCheck(response);
        } catch (IOException e) {
            log.error("Unable to insert into index : {} - {}", targetIndex, schema.type, e);
            return false;
        }
    }

    @Override
    public boolean insert(Object object) {
        return insert(object, CalendarUtil.today());
    }

    @Override
    public boolean insert(Object object, Calendar day) {
        Class clz = object.getClass();
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

        if (schema.id.length == 0) {
            return insertByNoId(object, day);
        }

        JsonObject jobj = StorageUtil.gson.toJsonTree(object).getAsJsonObject();
        String id = schema.getId(jobj);

        String targetIndex = schema.getWriteIndex(day);

        try {
            Response response = this.client.performRequest("PUT", targetIndex + "/" + schema.type + "/" + id,
                schema.insertParams,
                new NStringEntity(StorageUtil.gson.toJson(object), ContentType.APPLICATION_JSON));
            return ResponseHelper.documentCreatedCheck(response);
        } catch (IOException e) {
            log.error("Unable to insert into index : {} - {} id = {}", targetIndex, schema.type, id, e);
            return false;
        }
    }

    @Override
    public boolean delete(Class clz, String id) {
        return delete(clz, CalendarUtil.today(), id);
    }

    @Override
    public boolean delete(Class clz, Calendar day, String id) {
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);
        String targetIndex = schema.getWriteIndex(day);

        try {
            Response response = this.client.performRequest("DELETE", "/" + targetIndex + "/" + schema.type + "/" + id);
            return ResponseHelper.documentDeletedCheck(response);
        } catch (IOException e) {
            log.error("Unable to delete index : {} - {} id = {}", targetIndex, schema.type, id, e);
            return false;
        }
    }

    @Override
    public <T> T get(Class<T> clz, String id) {
        return get(clz, null, id);
    }

    @Override
    public <T> T get(Class<T> clz, Calendar day, String id) {
        JsonElement element = rawGet(clz, day, id);

        if (!element.isJsonObject()) {
            return null;
        } else {
            return StorageUtil.gson.fromJson(element, clz);
        }
    }
    @Override
    public <T> T[] getAll(Class<T[]> arrayClz) {
        return search(arrayClz, Collections.emptyList());
    }

    @Override
    public JsonElement rawGet(Class clz, String id) {
        return rawGet(clz, null, id);
    }

    @Override
    public JsonElement rawGet(Class clz, Calendar day, String id) {
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

        if (day == null) {
            day = CalendarUtil.today();
        }
        String targetIndex = schema.getWriteIndex(day);

        try {
            Response response = this.client.performRequest("GET", "/" + targetIndex + "/" + schema.type + "/" + id);
            JsonObject res = ResponseHelper.getJson(response);

            if (!res.get("found").getAsBoolean()) {
                return null;
            }

            return res.get("_source").getAsJsonObject();
        } catch (IOException e) {
            log.error("Unable to get index : {} - {} id = {}", targetIndex, schema.type, id, e);
            return JsonNull.INSTANCE;
        }
    }

    @Override
    public <T> boolean bulkInsert(T[] objects) {
        return bulkInsert(Arrays.asList(objects));
    }

    @Override
    public <T> boolean bulkInsert(List<T> objects) {
        try {
            Response response = this.client.performRequest("POST", "/_bulk", Collections.emptyMap(),
                new NStringEntity(RequestHelper.buildBulkInsert(objects), ContentType.APPLICATION_JSON));
            return ResponseHelper.bulkInsertCheck(response);
        } catch (IOException e) {
            log.error("Unable to bulk insert", e);
            System.out.println(RequestHelper.buildBulkInsert(objects));

            return false;
        }
    }

    @Override
    public <T> boolean bulkInsert(T[] objects, Calendar[] days) {
        return bulkInsert(Arrays.asList(objects), Arrays.asList(days));
    }

    @Override
    public <T> boolean bulkInsert(List<T> objects, List<Calendar> days) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("Insert into table with specified day information");
            }
            Response response = this.client.performRequest("POST", "/_bulk", Collections.emptyMap(),
                new NStringEntity(RequestHelper.buildBulkInsert(objects, days), ContentType.APPLICATION_JSON));
            return ResponseHelper.bulkInsertCheck(response);
        } catch (IOException e) {
            log.error("Unable to bulk insert with days", e);
            System.out.println(RequestHelper.buildBulkInsert(objects, days));

            return false;
        }
    }

    @Override
    public <T> T[] search(Class<T[]> arrayClz, List<ElasticFilter> filters) {
        return search(arrayClz, filters, 0, null);
    }

    @Override
    public <T> T[] search(Class<T[]> arrayClz, ElasticFilter[] filters) {
        return search(arrayClz, Arrays.asList(filters));
    }

    @Override
    public JsonArray rawSearch(Class clz, ElasticFilter[] filters, int size, String[] includeFields, ElasticSort[] sorts) {
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

        return rawSearch(schema.getReadIndex(), schema.type, filters, size, includeFields, sorts);
    }

    @Override
    public JsonArray rawSearch(String index, String type, ElasticFilter[] filters, int size, String[] includeFields, ElasticSort[] sorts) {
        String link = "/" + index + "/_search";

        Map<String, String> params = new HashMap<>();

        if (size > 0) {
            params.put("size", "" + size);
        }

        JsonObject searchObj = new JsonObject();
        if (filters != null) {
            searchObj.add("query", ElasticRestSearchParser.parse(Arrays.asList(filters)));
        }

        if (sorts != null && sorts.length > 0) {
            searchObj.add("sort", ElasticRestSortParser.parse(sorts));
        }

        if (includeFields != null) {
            JsonArray fieldList = new JsonArray();

            for (String ifield : includeFields) {
                fieldList.add(ifield);
            }
            searchObj.add("_source", fieldList);
        }

        log.info("{} : {}", index, searchObj.toString());

        try {
            Response response = this.client.performRequest("GET", link, params,
                new NStringEntity(searchObj.toString(), ContentType.APPLICATION_JSON)
            );
            JsonObject res = ResponseHelper.getJson(response);

            if (res.get("timed_out").getAsBoolean()) {
                log.error("Timeout when do search index : {} - {}", index, type);
                return null;
            }

            JsonArray hits = res.get("hits").getAsJsonObject().get("hits").getAsJsonArray();
            int totalSize = hits.size();
            JsonArray results = new JsonArray();

            for (int i = 0; i < totalSize; i++) {
                results.add(hits.get(i).getAsJsonObject().get("_source"));
            }

            return results;
        } catch (IOException e) {
            log.error("Unable to search index : {} - {}", index, type, e);
            return null;
        }
    }

    @Override
    public <T> T[] search(Class<T[]> arrayClz, List<ElasticFilter> filters, int size) {
        return search(arrayClz, filters, size, null);
    }

    @Override
    public <T> T[] search(Class<T[]> arrayClz, ElasticFilter[] filters, int size) {
        return search(arrayClz, Arrays.asList(filters), size);
    }

    @Override
    public <T> T[] search(Class<T[]> arrayClz, List<ElasticFilter> filters, int size, List<ElasticSort> sorts) {
        Class clz = arrayClz.getComponentType();
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

        String targetIndex = schema.getReadIndex();

        String link = "/" + targetIndex + "/_search";

        Map<String, String> params = new HashMap<>();

        if (size > 0) {
            params.put("size", "" + size);
        }

        JsonObject searchObj = new JsonObject();
        searchObj.add("query", ElasticRestSearchParser.parse(filters));

        if (sorts != null && sorts.size() > 0) {
            searchObj.add("sort", ElasticRestSortParser.parse(sorts));
        }

        try {
            Response response = this.client.performRequest("GET", link, params,
                new NStringEntity(searchObj.toString(), ContentType.APPLICATION_JSON)
            );
            JsonObject res = ResponseHelper.getJson(response);

            if (res.get("timed_out").getAsBoolean()) {
                log.error("Timeout when do search index : {} - {}", targetIndex, schema.type);
                return null;
            }

            JsonArray hits = res.get("hits").getAsJsonObject().get("hits").getAsJsonArray();

            Object[] results = new Object[hits.size()];

            for (int i = 0; i < results.length; i++) {
                results[i] = StorageUtil.gson.fromJson(hits.get(i).getAsJsonObject().get("_source"), clz);
            }

            return Arrays.copyOf(results, results.length, arrayClz);
        } catch (IOException e) {
            log.error("Unable to search index : {} - {}", targetIndex, schema.type, e);
            return null;
        }
    }

    @Override
    public <T> T[] search(Class<T[]> arrayClz, ElasticFilter[] filters, int size, List<ElasticSort> sorts) {
        return search(arrayClz, Arrays.asList(filters), size, sorts);
    }

    @Override
    public <T> Object aggregate(Class<T[]> arrayClz, List<ElasticFilter> filters, ElasticAggregate[] aggregates) {
        Class clz = arrayClz.getComponentType();
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

        String targetIndex = schema.getReadIndex();

        String link = "/" + targetIndex + "/_search";

        JsonObject searchObj = new JsonObject();
        searchObj.add("query", ElasticRestSearchParser.parse(filters));
        searchObj.add("aggs", ElasitcRestAggregateParser.parse(aggregates));
        searchObj.addProperty("size", 0);

        try {
            Response response = this.client.performRequest("GET", link, Collections.emptyMap(), new NStringEntity(
                searchObj.toString(), ContentType.APPLICATION_JSON
            ));


            JsonObject res = ResponseHelper.getJson(response);

            if (res.get("timed_out").getAsBoolean()) {
                return null;
            }

            JsonObject aggregations = res.get("aggregations").getAsJsonObject();

            return ResponseHelper.fetchBucketValueMap(aggregations, aggregates[0]);
        } catch (Throwable t) {
            log.error("Unable to search index : {} - {}", targetIndex, schema.type, t);
            return null;
        }
    }

    @Override
    public <T> Object aggregate(Class<T[]> arrayClz, ElasticFilter[] filters, ElasticAggregate[] aggregates) {
        return aggregate(arrayClz, Arrays.asList(filters), aggregates);
    }

    @Override
    public JsonElement rawAggregate(Class clz, ElasticFilter[] filters, ElasticAggregate[] aggregates, String[] includeFields) {
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

        return rawAggregate(schema.getReadIndex(), schema.type, filters, aggregates, includeFields);
    }

    @Override
    public JsonElement rawAggregate(String index, String type, ElasticFilter[] filters, ElasticAggregate[] aggregates, String[] includeFields) {
        String link = "/" + index + "/_search";

        JsonObject searchObj = new JsonObject();

        if (includeFields != null) {
            JsonArray fieldList = new JsonArray();

            for (String ifield : includeFields) {
                fieldList.add(ifield);
            }
            searchObj.add("_source", fieldList);
        }

        if (filters != null) {
            searchObj.add("query", ElasticRestSearchParser.parse(Arrays.asList(filters)));
        }
        searchObj.add("aggs", ElasitcRestAggregateParser.parse(aggregates));
        searchObj.addProperty("size", 0);

        log.info("{} : {}", index, searchObj.toString());

        try {
            Response response = this.client.performRequest("GET", link, Collections.emptyMap(), new NStringEntity(
                searchObj.toString(), ContentType.APPLICATION_JSON
            ));


            JsonObject res = ResponseHelper.getJson(response);

            if (res.get("timed_out").getAsBoolean()) {
                return null;
            }

            JsonObject aggregations = res.get("aggregations").getAsJsonObject();

            return ResponseHelper.fetchRawBucketValueMapArray(aggregations, Arrays.asList(aggregates));
        } catch (Throwable t) {
            log.error("Unable to search index : {} - {}", index, type, t);
            return null;
        }
    }

    @Override
    public <T> T[] scroll(Class<T[]> arrayClz, List<ElasticFilter> filters, int batchSize, List<ElasticSort> sorts, int keepMin) {
        Class clz = arrayClz.getComponentType();
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

        String targetIndex = schema.getReadIndex();

        String link = "/" + targetIndex + "/_search";

        Map<String, String> params = new HashMap<>();

        params.put("scroll", "" + keepMin + "m");
        if (batchSize > 0) {
            params.put("size", "" + batchSize);
        }

        JsonObject searchObj = new JsonObject();
        searchObj.add("query", ElasticRestSearchParser.parse(filters));

        if (sorts != null && sorts.size() > 0) {
            searchObj.add("sort", ElasticRestSortParser.parse(sorts));
        }

        log.info("{} : {}", schema.index, searchObj.toString());

        Object[] results = null;
        String scrollId = null;

        // fetch first ones
        try {
            Response response = this.client.performRequest("GET", link, params,
                new NStringEntity(searchObj.toString(), ContentType.APPLICATION_JSON)
            );
            JsonObject res = ResponseHelper.getJson(response);

            if (res.get("timed_out").getAsBoolean()) {
                log.error("Timeout when do search index : {} - {}", targetIndex, schema.type);
                return null;
            }

            scrollId = res.get("_scroll_id").getAsString();

            JsonObject hitObj = res.get("hits").getAsJsonObject();

            int totalCount = hitObj.get("total").getAsInt();
            results = new Object[totalCount];

            JsonArray hits = hitObj.get("hits").getAsJsonArray();
            int hitsCount = hits.size();

            for (int i = 0; i < hitsCount; i++) {
                results[i] = StorageUtil.gson.fromJson(hits.get(i).getAsJsonObject().get("_source"), clz);
            }

            // all done
            if (hitsCount == totalCount) {
                return Arrays.copyOf(results, results.length, arrayClz);
            }
        } catch (IOException e) {
            log.error("Unable to search index : {} - {}", targetIndex, schema.type, e);
            return null;
        }


        // start scroll
        int indexOffset = batchSize;
        String scrollLink = "/_search/scroll";
        JsonObject scrollObj = new JsonObject();
        scrollObj.addProperty("scroll", "" + keepMin + "m");
        scrollObj.addProperty("scroll_id", scrollId);
        Map<String, String> scrollParams = new HashMap<>();

        NStringEntity request = new NStringEntity(scrollObj.toString(), ContentType.APPLICATION_JSON);

        while (true) {
            if (log.isDebugEnabled()) {
                log.debug("Scroll for next Round index : {} - {}", targetIndex, schema.type);
            }

            Response response = null;
            try {
                response = this.client.performRequest("POST", scrollLink, scrollParams, request);

                JsonObject res = ResponseHelper.getJson(response);

                if (res.get("timed_out").getAsBoolean()) {
                    log.error("Timeout when do search index : {} - {}", targetIndex, schema.type);
                    return null;
                }

                JsonArray hits = res.get("hits").getAsJsonObject().get("hits").getAsJsonArray();
                int hitsCount = hits.size();

                if (hitsCount == 0) {
                    break;
                }

                for (int i = 0; i < hitsCount; i++) {
                    results[indexOffset + i] = StorageUtil.gson.fromJson(hits.get(i).getAsJsonObject().get("_source"), clz);
                }

                indexOffset += batchSize;
            } catch (IOException e) {
                log.error("Unable to scroll index : {} - {}", targetIndex, schema.type, e);
                return null;
            }
        }

        return Arrays.copyOf(results, results.length, arrayClz);
    }

    @Override
    public <T> T[] scroll(Class<T[]> arrayClz, ElasticFilter[] filters, int size, List<ElasticSort> sorts, int keepMin) {
        return scroll(arrayClz, Arrays.asList(filters), size, sorts, keepMin);
    }

    @Override
    public JsonArray rawScroll(Class arrayClz, ElasticFilter[] filters, int batchSize, String[] includeFiles, ElasticSort[] sorts, int keepMin) {
        Class clz = arrayClz.getComponentType();
        ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

        return rawScroll(schema.getReadIndex(), schema.type, filters, batchSize, includeFiles, sorts, keepMin);
    }

    @Override
    public JsonArray rawScroll(String index, String type, ElasticFilter[] filters, int batchSize, String[] includeFields, ElasticSort[] sorts, int keepMin) {

        String link = "/" + index + "/_search";

        Map<String, String> params = new HashMap<>();

        params.put("scroll", "" + keepMin + "m");
        if (batchSize > 0) {
            params.put("size", "" + batchSize);
        }

        JsonObject searchObj = new JsonObject();

        if (includeFields != null) {
            JsonArray fieldList = new JsonArray();

            for (String ifield : includeFields) {
                fieldList.add(ifield);
            }
            searchObj.add("_source", fieldList);
        }

        searchObj.add("query", ElasticRestSearchParser.parse(filters));

        if (sorts != null && sorts.length > 0) {
            searchObj.add("sort", ElasticRestSortParser.parse(sorts));
        }

        //@TODO provide sorts information
//        if (sorts != null && sorts.size() > 0) {
//            searchObj.add("sort", ElasticRestSortParser.parse(sorts));
//        }

        JsonArray results = new JsonArray();
        String scrollId = null;
        int totalCount = 0;

        log.info(searchObj.toString());

        // fetch first ones
        try {
            Response response = this.client.performRequest("GET", link, params,
                new NStringEntity(searchObj.toString(), ContentType.APPLICATION_JSON)
            );
            JsonObject res = ResponseHelper.getJson(response);

            if (res.get("timed_out").getAsBoolean()) {
                log.error("Timeout when do search index : {} - {}", index, type);
                return null;
            }

            scrollId = res.get("_scroll_id").getAsString();

            JsonObject hitObj = res.get("hits").getAsJsonObject();

            totalCount = hitObj.get("total").getAsInt();

            JsonArray hits = hitObj.get("hits").getAsJsonArray();
            int hitsCount = hits.size();

            for (int i = 0; i < hitsCount; i++) {
                results.add(hits.get(i).getAsJsonObject().get("_source"));
            }

            // all done
            if (hitsCount == totalCount) {
                return results;
            }
        } catch (IOException e) {
            log.error("Unable to search index : {} - {}", index, type, e);
            return null;
        }


        // start scroll
        int indexOffset = batchSize;
        String scrollLink = "/_search/scroll";
        JsonObject scrollObj = new JsonObject();
        scrollObj.addProperty("scroll", "" + keepMin + "m");
        scrollObj.addProperty("scroll_id", scrollId);
        Map<String, String> scrollParams = new HashMap<>();

        NStringEntity request = new NStringEntity(scrollObj.toString(), ContentType.APPLICATION_JSON);

        while (true) {
            if (log.isDebugEnabled()) {
                log.debug("Scroll for next Round index : {} - {}", index, type);
            }

            Response response = null;
            try {
                response = this.client.performRequest("POST", scrollLink, scrollParams, request);

                JsonObject res = ResponseHelper.getJson(response);

                if (res.get("timed_out").getAsBoolean()) {
                    log.error("Timeout when do search index : {} - {}", index, type);
                    return null;
                }

                JsonArray hits = res.get("hits").getAsJsonObject().get("hits").getAsJsonArray();
                int hitsCount = hits.size();

                if (hitsCount == 0) {
                    break;
                }

                for (int i = 0; i < hitsCount; i++) {
                    results.add(hits.get(i).getAsJsonObject().get("_source"));
                }

                indexOffset += batchSize;

                if (indexOffset == totalCount) {
                    break;
                }
            } catch (IOException e) {
                log.error("Unable to scroll index : {} - {}", index, type, e);
                return null;
            }
        }

        return results;
    }

    @Override
    public JsonObject raw(String method, String path, String param) {
        return null;
    }

    @Override
    public void close() throws IOException {
        this.client.close();
    }
}
