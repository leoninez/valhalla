package com.ebay.valhalla.rest.aggregation;

import com.ebay.valhalla.api.aggregate.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

public class ElasitcRestAggregateParser {
    public static JsonObject parse(ElasticAggregate[] aggregates) {
        return parse(Arrays.asList(aggregates));
    }

    public static JsonObject parse(List<ElasticAggregate> aggregates) {
        JsonObject aggObj = new JsonObject();

        for (ElasticAggregate aggregate : aggregates) {

            JsonObject aggItemObj = null;

            switch (aggregate.type()) {
                case max:
                case min:
                case sum:
                case avg:
                    aggItemObj = buildStat((ElasticStatAggregate) aggregate);
                    break;

                case terms:
                    aggItemObj = buildTerms((ElasticTermsAggregate) aggregate);
                    break;

                case top:
                    aggItemObj = buildTopHists((ElasticTopHitsAggregate) aggregate);
                    break;

                case nested:
                    aggItemObj = buildNested((ElasticNestedAggregate) aggregate);
                    break;

                case histogram:
                    aggItemObj = buildHistogram((ElasticHistoAggregate) aggregate);
                    break;

            }

            if (aggregate.subs() != null && aggregate.subs().size() > 0) {
                aggItemObj.add("aggs", parse(aggregate.subs()));
            }

            aggObj.add(aggregate.name(), aggItemObj);
        }

        return aggObj;
    }

    private static JsonObject buildStat(ElasticStatAggregate aggregate) {
        JsonObject aggActionObj = new JsonObject();
        aggActionObj.addProperty("field", aggregate.getField());

        JsonObject nameObj = new JsonObject();
        nameObj.add(aggregate.type().name(), aggActionObj);


        return nameObj;
    }

    private static JsonObject buildTerms(ElasticTermsAggregate aggregate) {
        JsonObject aggActionObj = new JsonObject();
        aggActionObj.addProperty("field", aggregate.getField());

        //@TODO should add size property
//        aggActionObj.addProperty("size", aggregate.getSize());

        JsonObject nameObj = new JsonObject();
        nameObj.add(aggregate.type().name(), aggActionObj);

        return nameObj;
    }

    private static JsonObject buildTopHists(ElasticTopHitsAggregate aggregate) {
        JsonObject orderObj = new JsonObject();
//        orderObj.addProperty("order", aggregate.order().name());

        JsonObject fieldObj = new JsonObject();
        fieldObj.add(aggregate.getField(), orderObj);

        JsonArray sortList = new JsonArray();
        sortList.add(fieldObj);

        JsonObject topHits = new JsonObject();
        topHits.addProperty("size", aggregate.getSize());
        topHits.add("sort", sortList);

        if (aggregate.getIncludeFields() != null) {
            JsonArray fieldList = new JsonArray();

            for (String ifield : aggregate.getIncludeFields()) {
                fieldList.add(ifield);
            }
            topHits.add("_source", fieldList);
        }

        JsonObject nameObj = new JsonObject();
        nameObj.add("top_hits", topHits);

        return nameObj;
    }

    private static JsonObject buildNested(ElasticNestedAggregate aggregate) {
        JsonObject aggActionObj = new JsonObject();
        aggActionObj.addProperty("path", aggregate.getField());

        JsonObject nameObj = new JsonObject();
        nameObj.add(aggregate.type().name(), aggActionObj);

        return nameObj;
    }

    private static JsonObject buildHistogram(ElasticHistoAggregate aggregate) {
        JsonObject aggActionObj = new JsonObject();
        aggActionObj.addProperty("field", aggregate.getField());
        aggActionObj.addProperty("interval", aggregate.getInterval());

        JsonObject nameObj = new JsonObject();
        nameObj.add(aggregate.type().name(), aggActionObj);

        return nameObj;
    }
}
