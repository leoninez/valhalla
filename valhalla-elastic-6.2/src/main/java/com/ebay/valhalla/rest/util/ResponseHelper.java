package com.ebay.valhalla.rest.util;

import com.ebay.valhalla.api.aggregate.*;
import com.ebay.valhalla.util.StorageUtil;
import com.google.gson.*;
import org.elasticsearch.client.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ResponseHelper {

    private final static Logger log = LoggerFactory.getLogger(ResponseHelper.class);

    public static JsonObject getJson(Response response) throws IOException {
        return StorageUtil.jsonParser.parse(new InputStreamReader(response.getEntity().getContent())).getAsJsonObject();
    }

    public static String getString(Response response) throws IOException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(response.getEntity().getContent(), StandardCharsets.UTF_8.name());
            return scanner.nextLine();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public static boolean responseAckCheck(Response response) {
        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode >= 300) return false;

        JsonObject res = null;
        try {
            res = getJson(response);
        } catch (IOException e) {
            log.error("Unable to fetch response or format error", e);
            return false;
        }

        return res.get("acknowledged").getAsBoolean();
    }

    public static boolean response200Check(Response response) {
        return response.getStatusLine().getStatusCode() == 200;
    }

    public static boolean responseTimeoutCheck(Response response) {
        JsonObject res = null;
        try {
            res = getJson(response);
        } catch (IOException e) {
            log.error("Unable to parse response or format error", e);
            return false;
        }

        return !res.has("timed_out") || res.get("timed_out").getAsBoolean();
    }

    public static int getCountValue(Response response) {
        JsonObject res = null;
        try {
            res = getJson(response);
        } catch (IOException e) {
            log.error("Unable to parse response or format error", e);
            return -1;
        }

        return res.get("count").getAsInt();
    }

    public static int searchHitsFetch(Response response) {
        JsonObject res = null;
        try {
            res = getJson(response);
        } catch (IOException e) {
            log.error("Unable to parse response or format error", e);
            return -1;
        }

        boolean timeouted = res.get("timed_out").getAsBoolean();

        if (timeouted) return -1;

        return res.get("hits").getAsJsonObject().get("total").getAsInt();
    }

    public static boolean documentCreatedCheck(Response response) {
        JsonObject res = null;
        try {
            res = getJson(response);
        } catch (IOException e) {
            log.error("Unable to parse response or format error", e);
            return false;
        }

        return "created".equals(res.get("result").getAsString());
    }

    public static boolean documentDeletedCheck(Response response) {
        JsonObject res = null;
        try {
            res = getJson(response);
        } catch (IOException e) {
            log.error("Unable to parse response or format error", e);
            return false;
        }

        return "deleted".equals(res.get("result").getAsString());
    }

    public static boolean bulkInsertCheck(Response response) {
        try {
            JsonObject res = getJson(response);
            return !res.get("errors").getAsBoolean();
        } catch (IOException e) {
            log.error("Unable to parse response or format error", e);
            return false;
        }
    }

    // aggregation fetcher
    public static Object fetchBuckedValues(JsonObject valueObj, ElasticAggregate aggregate) {

        if (aggregate instanceof ElasticTopHitsAggregate) {
            ElasticTopHitsClzAggregate etha = (ElasticTopHitsClzAggregate) aggregate;
            Object[] results = new Object[etha.getSize()];


            JsonArray hits = valueObj.get("hits").getAsJsonObject().getAsJsonArray("hits");

            for (int i = 0; i < results.length; i++) {
                JsonObject v = hits.get(i).getAsJsonObject();
                JsonObject vobj = v.get("_source").getAsJsonObject();

                results[i] = StorageUtil.gson.fromJson(vobj, etha.getClzz().getComponentType());
            }

            return Arrays.copyOf(results, results.length, etha.getClzz());
        } else {
            JsonArray buckets = valueObj.getAsJsonArray("buckets");

            Map<String, Integer> resutls = new HashMap<>();

            for (JsonElement element : buckets) {
                JsonObject v = element.getAsJsonObject();
                resutls.put(v.get("key_as_string").getAsString(), v.get("doc_count").getAsInt());
            }

            return resutls;
        }


    }

    public static Object fetchBucketValueMap(JsonObject valueObj, ElasticAggregate aggregate) {
        if (aggregate.subs() == null || aggregate.subs().size() == 0) {
            return fetchBuckedValues(valueObj.get(aggregate.name()).getAsJsonObject(), aggregate);
        } else {
            Map<String, Object> results = new HashMap<>();
            JsonArray buckets = valueObj.get(aggregate.name()).getAsJsonObject().getAsJsonArray("buckets");

            for (JsonElement element : buckets) {
                JsonObject v = element.getAsJsonObject();
                results.put(v.get("key").toString(),
                    fetchBucketValueMap(v, aggregate.subs().get(0)));
            }

            return results;
        }
    }

    public static void main(String[] args) {
        System.out.println(new BigDecimal("2.719275885714286E7"));
    }

    public static JsonElement fetchRawBucketValueMapArray(JsonObject valueObj, List<ElasticAggregate> aggregates) {
        JsonArray results = new JsonArray();
        for (ElasticAggregate aggregate : aggregates) {
            JsonObject bv = new JsonObject();
            bv.addProperty("key", aggregate.name());
            bv.add("value", fetchRawBucketValueMap(valueObj, aggregate));

            results.add(bv);
        }

        return results;
    }

    public static JsonElement fetchRawBucketValueMap(JsonObject valueObj, ElasticAggregate aggregate) {

        if (aggregate.subs() == null || aggregate.subs().size() == 0) {
            return fetchRawBucketValues(valueObj.get(aggregate.name()).getAsJsonObject(), aggregate);
        } else {
            JsonArray results = new JsonArray();
            JsonArray buckets = valueObj.get(aggregate.name()).getAsJsonObject().getAsJsonArray("buckets");

            for (JsonElement element : buckets) {
                JsonObject v = element.getAsJsonObject();
                JsonObject bv = new JsonObject();
                bv.add("key", v.get("key"));
                bv.add("value",fetchRawBucketValueMapArray(v, aggregate.subs()));

                results.add(bv);
            }

            return results;
        }
    }

    public static JsonElement fetchRawBucketValues(JsonObject valueObj, ElasticAggregate aggregate) {

        switch (aggregate.type()) {
            case top:
                return fetchRawTopBucketValues(valueObj);

            case terms:
            case histogram:
                return fetchRawCountBucketValues(valueObj);

            case max:
            case min:
            case avg:
            case sum:
                return fetchRawPeekBucketValues(valueObj);

            case nested:
                throw new RuntimeException("Should Not Reach Here: nested aggregate must have one sub aggregate");
        }

        // @TODO should not reach here
        throw new RuntimeException("Should Not Reach Here: not support aggregate");
    }

    private static JsonElement fetchRawTopBucketValues(JsonObject valueObj) {
        JsonArray results = new JsonArray();
        JsonArray hits = valueObj.get("hits").getAsJsonObject().getAsJsonArray("hits");

        for (JsonElement element : hits) {
            results.add(element.getAsJsonObject().get("_source"));
        }

        return results;
    }

    private static JsonElement fetchRawPeekBucketValues(JsonObject valueObj) {
        return valueObj.get("value");
    }

    private static JsonElement fetchRawCountBucketValues(JsonObject valueObj) {
        return valueObj.getAsJsonArray("buckets");
    }

}
