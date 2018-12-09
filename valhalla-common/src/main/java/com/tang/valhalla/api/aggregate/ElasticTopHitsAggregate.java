package com.tang.valhalla.api.aggregate;

import com.tang.valhalla.type.ElasticAggregateType;

public class ElasticTopHitsAggregate extends ElasticAggregateFieldAbstract {

    private int size = 10;
    private ElasticAggregateSort.SortOrder sort;
    private String[] includeFields;

    public ElasticTopHitsAggregate(String name, String field) {
        super(name, field);
    }

    public ElasticTopHitsAggregate(String name, String field, int size, ElasticAggregateSort.SortOrder sort, String[] includeFields) {
        super(name, field);
        this.size = size;
        this.sort = sort;
        this.includeFields = includeFields;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ElasticAggregateSort.SortOrder getSort() {
        return sort;
    }

    public void setSort(ElasticAggregateSort.SortOrder sort) {
        this.sort = sort;
    }

    public String[] getIncludeFields() {
        return includeFields;
    }

    public void setIncludeFields(String[] includeFields) {
        this.includeFields = includeFields;
    }

    @Override
    public ElasticAggregateType type() {
        return ElasticAggregateType.top;
    }
}
