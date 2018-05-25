package com.ebay.valhalla.webservice.server;

import com.ebay.valhalla.conf.ElasticClientConf;
import com.ebay.valhalla.webservice.resource.WebServiceResourceConfig;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.netty.channel.Channel;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.servlet.WebappContext;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class WebServer {
    private static final Logger log = LoggerFactory.getLogger(WebServer.class);

    private String appName;
    private WebServerConf serverConf;

    public WebServer(String appName, WebServerConf serverConf) {
        this.appName = appName;
        this.serverConf = serverConf;
    }

    public void startWithNetty() throws InterruptedException {

        ResourceConfig serviceConfig = new WebServiceResourceConfig(serverConf, this.appName);
        final Channel server = NettyHttpContainerProvider.createHttp2Server(serverConf.uri, serviceConfig, null);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> server.close()));

        Thread.currentThread().join();
    }

    public void startWithGrizzlyServlet() {
        WebappContext webappContext = new WebappContext(appName, "");

        ResourceConfig serviceConfig = new WebServiceResourceConfig(serverConf, this.appName);
        webappContext.addServlet(this.appName, new ServletContainer(serviceConfig)).addMapping("/api/*");

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(serverConf.uri);
        webappContext.deploy(server);

        try {
            server.start();
            log.info("Start {} WebServer @ {}", appName, serverConf.uri);
        } catch (IOException e) {
            log.error("Unable to Start {} WebServer @ {}", appName, serverConf.uri, e);
        }
    }

    public static void main(String[] args) {
        Config setting = null;
        if (args.length >= 1) {
            setting = ConfigFactory.parseFile(new File(args[0]));
        } else {
            setting = ConfigFactory.load("Setting.conf");
        }

        WebServerConf serverConf = new WebServerConf();

        // build es config for connection
        ElasticClientConf elasticClientConf = new ElasticClientConf();
        String esServer = setting.getString("elasticServer");
        elasticClientConf.hostPorts = new String[]{ esServer };
        serverConf.esConf = elasticClientConf;

        serverConf.executorClz = "com.ebay.valhalla.rest.ElasticRestExecutor";
        serverConf.uri = UriBuilder.fromUri(URI.create(setting.getString("connectionURI"))).build();

        WebServer webServer = new WebServer("BanelingWebService", serverConf);
        webServer.startWithGrizzlyServlet();
    }
}
