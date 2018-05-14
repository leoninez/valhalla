package io.hybrid.valhalla.common.conf;

import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by kevinlynna on 21/01/2018.
 */
public class ElasticClientConf {
    public static final String HOSTS_LIST = "elasticsearch.hosts";
    public static final String CLUSTER_NAME = "elasticsearch.cluster";
    public static final String SCHEMA_NAME = "elasticsearch.schema";
    public static final String AUTH_ENABLED = "elasticsearch.auth.enabled";
    public static final String AUTH_USER = "elasticsearch.auth.user";
    public static final String AUTH_PASSWORD = "elasticsearch.auth.password";
    private static final Logger log = LoggerFactory.getLogger(ElasticClientConf.class);
    public String cluster;
    public String[] hostPorts;
    public boolean isAuth = false;
    public String user;
    public String password;
    public String schema = "http";

    public ElasticClientConf() {
    }

    public ElasticClientConf(String cluster, String[] hostPorts) {
        this.cluster = cluster;
        this.hostPorts = hostPorts;
    }
    public static ElasticClientConf loadFromConf(Map<String, String> config) {
        ElasticClientConf conf = new ElasticClientConf();

        // parse hosts
        conf.hostPorts = config.get(HOSTS_LIST).split(",");
        conf.cluster = config.get(CLUSTER_NAME);
        if (config.containsKey(SCHEMA_NAME)) {
            conf.schema = config.get(SCHEMA_NAME);
        }

        if (config.containsKey(AUTH_ENABLED) && "true".equals(config.get(AUTH_ENABLED))) {
            if (config.containsKey(AUTH_USER) && config.containsKey(AUTH_PASSWORD)) {
                conf.withAuth(config.get(AUTH_USER), config.get(AUTH_PASSWORD));
            } else {
                log.error("Invalid or Missed Auth Information with key {} and {}", AUTH_USER, AUTH_PASSWORD);
            }
        }

        return conf;
    }
    public static ElasticClientConf loadFromConf(Config config) {
        ElasticClientConf conf = new ElasticClientConf();

        // parse hosts
        List<String> hosts = config.getStringList(HOSTS_LIST);

        conf.hostPorts = new String[hosts.size()];

        for (int i = 0; i < conf.hostPorts.length; i++) {
            conf.hostPorts[i] = hosts.get(i);
        }

        conf.cluster = config.getString(CLUSTER_NAME);
        if (config.hasPath(SCHEMA_NAME)) {
            conf.schema = config.getString(SCHEMA_NAME);
        }

        if (config.hasPath(AUTH_ENABLED) && config.getBoolean(AUTH_ENABLED)) {
            if (config.hasPath(AUTH_USER) && config.hasPath(AUTH_PASSWORD)) {
                conf.withAuth(config.getString(AUTH_USER), config.getString(AUTH_PASSWORD));
            } else {
                log.error("Invalid or Missed Auth Information with key {} and {}", AUTH_USER, AUTH_PASSWORD);
            }
        }

        return conf;
    }
    public ElasticClientConf withAuth(String user, String password) {
        isAuth = true;
        this.user = user;
        this.password = password;

        return this;
    }
    public ElasticClientConf disableAuth() {
        isAuth = false;
        return this;
    }
    public void commitToConf(Map<String, String> config) {

        config.put(HOSTS_LIST, String.join(",", this.hostPorts));
        config.put(CLUSTER_NAME, this.cluster);
        config.put(SCHEMA_NAME, this.schema);

        if (this.isAuth) {
            config.put(AUTH_ENABLED, "true");
            config.put(AUTH_USER, this.user);
            config.put(AUTH_PASSWORD, this.password);
        }
    }
    public void dupAuthConf(ElasticClientConf conf) {
        conf.isAuth = this.isAuth;
        conf.user = this.user;
        conf.password = this.password;
    }
    public ElasticClientConf setCluster(String cluster) {
        this.cluster = cluster;

        return this;
    }

    public ElasticClientConf setHostPorts(String[] hostPorts) {
        this.hostPorts = hostPorts;

        return this;
    }

    public boolean isValid() {
        return hostPorts != null;
    }
}
