package com.ebay.valhalla.webservice.server;

import com.ebay.valhalla.conf.ElasticClientConf;
import com.typesafe.config.Config;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class WebServerConf {
    public URI uri;
    public ElasticClientConf esConf;
    public String executorClz;

    public static WebServerConf loadFromConf(Config webServiceConfig, Config appConfig) {
        WebServerConf conf = new WebServerConf();
        conf.uri = UriBuilder.fromUri(URI.create(webServiceConfig.getString("connectionURI"))).build();
        conf.esConf = ElasticClientConf.loadFromConf(appConfig);
        conf.executorClz = appConfig.getString("elasticsearch.executor");

        return conf;
    }
}
