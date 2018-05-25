package com.ebay.valhalla.api.aggregate;

import com.ebay.valhalla.type.ElasticAggregateType;

import java.util.List;

public interface ElasticAggregate {
    String name();
    ElasticAggregateType type();
    List<ElasticAggregate> subs();
    ElasticAggregateOrder order();

    void addSub(ElasticAggregate sub);
    void addSubs(List<ElasticAggregate> subs);
    void withOrder(ElasticAggregateOrder order);
}
