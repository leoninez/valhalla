package com.tang.valhalla.service.rest;

import com.tang.valhalla.conf.ElasticClientConf;
import com.tang.valhalla.webservice.server.WebServer;
import com.tang.valhalla.webservice.server.WebServerConf;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class BanelingWebServer {
    public static void main(String[] args) {

        WebServerConf serverConf = new WebServerConf();

        // build es config for connection
        ElasticClientConf elasticClientConf = new ElasticClientConf();
        Config setting = ConfigFactory.load("es_setting.conf");
        String esServer = setting.getString("elasticServer");
        elasticClientConf.hostPorts = new String[]{ esServer };
        serverConf.esConf = elasticClientConf;

        serverConf.executorClz = "com.tang.valhalla.rest.ElasticRestExecutor";
        serverConf.uri = UriBuilder.fromUri(URI.create(setting.getString("connectionURI"))).build();

        WebServer webServer = new WebServer("BanelingWebService", serverConf);
        webServer.startWithGrizzlyServlet();

//        WebappContext webappContext = new WebappContext("JPM Web Service", "");
//
//        ResourceConfig jpmServiceConfig = new WebServiceResourceConfig(serverConf, "JPM");
//        webappContext.addServlet("JPM-API", new ServletContainer(jpmServiceConfig)).addMapping("/api/*");
//
//        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(serverConf.uri);
//        webappContext.deploy(server);
//
//        try {
//            server.start();
//            log.info("Start JPM WebServer @ {}", serverConf.uri);
//        } catch (IOException e) {
//            log.error("Unable to Start JPM WebServer @ {}", serverConf.uri, e);
//        }
    }
}
