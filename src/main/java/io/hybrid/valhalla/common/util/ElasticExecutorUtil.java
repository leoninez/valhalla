package io.hybrid.valhalla.common.util;

import io.hybrid.valhalla.rest.ElasticRawRestExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ElasticExecutorUtil {

    private static final Logger log = LoggerFactory.getLogger(ElasticExecutorUtil.class);
    private final static SimpleDateFormat dateKeyFormat = new SimpleDateFormat(
        "yyyy-MM-dd_HH:mm:ss");
    public static void cleanAll(ElasticRawRestExecutor executor, Class[] clzs) {
        // clean up old data
        for (Class clz : clzs) {
            if (executor.existedIndex(clz)) {
                executor.deleteIndex(clz);
            }
        }
    }
    public static void clearAll(ElasticRawRestExecutor executor, Class[] clzs) {
        ensureAllMissingTables(executor, clzs);

        // clean up old data
        for (Class clz : clzs) {
            if (executor.existedIndex(clz)) {
                executor.clearIndex(clz);
            }
        }
    }
    public static void ensureAllMissingTables(ElasticRawRestExecutor executor, Class[] clzs) {
        // clean up old data
        for (Class clz : clzs) {
            if (!executor.existedIndex(clz)) {
                executor.createIndex(clz);
            }
        }
    }
    public static void ensureAllMissingTablesWithDay(ElasticRawRestExecutor executor, Class[] clzs, Calendar day) {
        for (Class clz : clzs) {
            if (executor.existedIndex(clz, day)) {
                executor.removeAlias(clz, day);
                executor.deleteIndex(clz, day);
            }
            // test for year - MRJobEntity
            executor.createIndex(clz, day);
            executor.addAlias(clz, day);
        }
    }
    public static void ensureAllMissingTableWithDay(ElasticRawRestExecutor executor, Class clz, Calendar day) {
        if (executor.existedIndex(clz, day)) {
            executor.removeAlias(clz, day);
            executor.deleteIndex(clz, day);
        }
        // test for year - MRJobEntity
        executor.createIndex(clz, day);
        executor.addAlias(clz, day);
    }
    public static long toTimestamp(String dateKey) {
        try {
            return dateKeyFormat.parse(dateKey).getTime();
        } catch (ParseException e) {
            return -1;
        }
    }

}
