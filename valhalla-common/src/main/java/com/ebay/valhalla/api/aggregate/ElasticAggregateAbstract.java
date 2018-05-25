package com.ebay.valhalla.api.aggregate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ElasticAggregateAbstract implements ElasticAggregate {
    private String name;
    private List<ElasticAggregate> subs = new ArrayList<>();
    private ElasticAggregateOrder order;

    public ElasticAggregateAbstract(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public List<ElasticAggregate> subs() {
        return subs;
    }

    @Override
    public ElasticAggregateOrder order() {
        return order;
    }

    @Override
    public void addSub(ElasticAggregate sub) {
        subs.add(sub);
    }

    @Override
    public void addSubs(List<ElasticAggregate> subs) {
        this.subs.addAll(subs);
    }

    @Override
    public void withOrder(ElasticAggregateOrder order) {
        this.order = order;
    }
}
