package com.ebay.valhalla.mapper.template.action;

import com.ebay.valhalla.api.ElasticExecutor;
import com.ebay.valhalla.mapper.template.ElasticTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticInsertTemplate implements ElasticTemplate {
    private static final Logger log = LoggerFactory.getLogger(ElasticCreateTemplate.class);

    public Class clz;

    public ElasticInsertTemplate(Class clz) {
        this.clz = clz;
    }

    @Override
    public Object apply(ElasticExecutor executor, Object[] args) {
        try {
            if (clz == null) {
                executor.insert(args[0]);
            } else {
                executor.insert(args[0]);
            }
        } catch (Throwable t) {
            log.error("Apply to ElasticCreateTemplate fails with clz = {}", this.clz, t);
            return false;
        }

        return true;
    }
}
