package com.tang.valhalla.util;

import org.apache.http.HttpHost;

public class ConfUtil {
    public static HttpHost parseHttpHost(String hostPort, String schema) {
        String[] splits = hostPort.split(":");
        return new HttpHost(splits[0], Integer.parseInt(splits[1]), schema);
    }

    public static HttpHost[] parseHttpHosts(String[] hostPorts, String schema) {
        HttpHost[] httpHosts = new HttpHost[hostPorts.length];

        for (int i = 0; i < hostPorts.length; i++) {
            httpHosts[i] = parseHttpHost(hostPorts[i], schema);
        }

        return httpHosts;
    }
}
