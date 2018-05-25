package com.ebay.valhalla.webservice.handler;

import com.ebay.valhalla.api.ElasticExecutor;
import com.ebay.valhalla.api.aggregate.ElasticAggregate;
import com.ebay.valhalla.pattern.SearchQueryOutput;
import com.ebay.valhalla.schema.ElasticSearchSchema;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/elastic")
public class ElasticRestHandler {

    private static final Logger log = LoggerFactory.getLogger(ElasticRestHandler.class);
    private static final String version = "0.0.1";

    @Inject
    private ElasticExecutor executor;

    @GET
    @Path("/info")
    @Produces("text/plain")
    public String getBasicInfo() {
        return executor == null ? "null" : executor.toString();
    }


    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public String fetchClusterFullStatistic(@QueryParam("query") String query,
                                            @DefaultValue("-1") @QueryParam("size") int size) {

        SearchQueryOutput output = SearchQueryOutput.fromString(query);

        if (size < 0) {
            size = output.maxSize;
        }

        if (size < 0) {
            size = 10000;
        }

        // @TODO remove support for clz
        if (output.aggregates == null || output.aggregates.length == 0) {
            JsonArray result = null;

            if (size != 0) {
                result = executor.rawSearch(output.clzName, "data", output.filters, size, output.targetFields, output.sorts);
            } else {
                result = executor.rawScroll(output.clzName, "data", output.filters, size, output.targetFields, output.sorts, 1);
            }
            return wrapResult(result).toString();
        } else {
            JsonElement result = executor.rawAggregate(output.clzName, "data", output.filters,output.aggregates, output.targetFields);
            return wrapResult(result).toString();
        }

    }

    private JsonObject wrapResult(JsonElement element) {
        JsonObject result = new JsonObject();

        if (element == null) {
            result.addProperty("success", false);
            result.addProperty("version", version);

            return result;
        }

        result.addProperty("success", true);
        result.addProperty("version", version);

        if (element.isJsonArray()) {
            result.add("result", element);
        } else {
            JsonArray er = new JsonArray();
            er.add(element);
            result.add("result", er);
        }

        return result;
    }


    private Class findClz(String clzName) {
        Class clz = null;

        // find out target clz, first try short name test
        ElasticSearchSchema schema = ElasticSearchSchema.fromShortClassName(clzName);

        if (schema != null) {
            return schema.clz;
        }

        // then try full name scan
        try {
            return Class.forName(clzName);
        } catch (ClassNotFoundException e) {
            log.debug("Unable to find Class for name : {}", clzName, e);
        }

        return null;
    }
}
