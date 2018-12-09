package com.tang.valhalla.mapper.template.mapping;

import com.tang.valhalla.mapper.template.ElasticTemplate;

import java.lang.reflect.Method;

public interface ElasticA2TMapping {
    ElasticTemplate apply(Method method);
}
