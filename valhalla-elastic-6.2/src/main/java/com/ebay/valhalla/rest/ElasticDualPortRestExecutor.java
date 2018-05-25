package com.ebay.valhalla.rest;


import com.ebay.valhalla.api.ElasticExecutor;
import com.ebay.valhalla.api.aggregate.ElasticAggregate;
import com.ebay.valhalla.api.alias.ElasticAliasAction;
import com.ebay.valhalla.api.filter.ElasticFilter;
import com.ebay.valhalla.api.sort.ElasticSort;
import com.ebay.valhalla.conf.ElasticClientConf;
import com.ebay.valhalla.schema.ElasticSearchSchema;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class ElasticDualPortRestExecutor implements ElasticExecutor {

    private ElasticExecutor readExecutor;
    private ElasticExecutor writeExecutor;


    public ElasticDualPortRestExecutor(ElasticClientConf conf) {
        if (conf.hostPorts.length < 1) {
            throw new RuntimeException("The elastic client host-ports configuration should at least include 1 values");
        }

        String[] readHost = new String[1];
        readHost[0] = conf.hostPorts[0];
        ElasticClientConf readConf = new ElasticClientConf();
        readConf.setHostPorts(readHost);
        readConf.schema = conf.schema;

        String[] writeHost;
        if (conf.hostPorts.length <= 1) {
            writeHost = readHost;
        } else {
            writeHost = new String[]{conf.hostPorts[1]};
        }
        ElasticClientConf writeConf = new ElasticClientConf();
        writeConf.setHostPorts(writeHost);
        writeConf.schema = conf.schema;

        if (conf.isAuth) {
            conf.dupAuthConf(readConf);
            conf.dupAuthConf(writeConf);
        }

        this.readExecutor = new ElasticRestExecutor(readConf);
        this.writeExecutor = new ElasticRestExecutor(writeConf);
    }

    @Override
    public boolean createIndex(Class clz) {
        return writeExecutor.createIndex(clz);
    }

    @Override
    public boolean createIndex(Class clz, Calendar day) {
        return writeExecutor.createIndex(clz, day);
    }

    @Override
    public boolean deleteIndex(Class clz) {
        return writeExecutor.deleteIndex(clz);
    }

    @Override
    public boolean deleteIndex(Class clz, Calendar day) {
        return writeExecutor.deleteIndex(clz, day);
    }

    @Override
    public boolean deleteIndex(String index) {
        return writeExecutor.deleteIndex(index);
    }

    @Override
    public boolean existedIndex(Class clz) {
        return readExecutor.existedIndex(clz);
    }

    @Override
    public boolean existedIndex(Class clz, Calendar day) {
       return readExecutor.existedIndex(clz, day);
    }

    @Override
    public boolean existedIndex(String index, String type) {
        return readExecutor.existedIndex(index, type);
    }

    @Override
    public long countIndex(Class clz) {
        return readExecutor.countIndex(clz);
    }

    @Override
    public boolean clearIndex(Class clz) {
        return writeExecutor.clearIndex(clz);
    }

    @Override
    public boolean existAlias(Class clz, Calendar day) {
        return readExecutor.existAlias(clz, day);
    }

    @Override
    public boolean addAlias(Class clz) {
        return writeExecutor.addAlias(clz);
    }

    @Override
    public boolean addAlias(Class clz, Calendar day) {
        return writeExecutor.addAlias(clz, day);
    }

    @Override
    public boolean removeAlias(Class clz) {

        return writeExecutor.removeAlias(clz);
    }

    @Override
    public boolean removeAlias(Class clz, Calendar day) {
        return writeExecutor.removeAlias(clz, day);
    }

    @Override
    public boolean removeAlias(String index, String alias) {
        return writeExecutor.removeAlias(index, alias);
    }

    @Override
    public String[] allAliasIndex(Class clz) {
        return readExecutor.allAliasIndex(clz);
    }

    @Override
    public boolean aliasesAction(ElasticAliasAction[] actions) {
        return writeExecutor.aliasesAction(actions);
    }

    @Override
    public boolean insertById(Object object, String id) {
        return writeExecutor.insertById(object, id);
    }

    @Override
    public boolean insertById(Object object, String id, Calendar day) {
        return writeExecutor.insertById(object, id, day);
    }


    @Override
    public boolean insertByNoId(Object object) {
        return writeExecutor.insertByNoId(object);
    }

    @Override
    public boolean insertByNoId(Object object, Calendar day) {
        return writeExecutor.insertByNoId(object, day);
    }

    @Override
    public boolean insert(Object object) {
        return writeExecutor.insert(object);
    }

    @Override
    public boolean insert(Object object, Calendar day) {
        return writeExecutor.insert(object, day);
    }


    @Override
    public boolean delete(Class clz, String id) {
        return writeExecutor.delete(clz, id);
    }

    @Override
    public boolean delete(Class clz, Calendar day, String id) {
        return writeExecutor.delete(clz, day, id);
    }

    @Override
    public <T> T get(Class<T> clz, String id) {
        return readExecutor.get(clz, id);
    }

    @Override
    public <T> T get(Class<T> clz, Calendar day, String id) {
        return readExecutor.get(clz, day, id);
    }
    @Override
    public <T> T[] getAll(Class<T[]> arrayClz) {
        return readExecutor.getAll(arrayClz);
    }

    @Override
    public JsonElement rawGet(Class clz, String id) {
        return readExecutor.rawGet(clz, id);
    }

    @Override
    public JsonElement rawGet(Class clz, Calendar day, String id) {
        return readExecutor.rawGet(clz, day, id);
    }

    @Override
    public <T> boolean bulkInsert(T[] objects) {
        return writeExecutor.bulkInsert(objects);
    }

    @Override
    public <T> boolean bulkInsert(List<T> objects) {
        return writeExecutor.bulkInsert(objects);
    }

    @Override
    public <T> boolean bulkInsert(T[] objects, Calendar[] days) {
        return writeExecutor.bulkInsert(objects, days);
    }

    @Override
    public <T> boolean bulkInsert(List<T> objects, List<Calendar> days) {
        return writeExecutor.bulkInsert(objects, days);
    }

    @Override
    public <T> T[] search(Class<T[]> arrayClz, List<ElasticFilter> filters) {
        return readExecutor.search(arrayClz, filters);
    }

    @Override
    public <T> T[] search(Class<T[]> arrayClz, ElasticFilter[] filters) {
        return readExecutor.search(arrayClz, filters);
    }

    @Override
    public JsonArray rawSearch(Class clz, ElasticFilter[] filters, int size, String[] includeFields, ElasticSort[] sorts) {
        return readExecutor.rawSearch(clz, filters, size, includeFields, sorts);
    }

    @Override
    public JsonArray rawSearch(String index, String type, ElasticFilter[] filters, int size, String[] includeFields, ElasticSort[] sorts) {
        return readExecutor.rawSearch(index, type, filters, size, includeFields, sorts);
    }

    @Override
    public <T> T[] search(Class<T[]> arrayClz, List<ElasticFilter> filters, int size) {
        return readExecutor.search(arrayClz, filters, size);
    }

    @Override
    public <T> T[] search(Class<T[]> arrayClz, ElasticFilter[] filters, int size) {
        return readExecutor.search(arrayClz, filters, size);
    }

    @Override
    public <T> T[] search(Class<T[]> arrayClz, List<ElasticFilter> filters, int size, List<ElasticSort> sorts) {
        return readExecutor.search(arrayClz, filters, size, sorts);
    }

    @Override
    public <T> T[] search(Class<T[]> arrayClz, ElasticFilter[] filters, int size, List<ElasticSort> sorts) {
        return readExecutor.search(arrayClz, filters, size, sorts);
    }

    @Override
    public <T> Object aggregate(Class<T[]> arrayClz, List<ElasticFilter> filters, ElasticAggregate[] aggregates) {
        return readExecutor.aggregate(arrayClz, filters, aggregates);
    }

    @Override
    public <T> Object aggregate(Class<T[]> arrayClz, ElasticFilter[] filters, ElasticAggregate[] aggregates) {
        return readExecutor.aggregate(arrayClz, filters, aggregates);
    }

    @Override
    public JsonElement rawAggregate(Class clz, ElasticFilter[] filters, ElasticAggregate[] aggregates, String[] includeFields) {
        return readExecutor.rawAggregate(clz, filters, aggregates, includeFields);
    }

    @Override
    public JsonElement rawAggregate(String index, String type, ElasticFilter[] filters, ElasticAggregate[] aggregates, String[] includeFields) {
        return readExecutor.rawAggregate(index, type, filters, aggregates, includeFields);
    }

    @Override
    public JsonObject raw(String method, String path, String param) {
        return readExecutor.raw(method, path, param);
    }

    @Override
    public <T> T[] scroll(Class<T[]> arrayClz, List<ElasticFilter> filters, int size, List<ElasticSort> sorts, int keepMin) {
        return readExecutor.scroll(arrayClz, filters, size, sorts, keepMin);
    }

    @Override
    public <T> T[] scroll(Class<T[]> arrayClz, ElasticFilter[] filters, int size, List<ElasticSort> sorts, int keepMin) {
        return readExecutor.scroll(arrayClz, filters, size, sorts, keepMin);
    }

    @Override
    public JsonArray rawScroll(Class arrayClz, ElasticFilter[] filters, int batchSize, String[] includeFiles, ElasticSort[] sorts, int keepMin) {
        return readExecutor.rawScroll(arrayClz, filters, batchSize, includeFiles, sorts, keepMin);
    }

    @Override
    public JsonArray rawScroll(String index, String type, ElasticFilter[] filters, int batchSize, String[] includeFiles, ElasticSort[] sorts, int keepMin) {
        return readExecutor.rawScroll(index, type, filters, batchSize, includeFiles, sorts, keepMin);
    }

    @Override
    public void close() throws IOException {
        this.readExecutor.close();
        this.writeExecutor.close();
    }
}
