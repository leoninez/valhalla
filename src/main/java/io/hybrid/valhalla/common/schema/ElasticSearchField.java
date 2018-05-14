package io.hybrid.valhalla.common.schema;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.hybrid.valhalla.common.util.GsonJsonable;

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

        fieldObj.addProperty("type", this.type.name().toLowerCase());
        if (!indexEnabled) {
            fieldObj.addProperty("index", false);
        }

        return fieldObj;
    }
}