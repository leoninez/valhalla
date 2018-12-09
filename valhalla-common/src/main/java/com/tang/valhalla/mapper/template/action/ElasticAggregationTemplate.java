package com.tang.valhalla.mapper.template.action;

import com.tang.valhalla.api.ElasticExecutor;
import com.tang.valhalla.api.aggregate.ElasticAggregate;
import com.tang.valhalla.api.filter.ElasticFilter;
import com.tang.valhalla.mapper.template.ElasticTemplate;
import com.tang.valhalla.mapper.template.aggregate.ElasticAggregateTemplate;
import com.tang.valhalla.mapper.template.filter.ElasticFilterTemplate;

import java.util.ArrayList;
import java.util.List;

public class ElasticAggregationTemplate implements ElasticTemplate {
    public List<ElasticFilterTemplate> filterTemplates;
    public List<ElasticAggregateTemplate> aggregateTemplates;
    public Class arrayClz;

    public ElasticAggregationTemplate(List<ElasticFilterTemplate> filterTemplates,
                                      List<ElasticAggregateTemplate> aggregateTemplates,
                                      Class arrayClz) {
        this.filterTemplates = filterTemplates;
        this.aggregateTemplates = aggregateTemplates;
        this.arrayClz = arrayClz;
    }

    @Override
    public Object apply(ElasticExecutor executor, Object[] args) {
        List<ElasticFilter> filters = new ArrayList<>();

        for (ElasticFilterTemplate filterTemplate : filterTemplates) {
            filters.add(filterTemplate.apply(executor, args));
        }

        List<ElasticAggregate> aggregates = new ArrayList<>();

        for (ElasticAggregateTemplate aggregateTemplate : aggregateTemplates) {
            aggregates.add(aggregateTemplate.apply(executor, args));
        }

        //return executor.aggregate(arrayClz, filters, aggregates);
        //@TODO refine aggregate API
        return null;
    }
}
