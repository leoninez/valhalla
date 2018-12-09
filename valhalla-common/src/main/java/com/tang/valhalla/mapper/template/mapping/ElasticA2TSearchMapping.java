package com.tang.valhalla.mapper.template.mapping;

import com.tang.valhalla.mapper.annotation.ElasticSearchSearch;
import com.tang.valhalla.mapper.template.ElasticTemplate;
import com.tang.valhalla.mapper.template.action.ElasticSearchTemplate;
import com.tang.valhalla.mapper.template.mapping.filter.ElasticA2TFilterPipelineMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class ElasticA2TSearchMapping implements ElasticA2TMapping {

    private static final Logger log = LoggerFactory.getLogger(ElasticA2TSearchMapping.class);

    @Override
    public ElasticTemplate apply(Method method) {
        ElasticSearchSearch searchAnno = method.getAnnotation(ElasticSearchSearch.class);

        if (searchAnno == null) {
            log.info("{} : There is no ElasticSearchSearch annotation attached, ignore mapping", method);
            return null;
        }

        Class clz = searchAnno.clz();

        if (clz.equals(Object.class)) {
            log.error("{} : specified class type in ElasticSearchSearch annotation should not be Object.class");
            return null;
        }

        return new ElasticSearchTemplate(ElasticA2TFilterPipelineMapping.builtin().apply(method), clz);
    }
}
