// Generated from /Users/lzhou5/github/valhalla/valhalla-common/src/main/java/com/ebay/valhalla/pattern/g4/SearchQuery.g4 by ANTLR 4.7
package com.ebay.valhalla.pattern.g4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SearchQueryParser}.
 */
public interface SearchQueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(SearchQueryParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(SearchQueryParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#clzName}.
	 * @param ctx the parse tree
	 */
	void enterClzName(SearchQueryParser.ClzNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#clzName}.
	 * @param ctx the parse tree
	 */
	void exitClzName(SearchQueryParser.ClzNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter(SearchQueryParser.FilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter(SearchQueryParser.FilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#filter_list}.
	 * @param ctx the parse tree
	 */
	void enterFilter_list(SearchQueryParser.Filter_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#filter_list}.
	 * @param ctx the parse tree
	 */
	void exitFilter_list(SearchQueryParser.Filter_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#filter_item}.
	 * @param ctx the parse tree
	 */
	void enterFilter_item(SearchQueryParser.Filter_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#filter_item}.
	 * @param ctx the parse tree
	 */
	void exitFilter_item(SearchQueryParser.Filter_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#filter_item_equal}.
	 * @param ctx the parse tree
	 */
	void enterFilter_item_equal(SearchQueryParser.Filter_item_equalContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#filter_item_equal}.
	 * @param ctx the parse tree
	 */
	void exitFilter_item_equal(SearchQueryParser.Filter_item_equalContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#filter_match_equal}.
	 * @param ctx the parse tree
	 */
	void enterFilter_match_equal(SearchQueryParser.Filter_match_equalContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#filter_match_equal}.
	 * @param ctx the parse tree
	 */
	void exitFilter_match_equal(SearchQueryParser.Filter_match_equalContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#filter_item_range}.
	 * @param ctx the parse tree
	 */
	void enterFilter_item_range(SearchQueryParser.Filter_item_rangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#filter_item_range}.
	 * @param ctx the parse tree
	 */
	void exitFilter_item_range(SearchQueryParser.Filter_item_rangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#filter_item_time_range}.
	 * @param ctx the parse tree
	 */
	void enterFilter_item_time_range(SearchQueryParser.Filter_item_time_rangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#filter_item_time_range}.
	 * @param ctx the parse tree
	 */
	void exitFilter_item_time_range(SearchQueryParser.Filter_item_time_rangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#filter_item_compare}.
	 * @param ctx the parse tree
	 */
	void enterFilter_item_compare(SearchQueryParser.Filter_item_compareContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#filter_item_compare}.
	 * @param ctx the parse tree
	 */
	void exitFilter_item_compare(SearchQueryParser.Filter_item_compareContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#sort}.
	 * @param ctx the parse tree
	 */
	void enterSort(SearchQueryParser.SortContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#sort}.
	 * @param ctx the parse tree
	 */
	void exitSort(SearchQueryParser.SortContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#sort_list}.
	 * @param ctx the parse tree
	 */
	void enterSort_list(SearchQueryParser.Sort_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#sort_list}.
	 * @param ctx the parse tree
	 */
	void exitSort_list(SearchQueryParser.Sort_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#sort_item}.
	 * @param ctx the parse tree
	 */
	void enterSort_item(SearchQueryParser.Sort_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#sort_item}.
	 * @param ctx the parse tree
	 */
	void exitSort_item(SearchQueryParser.Sort_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#sort_item_order}.
	 * @param ctx the parse tree
	 */
	void enterSort_item_order(SearchQueryParser.Sort_item_orderContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#sort_item_order}.
	 * @param ctx the parse tree
	 */
	void exitSort_item_order(SearchQueryParser.Sort_item_orderContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterSelector(SearchQueryParser.SelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitSelector(SearchQueryParser.SelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#selector_list}.
	 * @param ctx the parse tree
	 */
	void enterSelector_list(SearchQueryParser.Selector_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#selector_list}.
	 * @param ctx the parse tree
	 */
	void exitSelector_list(SearchQueryParser.Selector_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#selector_item}.
	 * @param ctx the parse tree
	 */
	void enterSelector_item(SearchQueryParser.Selector_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#selector_item}.
	 * @param ctx the parse tree
	 */
	void exitSelector_item(SearchQueryParser.Selector_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#aggregator}.
	 * @param ctx the parse tree
	 */
	void enterAggregator(SearchQueryParser.AggregatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#aggregator}.
	 * @param ctx the parse tree
	 */
	void exitAggregator(SearchQueryParser.AggregatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#aggregator_list}.
	 * @param ctx the parse tree
	 */
	void enterAggregator_list(SearchQueryParser.Aggregator_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#aggregator_list}.
	 * @param ctx the parse tree
	 */
	void exitAggregator_list(SearchQueryParser.Aggregator_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#aggregator_item}.
	 * @param ctx the parse tree
	 */
	void enterAggregator_item(SearchQueryParser.Aggregator_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#aggregator_item}.
	 * @param ctx the parse tree
	 */
	void exitAggregator_item(SearchQueryParser.Aggregator_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#aggregator_term_item}.
	 * @param ctx the parse tree
	 */
	void enterAggregator_term_item(SearchQueryParser.Aggregator_term_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#aggregator_term_item}.
	 * @param ctx the parse tree
	 */
	void exitAggregator_term_item(SearchQueryParser.Aggregator_term_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#aggregator_top_item}.
	 * @param ctx the parse tree
	 */
	void enterAggregator_top_item(SearchQueryParser.Aggregator_top_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#aggregator_top_item}.
	 * @param ctx the parse tree
	 */
	void exitAggregator_top_item(SearchQueryParser.Aggregator_top_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#aggregator_top_order}.
	 * @param ctx the parse tree
	 */
	void enterAggregator_top_order(SearchQueryParser.Aggregator_top_orderContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#aggregator_top_order}.
	 * @param ctx the parse tree
	 */
	void exitAggregator_top_order(SearchQueryParser.Aggregator_top_orderContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#aggregator_stat_list}.
	 * @param ctx the parse tree
	 */
	void enterAggregator_stat_list(SearchQueryParser.Aggregator_stat_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#aggregator_stat_list}.
	 * @param ctx the parse tree
	 */
	void exitAggregator_stat_list(SearchQueryParser.Aggregator_stat_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#aggregator_stat_item_list}.
	 * @param ctx the parse tree
	 */
	void enterAggregator_stat_item_list(SearchQueryParser.Aggregator_stat_item_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#aggregator_stat_item_list}.
	 * @param ctx the parse tree
	 */
	void exitAggregator_stat_item_list(SearchQueryParser.Aggregator_stat_item_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#aggregator_stat_item}.
	 * @param ctx the parse tree
	 */
	void enterAggregator_stat_item(SearchQueryParser.Aggregator_stat_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#aggregator_stat_item}.
	 * @param ctx the parse tree
	 */
	void exitAggregator_stat_item(SearchQueryParser.Aggregator_stat_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#aggregator_stat_action}.
	 * @param ctx the parse tree
	 */
	void enterAggregator_stat_action(SearchQueryParser.Aggregator_stat_actionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#aggregator_stat_action}.
	 * @param ctx the parse tree
	 */
	void exitAggregator_stat_action(SearchQueryParser.Aggregator_stat_actionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#aggregator_nested_item}.
	 * @param ctx the parse tree
	 */
	void enterAggregator_nested_item(SearchQueryParser.Aggregator_nested_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#aggregator_nested_item}.
	 * @param ctx the parse tree
	 */
	void exitAggregator_nested_item(SearchQueryParser.Aggregator_nested_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#aggregator_histo_item}.
	 * @param ctx the parse tree
	 */
	void enterAggregator_histo_item(SearchQueryParser.Aggregator_histo_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#aggregator_histo_item}.
	 * @param ctx the parse tree
	 */
	void exitAggregator_histo_item(SearchQueryParser.Aggregator_histo_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#search_max_size}.
	 * @param ctx the parse tree
	 */
	void enterSearch_max_size(SearchQueryParser.Search_max_sizeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#search_max_size}.
	 * @param ctx the parse tree
	 */
	void exitSearch_max_size(SearchQueryParser.Search_max_sizeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#key_list}.
	 * @param ctx the parse tree
	 */
	void enterKey_list(SearchQueryParser.Key_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#key_list}.
	 * @param ctx the parse tree
	 */
	void exitKey_list(SearchQueryParser.Key_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link SearchQueryParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(SearchQueryParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SearchQueryParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(SearchQueryParser.ValueContext ctx);
}