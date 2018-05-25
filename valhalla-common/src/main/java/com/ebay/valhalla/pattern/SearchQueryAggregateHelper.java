package com.ebay.valhalla.pattern;


import com.ebay.valhalla.api.aggregate.*;
import com.ebay.valhalla.pattern.g4.SearchQueryParser;
import com.ebay.valhalla.type.ElasticAggregateType;

public class SearchQueryAggregateHelper {
    public static ElasticAggregate buildTermAggregate(SearchQueryParser.Aggregator_term_itemContext term_itemContext) {
        String targetKey = term_itemContext.KEY().getText();
        return new ElasticTermsAggregate(targetKey, targetKey);
    }

    public static ElasticAggregate buildStatAggregate(SearchQueryParser.Aggregator_stat_itemContext stat_itemContext) {
        String targetKey = stat_itemContext.KEY().getText();
        String action = stat_itemContext.aggregator_stat_action().getText();

        return new ElasticStatAggregate(targetKey, targetKey, ElasticAggregateType.valueOf(action));
    }

    public static ElasticAggregate buildTopAggregate(SearchQueryParser.Aggregator_top_itemContext top_itemContext, SearchQueryOutput output) {
        String targetKey = top_itemContext.KEY().getText();
        int size = Integer.parseInt(top_itemContext.NUM().getText());
        SearchQueryParser.Aggregator_top_orderContext orderContext = top_itemContext.aggregator_top_order();
        String order = "desc";
        if (orderContext != null) {
            order = orderContext.getText();
        }

        return new ElasticTopHitsAggregate(targetKey, targetKey, size,
            ElasticAggregateSort.SortOrder.valueOf(order), output.targetFields);
    }

    public static ElasticAggregate buildNestedAggregate(SearchQueryParser.Aggregator_nested_itemContext itemContext) {
        String targetKey = itemContext.KEY().getText();
        return new ElasticNestedAggregate(targetKey, targetKey);
    }

    public static ElasticAggregate buildHistoAggregate(SearchQueryParser.Aggregator_histo_itemContext itemContext) {
        String targetKey = itemContext.KEY().getText();
        int interval = Integer.parseInt(itemContext.NUM().getText());
        return new ElasticHistoAggregate(targetKey, targetKey, interval);
    }
}
