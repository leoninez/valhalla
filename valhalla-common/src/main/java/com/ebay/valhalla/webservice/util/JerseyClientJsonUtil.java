package com.ebay.valhalla.webservice.util;

import com.ebay.valhalla.util.StorageUtil;
import com.google.gson.JsonObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class JerseyClientJsonUtil {
    public static JsonObject fetchJson(Client client, String targetUrl) {
        Response response = client.target(targetUrl)
            .request(MediaType.APPLICATION_JSON).get();

        if (response.getStatusInfo().toEnum() != Response.Status.OK) {
            return null;
        }

        String resStr = response.readEntity(String.class);
        return StorageUtil.jsonParser.parse(resStr).getAsJsonObject();
    }
}
