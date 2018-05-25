package com.ebay.valhalla.webservice.resource;

import com.ebay.valhalla.webservice.binder.CORSFilter;
import com.ebay.valhalla.webservice.binder.WebServiceBinder;
import com.ebay.valhalla.webservice.exception.ThrowableMapper;
import com.ebay.valhalla.webservice.handler.ElasticRestHandler;
import com.ebay.valhalla.webservice.server.WebServerConf;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.EncodingFilter;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.ApplicationPath;

@WebServlet(loadOnStartup = 1, urlPatterns = "/api/*")
@ApplicationPath("/api")
public class WebServiceResourceConfig extends ResourceConfig {
    public WebServerConf conf;

    public WebServiceResourceConfig(WebServerConf conf, String appName) {
        this.conf = conf;

        this.register(CORSFilter.class);
        this.register(new WebServiceBinder(this.conf));
        this.register(ThrowableMapper.class);
        this.register(ElasticRestHandler.class);

        register(EntityFilteringFeature.class);
        EncodingFilter.enableFor(this, GZipEncoder.class);

        this.setApplicationName(appName);
    }
}
