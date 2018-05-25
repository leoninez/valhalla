package com.ebay.valhalla.mapper.template.mapping.filter;

import com.ebay.valhalla.mapper.annotation.filter.ElasticSearchFilter;
import com.ebay.valhalla.mapper.template.filter.ElasticFilterTemplate;
import com.ebay.valhalla.mapper.template.filter.ElasticTermFilterTemplate;
import com.ebay.valhalla.type.ElasticFilterType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ElasticA2TTermFilterMapping implements ElasticA2TFilterMapping {
    @Override
    public List<ElasticFilterTemplate> apply(Method method) {
        List<ElasticFilterTemplate> list = new ArrayList<>();

        Annotation[] annotations = method.getDeclaredAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(ElasticSearchFilter.class)) {
                ElasticSearchFilter filter = (ElasticSearchFilter) annotation;

                if (filter.type() == ElasticFilterType.TERM) {
                    String[] params = filter.parameters();
                    list.add(new ElasticTermFilterTemplate(params[0], params[1]));
                }
            }
        }

        return list;
    }
}
