package com.ebay.valhalla.mapper.template;

import com.ebay.valhalla.api.ElasticExecutor;

public interface ElasticTemplate {
    Object apply(ElasticExecutor executor, Object[] args);

    // @TODO should be append Objec.. parameters in the apply function to avert createIndex action every function call
    // Object apply(NElasticTransportClient executor, Object object ...);
}
