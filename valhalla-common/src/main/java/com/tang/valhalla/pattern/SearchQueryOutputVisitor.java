package com.tang.valhalla.pattern;


import com.tang.valhalla.api.aggregate.ElasticAggregate;
import com.tang.valhalla.api.filter.ElasticFilter;
import com.tang.valhalla.api.filter.ElasticLongRangeFilter;
import com.tang.valhalla.api.filter.ElasticMatchFilter;
import com.tang.valhalla.api.filter.ElasticTermFilter;
import com.tang.valhalla.api.sort.ElasticFieldSort;
import com.tang.valhalla.api.sort.ElasticSort;
import com.tang.valhalla.pattern.g4.SearchQueryBaseVisitor;
import com.tang.valhalla.pattern.g4.SearchQueryParser;
import com.tang.valhalla.type.ElasticSortOrder;
import com.tang.valhalla.util.ElasticExecutorUtil;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class SearchQueryOutputVisitor extends SearchQueryBaseVisitor<SearchQueryOutput> {

    @Override
    public SearchQueryOutput visitQuery(SearchQueryParser.QueryContext ctx) {
        SearchQueryOutput output = new SearchQueryOutput();

        String clzName = ctx.clzName().getText();
        output.clzName = clzName;

        SearchQueryParser.FilterContext filterContext = ctx.filter();
        output.filters = buildElasticFilters(filterContext);

        SearchQueryParser.SortContext sortContext = ctx.sort();
        output.sorts = buildElasticSorts(sortContext);

        SearchQueryParser.SelectorContext selectorContext = ctx.selector();
        output.targetFields = buildIncludeFields(selectorContext);

        SearchQueryParser.AggregatorContext aggregatorContext = ctx.aggregator();
        output.aggregates = buildElasticAggregates(aggregatorContext, output);

        // parse size information
        SearchQueryParser.Search_max_sizeContext max_sizeContext = ctx.search_max_size();
        int size = 10;
        if (max_sizeContext != null) {
            size = Integer.parseInt(max_sizeContext.NUM().getText());
        }
        output.maxSize = size;

        return output;
    }

    private ElasticSort[] buildElasticSorts(SearchQueryParser.SortContext sortContext) {
        if (sortContext == null) {
            return null;
        }

        SearchQueryParser.Sort_listContext listContext = sortContext.sort_list();

        if (listContext == null) {
            return null;
        }

        List<ElasticSort> list = new ArrayList<>();

        while (true) {
            SearchQueryParser.Sort_itemContext itemContext = listContext.sort_item();

            String key = itemContext.KEY().getText();
            SearchQueryParser.Sort_item_orderContext orderContext = itemContext.sort_item_order();

            if (orderContext == null || ">".equals(orderContext.getText())) {
                list.add(new ElasticFieldSort(key, ElasticSortOrder.desc));
            } else {
                list.add(new ElasticFieldSort(key, ElasticSortOrder.asc));
            }

            listContext = listContext.sort_list();

            if (listContext == null) {
                break;
            }
        }

        ElasticSort[] sorts = new ElasticSort[list.size()];


        for (int i = 0; i < sorts.length; i++) {
            sorts[i] = list.get(i);
        }

        return sorts;
    }

    private ElasticFilter[] buildElasticFilters(SearchQueryParser.FilterContext filterContext) {
        if (filterContext == null) {
            return null;
        }

        SearchQueryParser.Filter_listContext listContext = filterContext.filter_list();

        if (listContext == null) {
            return null;
        }

        List<ElasticFilter> list = new ArrayList<>();

        while (true) {
            SearchQueryParser.Filter_itemContext itemContext = listContext.filter_item();

            SearchQueryParser.Filter_item_equalContext equalContext = itemContext.filter_item_equal();
            SearchQueryParser.Filter_match_equalContext matchContext = itemContext.filter_match_equal();
            SearchQueryParser.Filter_item_rangeContext rangeContext = itemContext.filter_item_range();
            SearchQueryParser.Filter_item_time_rangeContext time_rangeContext = itemContext.filter_item_time_range();
            SearchQueryParser.Filter_item_compareContext compareContext = itemContext.filter_item_compare();

            if (equalContext != null) {
                String value = equalContext.value().getText();
                TerminalNode numNode = equalContext.value().NUM();
                TerminalNode strNode = equalContext.value().STR();
                TerminalNode boolNode = equalContext.value().BOOL();
                TerminalNode chinNode = equalContext.value().CHIN_STR();

                if (strNode != null || chinNode != null) {
                    list.add(new ElasticTermFilter(equalContext.KEY().getText(),
                        value.substring(1, value.length() - 1)));
                } else if (numNode != null) {
                    list.add(new ElasticTermFilter(equalContext.KEY().getText(), Long.parseLong(value)));
                } else if (boolNode != null) {
                    list.add(new ElasticTermFilter(equalContext.KEY().getText(), value.equals("true")));
                } else {
                    // should not reach here...
                }
            }
            if (matchContext != null) {
                String value = matchContext.value().getText();
                TerminalNode numNode = matchContext.value().NUM();
                TerminalNode strNode = matchContext.value().STR();
                TerminalNode boolNode = matchContext.value().BOOL();
                TerminalNode chinNode = matchContext.value().CHIN_STR();

                if (strNode != null || chinNode != null) {
                    list.add(new ElasticMatchFilter(matchContext.KEY().getText(),
                        value.substring(1, value.length() - 1)));
                } else if (numNode != null) {
                    list.add(new ElasticMatchFilter(matchContext.KEY().getText(), Long.parseLong(value)));
                } else if (boolNode != null) {
                    list.add(new ElasticMatchFilter(matchContext.KEY().getText(), value.equals("true")));
                } else {
                    // should not reach here...
                }
            } else if (rangeContext != null) {
                long value1 = Long.parseLong(rangeContext.NUM(0).getText());
                long value2 = Long.parseLong(rangeContext.NUM(1).getText());

                list.add(new ElasticLongRangeFilter(rangeContext.KEY().getText(), value1, value2));
            } else if (compareContext != null) {
                long value1 = Long.parseLong(compareContext.NUM().getText());

                String op = compareContext.sort_item_order().getText();

                list.add(new ElasticLongRangeFilter(compareContext.KEY().getText(), value1,
                    op.equals(">") ? ElasticLongRangeFilter.CompareOP.LARGER : ElasticLongRangeFilter.CompareOP.SMALLER));
            } else {
                long value1 = ElasticExecutorUtil.toTimestamp(time_rangeContext.DATEKEY(0).getText());
                long value2 = ElasticExecutorUtil.toTimestamp(time_rangeContext.DATEKEY(0).getText());

                list.add(new ElasticLongRangeFilter(time_rangeContext.KEY().getText(), value1, value2));
            }

            listContext = listContext.filter_list();

            if (listContext == null) {
                break;
            }
        }

        ElasticFilter[] filters = new ElasticFilter[list.size()];


        for (int i = 0; i < filters.length; i++) {
            filters[i] = list.get(i);
        }

        return filters;
    }

    private String[] buildIncludeFields(SearchQueryParser.SelectorContext selectorContext) {
        if (selectorContext == null) {
            return null;
        }

        SearchQueryParser.Selector_listContext listContext = selectorContext.selector_list();

        if (listContext == null) {
            return null;
        }


        List<String> list = new ArrayList<>();

        while (true) {
            list.add(listContext.selector_item().KEY().getText());

            listContext = listContext.selector_list();

            if (listContext == null) {
                break;
            }
        }

        String[] targetFields = new String[list.size()];

        for (int i = 0; i < targetFields.length; i++) {
            targetFields[i] = list.get(i);
        }

        return targetFields;
    }

    private ElasticAggregate[] buildElasticAggregates(SearchQueryParser.AggregatorContext aggregatorContext,
                                                      SearchQueryOutput output) {
        if (aggregatorContext == null) {
            return null;
        }

        SearchQueryParser.Aggregator_listContext listContext = aggregatorContext.aggregator_list();

        if (listContext == null) {
            return null;
        }


        return buildAggregatesFromListContext(listContext, output).toArray(new ElasticAggregate[0]);
    }

    private List<ElasticAggregate> buildAggregatesFromListContext(SearchQueryParser.Aggregator_listContext listContext,
                                                                  SearchQueryOutput output) {
        SearchQueryParser.Aggregator_itemContext itemContext = listContext.aggregator_item();

        // first check if stat list
        SearchQueryParser.Aggregator_stat_listContext stat_listContext = itemContext.aggregator_stat_list();

        // no more test
        if (stat_listContext != null) {
            return buildStatList(stat_listContext);
        }

        ElasticAggregate aggregate = buildAggregateFromItem(itemContext, output);

        SearchQueryParser.Aggregator_listContext next_listContext = listContext.aggregator_list();

        if (next_listContext != null) {
            aggregate.addSubs(buildAggregatesFromListContext(next_listContext, output));
        }

        List<ElasticAggregate> aggList = new ArrayList<>();
        aggList.add(aggregate);

        return aggList;
    }

    private List<ElasticAggregate> buildStatList(SearchQueryParser.Aggregator_stat_listContext stat_listContext) {
        SearchQueryParser.Aggregator_stat_item_listContext stat_item_listContext = stat_listContext.aggregator_stat_item_list();

        List<ElasticAggregate> aggList = new ArrayList<>();

        while (stat_item_listContext != null) {
            SearchQueryParser.Aggregator_stat_itemContext itemContext = stat_item_listContext.aggregator_stat_item();
            aggList.add(SearchQueryAggregateHelper.buildStatAggregate(itemContext));

            stat_item_listContext = stat_item_listContext.aggregator_stat_item_list();
        }

        return aggList;
    }

    // output is provided for value fetch
    private ElasticAggregate buildAggregateFromItem(SearchQueryParser.Aggregator_itemContext itemContext, SearchQueryOutput output) {
        SearchQueryParser.Aggregator_term_itemContext term_itemContext = itemContext.aggregator_term_item();
        SearchQueryParser.Aggregator_stat_itemContext stat_itemContext = itemContext.aggregator_stat_item();
        SearchQueryParser.Aggregator_top_itemContext top_itemContext = itemContext.aggregator_top_item();
        SearchQueryParser.Aggregator_nested_itemContext nested_itemContext = itemContext.aggregator_nested_item();
        SearchQueryParser.Aggregator_histo_itemContext histo_itemContext = itemContext.aggregator_histo_item();

        if (term_itemContext != null) {
            return SearchQueryAggregateHelper.buildTermAggregate(term_itemContext);
        }

        if (stat_itemContext != null) {
            return SearchQueryAggregateHelper.buildStatAggregate(stat_itemContext);
        }

        if (top_itemContext != null) {
            return SearchQueryAggregateHelper.buildTopAggregate(top_itemContext, output);
        }

        if (nested_itemContext != null) {
            return SearchQueryAggregateHelper.buildNestedAggregate(nested_itemContext);
        }

        if (histo_itemContext != null) {
            return SearchQueryAggregateHelper.buildHistoAggregate(histo_itemContext);
        }

        // @SHOULD not REACH HERE
        return null;
    }
}
