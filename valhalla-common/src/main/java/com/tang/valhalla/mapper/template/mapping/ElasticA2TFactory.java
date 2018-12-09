package com.tang.valhalla.mapper.template.mapping;

import com.tang.valhalla.mapper.template.mapping.aggregate.ElasticA2TAggregateMapping;
import com.tang.valhalla.mapper.template.mapping.filter.ElasticA2TFilterMapping;

public class ElasticA2TFactory {
    public static final ElasticA2TMapping[] builtinActionMappings = new ElasticA2TMapping[]{
        new ElasticA2TCreateMapping(),
        new ElasticA2TInsertMapping(),
        new ElasticA2TBulkInsertMapping(),
        new ElasticA2TSearchMapping(),
        new ElasticA2TAggregationMapping()
    };

    public static final ElasticA2TFilterMapping[] builtinFilterMappings =
        new ElasticA2TFilterMapping[]{

        };

    public static final ElasticA2TAggregateMapping[] builtinAggregateMappings =
        new ElasticA2TAggregateMapping[]{

        };

}
