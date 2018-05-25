package com.ebay.valhalla.schema;

import com.ebay.valhalla.api.GsonJsonable;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ElasticSearchField implements GsonJsonable {
    public String key;
    public ElasticSearchFieldType type;
    public boolean indexEnabled;

    public ElasticSearchField(String key, ElasticSearchFieldType type, boolean indexEnabled) {
        this.key = key;
        this.type = type;
        this.indexEnabled = indexEnabled;
    }

    @Override
    public JsonElement toJson() {
        JsonObject fieldObj = new JsonObject();

        // chinese analyzer
        if (this.type == ElasticSearchFieldType.IK) {
            fieldObj.addProperty("type", "text");
            fieldObj.addProperty("analyzer", "ik_max_word");
            fieldObj.addProperty("search_analyzer", "ik_max_word");
        } else {
            fieldObj.addProperty("type", this.type.name().toLowerCase());
            if (!indexEnabled) {
                fieldObj.addProperty("index", false);
            }
        }

        return fieldObj;
    }
}