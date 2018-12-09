package com.tang.valhalla.mapper.template.mapping;

import com.tang.valhalla.mapper.annotation.ElasticSearchCreate;
import com.tang.valhalla.mapper.template.ElasticTemplate;
import com.tang.valhalla.mapper.template.action.ElasticCreateTemplate;

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
