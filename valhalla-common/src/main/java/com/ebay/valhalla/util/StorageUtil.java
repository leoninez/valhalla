package com.ebay.valhalla.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class StorageUtil {

    public static void setGson(Gson newGson) {
        gson = newGson;
    }

    public static Gson gson = new Gson();
    public static JsonParser jsonParser = new JsonParser();

    public static Type[] getFieldGenericClz(Field field) {
        ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
        return parameterizedType.getActualTypeArguments();
    }

    public static Map<String, Object> jsonToMap(JsonObject object) {
        return gson.fromJson(object, HashMap.class);
    }
}
