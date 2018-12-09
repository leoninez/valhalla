package com.tang.valhalla.rest.sort;


import com.tang.valhalla.api.sort.ElasticFieldSort;
import com.tang.valhalla.api.sort.ElasticSort;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public class ElasticRestSortParser {
    public static JsonArray parse(ElasticSort[] sorts) {
        JsonArray sortArry = new JsonArray();

        for (ElasticSort sort : sorts) {
            JsonObject object = new JsonObject();
            JsonObject orderObject = new JsonObject();


            switch (sort.type()) {
                case FIELD:
                    ElasticFieldSort fieldSort = (ElasticFieldSort) sort;
                    orderObject.addProperty("order", fieldSort.order().toString());
                    object.add(fieldSort.field(), orderObject);
                    break;
            }

            sortArry.add(object);
        }

        return sortArry;
    }

    public static JsonArray parse(List<ElasticSort> sorts) {
        JsonArray sortArry = new JsonArray();

        for (ElasticSort sort : sorts) {
            JsonObject object = new JsonObject();


            switch (sort.type()) {
                case FIELD:
                    ElasticFieldSort fieldSort = (ElasticFieldSort) sort;
                    object.addProperty(fieldSort.field(), fieldSort.order().toString());
                    break;
            }

            sortArry.add(object);
        }

        return sortArry;
    }
}
