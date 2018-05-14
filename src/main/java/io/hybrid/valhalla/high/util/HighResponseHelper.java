package io.hybrid.valhalla.high.util;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.hybrid.valhalla.common.util.StorageUtil;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;

public class HighResponseHelper {
    public static JsonElement jsonFromGetResponse(GetResponse response) {
        return StorageUtil.jsonParser.parse(response.getSourceAsString());
    }

    public static JsonElement jsonFromSearchHit(SearchHit hit) {
        return StorageUtil.jsonParser.parse(hit.getSourceAsString());
    }

    public static JsonArray jsonArrayFromSearchHits(SearchHits hits) {
        JsonArray jsonArray = new JsonArray();

        for (SearchHit hit: hits) {
            jsonArray.add(jsonFromSearchHit(hit));
        }

        return jsonArray;
    }

    public static JsonElement jsonFromAggregation(Aggregation aggregation, AggregationBuilder builder) {
        String name = builder.getName();
        String type = aggregation.getType();

        return null;
    }
}
