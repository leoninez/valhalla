package com.tang.valhalla.webservice.binder;

import com.tang.valhalla.conf.ElasticClientConf;
import com.tang.valhalla.webservice.server.WebServerConf;
import com.tang.valhalla.api.ElasticExecutor;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.internal.inject.Bindings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

public class WebServiceBinder extends AbstractBinder {
    private static final Logger log = LoggerFactory.getLogger(WebServiceBinder.class);

    private WebServerConf conf;

    public WebServiceBinder(WebServerConf conf) {
        this.conf = conf;
    }

    @Override
    protected void configure() {
        try {
            Class<?> clz = Class.forName(conf.executorClz);

            ElasticExecutor executor = fromClz(clz, conf.esConf);

            assert executor != null;

            bind(Bindings.service(executor)).to(ElasticExecutor.class);
            bind(Bindings.service(conf)).to(WebServerConf.class);

        } catch (Throwable t) {
            log.error("Unable to init ElasticExecutor", t);
        }
    }

    private ElasticExecutor fromClz(Class<?> clz, ElasticClientConf conf) {
        try {
            Constructor<ElasticExecutor> constructor = (Constructor<ElasticExecutor>) clz.getConstructor(ElasticClientConf.class);

            return constructor.newInstance(conf);
        } catch (Throwable t) {
           log.error("Unable to init ElasticExecutor : {}", clz.getCanonicalName());
        }
        return null;
    }
}
