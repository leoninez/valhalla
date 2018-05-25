package com.ebay.valhalla.rest.util;

import com.ebay.valhalla.schema.ElasticSearchSchema;
import com.ebay.valhalla.util.StorageUtil;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class RequestHelper {

    private final static Logger log = LoggerFactory.getLogger(RequestHelper.class);

    public static <T> String buildBulkInsert(List<T> objects) {
        StringBuffer buffer = new StringBuffer();

        for (T object : objects) {
            Class clz = object.getClass();
            ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

            // need to read id from object
            JsonObject jobj = StorageUtil.gson.toJsonTree(object).getAsJsonObject();

            JsonObject header = new JsonObject();
            JsonObject headerContent = new JsonObject();
            header.add("index", headerContent);


            headerContent.addProperty("_index", schema.index);
            headerContent.addProperty("_type", schema.type);


            if (schema.id.length > 0) {
                headerContent.addProperty("_id", schema.getId(jobj));
            }

            for (Map.Entry<String, String> entry: schema.insertParams.entrySet()) {
                headerContent.addProperty(entry.getKey(), entry.getValue());
            }

            buffer.append(header.toString());
            buffer.append("\n");
            buffer.append(jobj.toString());
            buffer.append("\n");
        }
        String insertString = buffer.toString();
        if (log.isDebugEnabled()) {
            log.debug(insertString);
        }
        return insertString;
    }

    public static <T> String buildBulkInsert(List<T> objects, List<Calendar> days) {
        StringBuffer buffer = new StringBuffer();

        assert (objects.size() <= days.size());

        int size = objects.size();

        for (int i = 0; i < size; i ++){
            T object = objects.get(i);
            Calendar day = days.get(i);

            Class clz = object.getClass();
            ElasticSearchSchema schema = ElasticSearchSchema.getOrBuild(clz);

            // need to read id from object
            JsonObject jobj = StorageUtil.gson.toJsonTree(object).getAsJsonObject();

            JsonObject header = new JsonObject();
            JsonObject headerContent = new JsonObject();
            header.add("index", headerContent);
            
            headerContent.addProperty("_index", schema.getWriteIndex(day));
            headerContent.addProperty("_type", schema.type);

            if (schema.id.length > 0) {
                headerContent.addProperty("_id", schema.getId(jobj));
            }

            for (Map.Entry<String, String> entry: schema.insertParams.entrySet()) {
                headerContent.addProperty(entry.getKey(), entry.getValue());
            }

            buffer.append(header.toString());
            buffer.append("\n");
            buffer.append(jobj.toString());
            buffer.append("\n");
        }
        String insertString = buffer.toString();
        if (log.isDebugEnabled()) {
            log.debug(insertString);
        }
        return insertString;
    }
}
