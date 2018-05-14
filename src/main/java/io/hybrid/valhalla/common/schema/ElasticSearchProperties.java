package io.hybrid.valhalla.common.schema;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.hybrid.valhalla.common.util.GsonJsonable;

import java.util.ArrayList;
import java.util.List;

public class ElasticSearchProperties implements GsonJsonable {
    public List<ElasticSearchField> fields = new ArrayList<>();

    @Override
    public JsonElement toJson() {
        JsonObject proJsonObj = new JsonObject();

        for (ElasticSearchField field : fields) {
            proJsonObj.add(field.key, field.toJson());
        }

        return proJsonObj;
    }
}
