package com.tang.valhalla.mapper.template.mapping.filter;

import com.tang.valhalla.mapper.template.filter.ElasticFilterTemplate;

import java.lang.reflect.Method;
import java.util.List;

public interface ElasticA2TFilterMapping {
    List<ElasticFilterTemplate> apply(Method method);
}
