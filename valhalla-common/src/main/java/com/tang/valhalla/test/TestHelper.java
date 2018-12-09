package com.tang.valhalla.test;

import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


public class TestHelper {
    public static final String VERSION_5_2_2 = "5.2.2";
    public static final String VERSION_6_2_3 = "6.2.3";

    // should x.class is at least super class of y.class
    // x.getClass().isAssignableFrom(y.getClass());
    public static void unfoldObjectGetMethod(Object x, Object y, UnfoldObjMethod method) {
        if (!x.getClass().isAssignableFrom(y.getClass())) {
            return;
        }

        Method[] methods = x.getClass().getMethods();

        for (Method mex : methods) {
            if (mex.getName().startsWith("get")) {
                method.apply(x, y, mex);
            }
        }
    }

    public static EmbeddedElastic buildLocalserver(String version, String downloadDir, String installDir) {
        // build up one embedded elastic server
        EmbeddedElastic esServer = EmbeddedElastic.builder()
            .withElasticVersion(version)
            .withSetting(PopularProperties.TRANSPORT_TCP_PORT, 9300)
            .withSetting(PopularProperties.CLUSTER_NAME, "test")
            .withSetting(PopularProperties.HTTP_PORT, 9200)
            .withDownloadDirectory(new File(downloadDir))
            .withInstallationDirectory(new File(installDir))
            .withCleanInstallationDirectoryOnStop(true)
            .withStartTimeout(30, TimeUnit.SECONDS)
            .build();

        return esServer;
    }

    public static EmbeddedElastic buildLocalserver() {
        String sysOs = System.getProperty("os.name");

        if (sysOs.toLowerCase().startsWith("win")) {
            return buildLocalWindowsESServer();
        } else {
            return buildLocalPosixESServer();
        }
    }

    public static EmbeddedElastic buildLocalPosixESServer() {
        return buildLocalserver(VERSION_6_2_3, "/tmp/elastic/download", "/tmp/elastic/install");
    }

    public static EmbeddedElastic buildLocalWindowsESServer() {
        return buildLocalserver(VERSION_6_2_3, "E:\\temp\\es", "E:\\temp\\es\\install");
    }

    public static interface UnfoldObjMethod {
        void apply(Object x, Object y, Method method);
    }
}
