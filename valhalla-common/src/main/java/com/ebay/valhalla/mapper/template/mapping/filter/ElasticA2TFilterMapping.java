package com.ebay.valhalla.mapper.template.mapping.filter;

import com.ebay.valhalla.mapper.template.filter.ElasticFilterTemplate;

import java.lang.reflect.Method;
import java.util.List;

public interface ElasticA2TFilterMapping {
    List<ElasticFilterTemplate> apply(Method method);
}
