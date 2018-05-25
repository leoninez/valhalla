// Generated from /Users/lzhou5/github/valhalla/valhalla-common/src/main/java/com/ebay/valhalla/pattern/g4/SearchQuery.g4 by ANTLR 4.7
package com.ebay.valhalla.pattern.g4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SearchQueryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SearchQueryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(SearchQueryParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#clzName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClzName(SearchQueryParser.ClzNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter(SearchQueryParser.FilterContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#filter_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_list(SearchQueryParser.Filter_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#filter_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_item(SearchQueryParser.Filter_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#filter_item_equal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_item_equal(SearchQueryParser.Filter_item_equalContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#filter_match_equal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_match_equal(SearchQueryParser.Filter_match_equalContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#filter_item_range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_item_range(SearchQueryParser.Filter_item_rangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#filter_item_time_range}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_item_time_range(SearchQueryParser.Filter_item_time_rangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#filter_item_compare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter_item_compare(SearchQueryParser.Filter_item_compareContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#sort}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSort(SearchQueryParser.SortContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#sort_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSort_list(SearchQueryParser.Sort_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#sort_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSort_item(SearchQueryParser.Sort_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#sort_item_order}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSort_item_order(SearchQueryParser.Sort_item_orderContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#selector}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelector(SearchQueryParser.SelectorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#selector_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelector_list(SearchQueryParser.Selector_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#selector_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelector_item(SearchQueryParser.Selector_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#aggregator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregator(SearchQueryParser.AggregatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#aggregator_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregator_list(SearchQueryParser.Aggregator_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#aggregator_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregator_item(SearchQueryParser.Aggregator_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#aggregator_term_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregator_term_item(SearchQueryParser.Aggregator_term_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#aggregator_top_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregator_top_item(SearchQueryParser.Aggregator_top_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#aggregator_top_order}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregator_top_order(SearchQueryParser.Aggregator_top_orderContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#aggregator_stat_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregator_stat_list(SearchQueryParser.Aggregator_stat_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#aggregator_stat_item_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregator_stat_item_list(SearchQueryParser.Aggregator_stat_item_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#aggregator_stat_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregator_stat_item(SearchQueryParser.Aggregator_stat_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#aggregator_stat_action}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregator_stat_action(SearchQueryParser.Aggregator_stat_actionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#aggregator_nested_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregator_nested_item(SearchQueryParser.Aggregator_nested_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#aggregator_histo_item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregator_histo_item(SearchQueryParser.Aggregator_histo_itemContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#search_max_size}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSearch_max_size(SearchQueryParser.Search_max_sizeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#key_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKey_list(SearchQueryParser.Key_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link SearchQueryParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(SearchQueryParser.ValueContext ctx);
}