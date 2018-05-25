package com.ebay.valhalla.mapper.template.mapping;

import com.ebay.valhalla.mapper.annotation.ElasticSearchCreate;
import com.ebay.valhalla.mapper.template.ElasticTemplate;
import com.ebay.valhalla.mapper.template.action.ElasticCreateTemplate;

import java.lang.reflect.Method;

public class ElasticA2TCreateMapping implements ElasticA2TMapping {
    @Override
    public ElasticTemplate apply(Method method) {
        ElasticSearchCreate createAnno = method.getAnnotation(ElasticSearchCreate.class);

        if (createAnno != null) {
            return new ElasticCreateTemplate(createAnno.clz());
        }

        return null;
    }
}
