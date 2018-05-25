// Generated from /Users/lzhou5/work/github/job-monitoring/jpm-valhalla/valhalla-common/src/main/java/com/ebay/valhalla/pattern/g4/SearchQuery.g4 by ANTLR 4.7
package com.ebay.valhalla.pattern.g4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SearchQueryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, BOOL=26, DATEKEY=27, STR=28, KEY=29, NUM=30;
	public static final int
		RULE_query = 0, RULE_clzName = 1, RULE_filter = 2, RULE_filter_list = 3, 
		RULE_filter_item = 4, RULE_filter_item_equal = 5, RULE_filter_item_range = 6, 
		RULE_filter_item_time_range = 7, RULE_filter_item_compare = 8, RULE_sort = 9, 
		RULE_sort_list = 10, RULE_sort_item = 11, RULE_sort_item_order = 12, RULE_selector = 13, 
		RULE_selector_list = 14, RULE_selector_item = 15, RULE_aggregator = 16, 
		RULE_aggregator_list = 17, RULE_aggregator_item = 18, RULE_aggregator_term_item = 19, 
		RULE_aggregator_top_item = 20, RULE_aggregator_top_order = 21, RULE_aggregator_stat_list = 22, 
		RULE_aggregator_stat_item_list = 23, RULE_aggregator_stat_item = 24, RULE_aggregator_stat_action = 25, 
		RULE_aggregator_nested_item = 26, RULE_aggregator_histo_item = 27, RULE_search_max_size = 28, 
		RULE_key_list = 29, RULE_value = 30;
	public static final String[] ruleNames = {
		"query", "clzName", "filter", "filter_list", "filter_item", "filter_item_equal", 
		"filter_item_range", "filter_item_time_range", "filter_item_compare", 
		"sort", "sort_list", "sort_item", "sort_item_order", "selector", "selector_list", 
		"selector_item", "aggregator", "aggregator_list", "aggregator_item", "aggregator_term_item", 
		"aggregator_top_item", "aggregator_top_order", "aggregator_stat_list", 
		"aggregator_stat_item_list", "aggregator_stat_item", "aggregator_stat_action", 
		"aggregator_nested_item", "aggregator_histo_item", "search_max_size", 
		"key_list", "value"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'['", "']'", "'[]'", "','", "'@'", "'='", "'=['", "'('", "')'", 
		"'()'", "'<'", "'>'", "'{'", "'}'", "'{}'", "'<>'", "'top('", "'desc'", 
		"'asc'", "'max'", "'avg'", "'min'", "'sum'", "'$'", "'histo('"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "BOOL", "DATEKEY", "STR", "KEY", "NUM"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SearchQuery.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SearchQueryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public ClzNameContext clzName() {
			return getRuleContext(ClzNameContext.class,0);
		}
		public FilterContext filter() {
			return getRuleContext(FilterContext.class,0);
		}
		public SortContext sort() {
			return getRuleContext(SortContext.class,0);
		}
		public SelectorContext selector() {
			return getRuleContext(SelectorContext.class,0);
		}
		public AggregatorContext aggregator() {
			return getRuleContext(AggregatorContext.class,0);
		}
		public Search_max_sizeContext search_max_size() {
			return getRuleContext(Search_max_sizeContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			clzName();
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0 || _la==T__2) {
				{
				setState(63);
				filter();
				}
			}

			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7 || _la==T__9) {
				{
				setState(66);
				sort();
				}
			}

			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12 || _la==T__14) {
				{
				setState(69);
				selector();
				}
			}

			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10 || _la==T__15) {
				{
				setState(72);
				aggregator();
				}
			}

			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(75);
				search_max_size();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClzNameContext extends ParserRuleContext {
		public TerminalNode KEY() { return getToken(SearchQueryParser.KEY, 0); }
		public ClzNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clzName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterClzName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitClzName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitClzName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClzNameContext clzName() throws RecognitionException {
		ClzNameContext _localctx = new ClzNameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_clzName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(KEY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FilterContext extends ParserRuleContext {
		public Filter_listContext filter_list() {
			return getRuleContext(Filter_listContext.class,0);
		}
		public FilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitFilter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitFilter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterContext filter() throws RecognitionException {
		FilterContext _localctx = new FilterContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_filter);
		try {
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(80);
				match(T__0);
				setState(81);
				filter_list();
				setState(82);
				match(T__1);
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Filter_listContext extends ParserRuleContext {
		public Filter_itemContext filter_item() {
			return getRuleContext(Filter_itemContext.class,0);
		}
		public Filter_listContext filter_list() {
			return getRuleContext(Filter_listContext.class,0);
		}
		public Filter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterFilter_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitFilter_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitFilter_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Filter_listContext filter_list() throws RecognitionException {
		Filter_listContext _localctx = new Filter_listContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_filter_list);
		try {
			setState(92);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(87);
				filter_item();
				setState(88);
				match(T__3);
				setState(89);
				filter_list();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				filter_item();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Filter_itemContext extends ParserRuleContext {
		public Filter_item_equalContext filter_item_equal() {
			return getRuleContext(Filter_item_equalContext.class,0);
		}
		public Filter_item_rangeContext filter_item_range() {
			return getRuleContext(Filter_item_rangeContext.class,0);
		}
		public Filter_item_time_rangeContext filter_item_time_range() {
			return getRuleContext(Filter_item_time_rangeContext.class,0);
		}
		public Filter_item_compareContext filter_item_compare() {
			return getRuleContext(Filter_item_compareContext.class,0);
		}
		public Filter_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterFilter_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitFilter_item(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitFilter_item(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Filter_itemContext filter_item() throws RecognitionException {
		Filter_itemContext _localctx = new Filter_itemContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_filter_item);
		try {
			setState(98);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(94);
				filter_item_equal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(95);
				filter_item_range();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(96);
				filter_item_time_range();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(97);
				filter_item_compare();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Filter_item_equalContext extends ParserRuleContext {
		public TerminalNode KEY() { return getToken(SearchQueryParser.KEY, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public Filter_item_equalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter_item_equal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterFilter_item_equal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitFilter_item_equal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitFilter_item_equal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Filter_item_equalContext filter_item_equal() throws RecognitionException {
		Filter_item_equalContext _localctx = new Filter_item_equalContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_filter_item_equal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(T__4);
			setState(101);
			match(KEY);
			setState(102);
			match(T__5);
			setState(103);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Filter_item_rangeContext extends ParserRuleContext {
		public TerminalNode KEY() { return getToken(SearchQueryParser.KEY, 0); }
		public List<TerminalNode> NUM() { return getTokens(SearchQueryParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(SearchQueryParser.NUM, i);
		}
		public Filter_item_rangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter_item_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterFilter_item_range(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitFilter_item_range(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitFilter_item_range(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Filter_item_rangeContext filter_item_range() throws RecognitionException {
		Filter_item_rangeContext _localctx = new Filter_item_rangeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_filter_item_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(T__4);
			setState(106);
			match(KEY);
			setState(107);
			match(T__6);
			setState(108);
			match(NUM);
			setState(109);
			match(T__3);
			setState(110);
			match(NUM);
			setState(111);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Filter_item_time_rangeContext extends ParserRuleContext {
		public TerminalNode KEY() { return getToken(SearchQueryParser.KEY, 0); }
		public List<TerminalNode> DATEKEY() { return getTokens(SearchQueryParser.DATEKEY); }
		public TerminalNode DATEKEY(int i) {
			return getToken(SearchQueryParser.DATEKEY, i);
		}
		public Filter_item_time_rangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter_item_time_range; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterFilter_item_time_range(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitFilter_item_time_range(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitFilter_item_time_range(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Filter_item_time_rangeContext filter_item_time_range() throws RecognitionException {
		Filter_item_time_rangeContext _localctx = new Filter_item_time_rangeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_filter_item_time_range);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(T__4);
			setState(114);
			match(KEY);
			setState(115);
			match(T__6);
			setState(116);
			match(DATEKEY);
			setState(117);
			match(T__3);
			setState(118);
			match(DATEKEY);
			setState(119);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Filter_item_compareContext extends ParserRuleContext {
		public TerminalNode KEY() { return getToken(SearchQueryParser.KEY, 0); }
		public Sort_item_orderContext sort_item_order() {
			return getRuleContext(Sort_item_orderContext.class,0);
		}
		public TerminalNode NUM() { return getToken(SearchQueryParser.NUM, 0); }
		public Filter_item_compareContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filter_item_compare; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterFilter_item_compare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitFilter_item_compare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitFilter_item_compare(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Filter_item_compareContext filter_item_compare() throws RecognitionException {
		Filter_item_compareContext _localctx = new Filter_item_compareContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_filter_item_compare);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(T__4);
			setState(122);
			match(KEY);
			setState(123);
			sort_item_order();
			setState(124);
			match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SortContext extends ParserRuleContext {
		public Sort_listContext sort_list() {
			return getRuleContext(Sort_listContext.class,0);
		}
		public SortContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sort; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterSort(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitSort(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitSort(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SortContext sort() throws RecognitionException {
		SortContext _localctx = new SortContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_sort);
		try {
			setState(131);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				match(T__7);
				setState(127);
				sort_list();
				setState(128);
				match(T__8);
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				match(T__9);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sort_listContext extends ParserRuleContext {
		public Sort_itemContext sort_item() {
			return getRuleContext(Sort_itemContext.class,0);
		}
		public Sort_listContext sort_list() {
			return getRuleContext(Sort_listContext.class,0);
		}
		public Sort_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sort_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterSort_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitSort_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitSort_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sort_listContext sort_list() throws RecognitionException {
		Sort_listContext _localctx = new Sort_listContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_sort_list);
		try {
			setState(138);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(133);
				sort_item();
				setState(134);
				match(T__3);
				setState(135);
				sort_list();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				sort_item();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sort_itemContext extends ParserRuleContext {
		public TerminalNode KEY() { return getToken(SearchQueryParser.KEY, 0); }
		public Sort_item_orderContext sort_item_order() {
			return getRuleContext(Sort_item_orderContext.class,0);
		}
		public Sort_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sort_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterSort_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitSort_item(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitSort_item(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sort_itemContext sort_item() throws RecognitionException {
		Sort_itemContext _localctx = new Sort_itemContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_sort_item);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(T__4);
			setState(141);
			match(KEY);
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10 || _la==T__11) {
				{
				setState(142);
				sort_item_order();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sort_item_orderContext extends ParserRuleContext {
		public Sort_item_orderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sort_item_order; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterSort_item_order(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitSort_item_order(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitSort_item_order(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sort_item_orderContext sort_item_order() throws RecognitionException {
		Sort_item_orderContext _localctx = new Sort_item_orderContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_sort_item_order);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			_la = _input.LA(1);
			if ( !(_la==T__10 || _la==T__11) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectorContext extends ParserRuleContext {
		public Selector_listContext selector_list() {
			return getRuleContext(Selector_listContext.class,0);
		}
		public SelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitSelector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitSelector(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectorContext selector() throws RecognitionException {
		SelectorContext _localctx = new SelectorContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_selector);
		try {
			setState(152);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
				enterOuterAlt(_localctx, 1);
				{
				setState(147);
				match(T__12);
				setState(148);
				selector_list();
				setState(149);
				match(T__13);
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				match(T__14);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Selector_listContext extends ParserRuleContext {
		public Selector_itemContext selector_item() {
			return getRuleContext(Selector_itemContext.class,0);
		}
		public Selector_listContext selector_list() {
			return getRuleContext(Selector_listContext.class,0);
		}
		public Selector_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selector_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterSelector_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitSelector_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitSelector_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Selector_listContext selector_list() throws RecognitionException {
		Selector_listContext _localctx = new Selector_listContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_selector_list);
		try {
			setState(159);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(154);
				selector_item();
				setState(155);
				match(T__3);
				setState(156);
				selector_list();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				selector_item();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Selector_itemContext extends ParserRuleContext {
		public TerminalNode KEY() { return getToken(SearchQueryParser.KEY, 0); }
		public Selector_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selector_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterSelector_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitSelector_item(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitSelector_item(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Selector_itemContext selector_item() throws RecognitionException {
		Selector_itemContext _localctx = new Selector_itemContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_selector_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(T__4);
			setState(162);
			match(KEY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AggregatorContext extends ParserRuleContext {
		public Aggregator_listContext aggregator_list() {
			return getRuleContext(Aggregator_listContext.class,0);
		}
		public AggregatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterAggregator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitAggregator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitAggregator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AggregatorContext aggregator() throws RecognitionException {
		AggregatorContext _localctx = new AggregatorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_aggregator);
		try {
			setState(169);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				match(T__10);
				setState(165);
				aggregator_list();
				setState(166);
				match(T__11);
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
				match(T__15);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Aggregator_listContext extends ParserRuleContext {
		public Aggregator_itemContext aggregator_item() {
			return getRuleContext(Aggregator_itemContext.class,0);
		}
		public Aggregator_listContext aggregator_list() {
			return getRuleContext(Aggregator_listContext.class,0);
		}
		public Aggregator_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregator_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterAggregator_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitAggregator_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitAggregator_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aggregator_listContext aggregator_list() throws RecognitionException {
		Aggregator_listContext _localctx = new Aggregator_listContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_aggregator_list);
		try {
			setState(176);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(171);
				aggregator_item();
				setState(172);
				match(T__3);
				setState(173);
				aggregator_list();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				aggregator_item();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Aggregator_itemContext extends ParserRuleContext {
		public Aggregator_term_itemContext aggregator_term_item() {
			return getRuleContext(Aggregator_term_itemContext.class,0);
		}
		public Aggregator_top_itemContext aggregator_top_item() {
			return getRuleContext(Aggregator_top_itemContext.class,0);
		}
		public Aggregator_stat_itemContext aggregator_stat_item() {
			return getRuleContext(Aggregator_stat_itemContext.class,0);
		}
		public Aggregator_stat_listContext aggregator_stat_list() {
			return getRuleContext(Aggregator_stat_listContext.class,0);
		}
		public Aggregator_nested_itemContext aggregator_nested_item() {
			return getRuleContext(Aggregator_nested_itemContext.class,0);
		}
		public Aggregator_histo_itemContext aggregator_histo_item() {
			return getRuleContext(Aggregator_histo_itemContext.class,0);
		}
		public Aggregator_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregator_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterAggregator_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitAggregator_item(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitAggregator_item(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aggregator_itemContext aggregator_item() throws RecognitionException {
		Aggregator_itemContext _localctx = new Aggregator_itemContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_aggregator_item);
		try {
			setState(184);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(178);
				aggregator_term_item();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 2);
				{
				setState(179);
				aggregator_top_item();
				}
				break;
			case T__19:
			case T__20:
			case T__21:
			case T__22:
				enterOuterAlt(_localctx, 3);
				{
				setState(180);
				aggregator_stat_item();
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 4);
				{
				setState(181);
				aggregator_stat_list();
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 5);
				{
				setState(182);
				aggregator_nested_item();
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 6);
				{
				setState(183);
				aggregator_histo_item();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Aggregator_term_itemContext extends ParserRuleContext {
		public TerminalNode KEY() { return getToken(SearchQueryParser.KEY, 0); }
		public Aggregator_term_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregator_term_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterAggregator_term_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitAggregator_term_item(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitAggregator_term_item(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aggregator_term_itemContext aggregator_term_item() throws RecognitionException {
		Aggregator_term_itemContext _localctx = new Aggregator_term_itemContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_aggregator_term_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(T__4);
			setState(187);
			match(KEY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Aggregator_top_itemContext extends ParserRuleContext {
		public TerminalNode KEY() { return getToken(SearchQueryParser.KEY, 0); }
		public TerminalNode NUM() { return getToken(SearchQueryParser.NUM, 0); }
		public Aggregator_top_orderContext aggregator_top_order() {
			return getRuleContext(Aggregator_top_orderContext.class,0);
		}
		public Aggregator_top_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregator_top_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterAggregator_top_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitAggregator_top_item(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitAggregator_top_item(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aggregator_top_itemContext aggregator_top_item() throws RecognitionException {
		Aggregator_top_itemContext _localctx = new Aggregator_top_itemContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_aggregator_top_item);
		try {
			setState(202);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(189);
				match(T__16);
				setState(190);
				match(KEY);
				setState(191);
				match(T__3);
				setState(192);
				match(NUM);
				setState(193);
				match(T__3);
				setState(194);
				aggregator_top_order();
				setState(195);
				match(T__8);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(197);
				match(T__16);
				setState(198);
				match(KEY);
				setState(199);
				match(T__3);
				setState(200);
				match(NUM);
				setState(201);
				match(T__8);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Aggregator_top_orderContext extends ParserRuleContext {
		public Aggregator_top_orderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregator_top_order; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterAggregator_top_order(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitAggregator_top_order(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitAggregator_top_order(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aggregator_top_orderContext aggregator_top_order() throws RecognitionException {
		Aggregator_top_orderContext _localctx = new Aggregator_top_orderContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_aggregator_top_order);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			_la = _input.LA(1);
			if ( !(_la==T__17 || _la==T__18) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Aggregator_stat_listContext extends ParserRuleContext {
		public Aggregator_stat_item_listContext aggregator_stat_item_list() {
			return getRuleContext(Aggregator_stat_item_listContext.class,0);
		}
		public Aggregator_stat_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregator_stat_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterAggregator_stat_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitAggregator_stat_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitAggregator_stat_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aggregator_stat_listContext aggregator_stat_list() throws RecognitionException {
		Aggregator_stat_listContext _localctx = new Aggregator_stat_listContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_aggregator_stat_list);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(T__0);
			setState(207);
			aggregator_stat_item_list();
			setState(208);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Aggregator_stat_item_listContext extends ParserRuleContext {
		public Aggregator_stat_itemContext aggregator_stat_item() {
			return getRuleContext(Aggregator_stat_itemContext.class,0);
		}
		public Aggregator_stat_item_listContext aggregator_stat_item_list() {
			return getRuleContext(Aggregator_stat_item_listContext.class,0);
		}
		public Aggregator_stat_item_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregator_stat_item_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterAggregator_stat_item_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitAggregator_stat_item_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitAggregator_stat_item_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aggregator_stat_item_listContext aggregator_stat_item_list() throws RecognitionException {
		Aggregator_stat_item_listContext _localctx = new Aggregator_stat_item_listContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_aggregator_stat_item_list);
		try {
			setState(215);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(210);
				aggregator_stat_item();
				setState(211);
				match(T__3);
				setState(212);
				aggregator_stat_item_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(214);
				aggregator_stat_item();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Aggregator_stat_itemContext extends ParserRuleContext {
		public Aggregator_stat_actionContext aggregator_stat_action() {
			return getRuleContext(Aggregator_stat_actionContext.class,0);
		}
		public TerminalNode KEY() { return getToken(SearchQueryParser.KEY, 0); }
		public Aggregator_stat_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregator_stat_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterAggregator_stat_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitAggregator_stat_item(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitAggregator_stat_item(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aggregator_stat_itemContext aggregator_stat_item() throws RecognitionException {
		Aggregator_stat_itemContext _localctx = new Aggregator_stat_itemContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_aggregator_stat_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			aggregator_stat_action();
			setState(218);
			match(T__7);
			setState(219);
			match(KEY);
			setState(220);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Aggregator_stat_actionContext extends ParserRuleContext {
		public Aggregator_stat_actionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregator_stat_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterAggregator_stat_action(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitAggregator_stat_action(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitAggregator_stat_action(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aggregator_stat_actionContext aggregator_stat_action() throws RecognitionException {
		Aggregator_stat_actionContext _localctx = new Aggregator_stat_actionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_aggregator_stat_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Aggregator_nested_itemContext extends ParserRuleContext {
		public TerminalNode KEY() { return getToken(SearchQueryParser.KEY, 0); }
		public Aggregator_nested_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregator_nested_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterAggregator_nested_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitAggregator_nested_item(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitAggregator_nested_item(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aggregator_nested_itemContext aggregator_nested_item() throws RecognitionException {
		Aggregator_nested_itemContext _localctx = new Aggregator_nested_itemContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_aggregator_nested_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(T__23);
			setState(225);
			match(KEY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Aggregator_histo_itemContext extends ParserRuleContext {
		public TerminalNode KEY() { return getToken(SearchQueryParser.KEY, 0); }
		public TerminalNode NUM() { return getToken(SearchQueryParser.NUM, 0); }
		public Aggregator_histo_itemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregator_histo_item; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterAggregator_histo_item(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitAggregator_histo_item(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitAggregator_histo_item(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Aggregator_histo_itemContext aggregator_histo_item() throws RecognitionException {
		Aggregator_histo_itemContext _localctx = new Aggregator_histo_itemContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_aggregator_histo_item);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			match(T__24);
			setState(228);
			match(KEY);
			setState(229);
			match(T__3);
			setState(230);
			match(NUM);
			setState(231);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Search_max_sizeContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(SearchQueryParser.NUM, 0); }
		public Search_max_sizeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_search_max_size; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterSearch_max_size(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitSearch_max_size(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitSearch_max_size(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Search_max_sizeContext search_max_size() throws RecognitionException {
		Search_max_sizeContext _localctx = new Search_max_sizeContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_search_max_size);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(T__4);
			setState(234);
			match(NUM);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Key_listContext extends ParserRuleContext {
		public TerminalNode KEY() { return getToken(SearchQueryParser.KEY, 0); }
		public Key_listContext key_list() {
			return getRuleContext(Key_listContext.class,0);
		}
		public Key_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_key_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterKey_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitKey_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitKey_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Key_listContext key_list() throws RecognitionException {
		Key_listContext _localctx = new Key_listContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_key_list);
		try {
			setState(240);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(236);
				match(KEY);
				setState(237);
				match(T__3);
				setState(238);
				key_list();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				match(KEY);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode NUM() { return getToken(SearchQueryParser.NUM, 0); }
		public TerminalNode STR() { return getToken(SearchQueryParser.STR, 0); }
		public TerminalNode BOOL() { return getToken(SearchQueryParser.BOOL, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SearchQueryListener ) ((SearchQueryListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SearchQueryVisitor ) return ((SearchQueryVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << STR) | (1L << NUM))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u00f7\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\5\2C\n\2\3\2\5\2F\n\2\3\2\5\2I\n\2\3\2\5\2L\n\2\3\2\5\2O\n\2\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\5\4X\n\4\3\5\3\5\3\5\3\5\3\5\5\5_\n\5\3\6\3\6"+
		"\3\6\3\6\5\6e\n\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\5\13\u0086\n\13\3\f\3\f\3\f\3\f\3\f\5\f\u008d\n\f\3\r\3\r\3\r"+
		"\5\r\u0092\n\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\5\17\u009b\n\17\3\20"+
		"\3\20\3\20\3\20\3\20\5\20\u00a2\n\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\5\22\u00ac\n\22\3\23\3\23\3\23\3\23\3\23\5\23\u00b3\n\23\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\5\24\u00bb\n\24\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u00cd\n\26\3\27"+
		"\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\5\31\u00da\n\31\3\32"+
		"\3\32\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37\5\37\u00f3\n\37\3 \3 \3 \2\2"+
		"!\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>\2\6"+
		"\3\2\r\16\3\2\24\25\3\2\26\31\5\2\34\34\36\36  \2\u00f0\2@\3\2\2\2\4P"+
		"\3\2\2\2\6W\3\2\2\2\b^\3\2\2\2\nd\3\2\2\2\ff\3\2\2\2\16k\3\2\2\2\20s\3"+
		"\2\2\2\22{\3\2\2\2\24\u0085\3\2\2\2\26\u008c\3\2\2\2\30\u008e\3\2\2\2"+
		"\32\u0093\3\2\2\2\34\u009a\3\2\2\2\36\u00a1\3\2\2\2 \u00a3\3\2\2\2\"\u00ab"+
		"\3\2\2\2$\u00b2\3\2\2\2&\u00ba\3\2\2\2(\u00bc\3\2\2\2*\u00cc\3\2\2\2,"+
		"\u00ce\3\2\2\2.\u00d0\3\2\2\2\60\u00d9\3\2\2\2\62\u00db\3\2\2\2\64\u00e0"+
		"\3\2\2\2\66\u00e2\3\2\2\28\u00e5\3\2\2\2:\u00eb\3\2\2\2<\u00f2\3\2\2\2"+
		">\u00f4\3\2\2\2@B\5\4\3\2AC\5\6\4\2BA\3\2\2\2BC\3\2\2\2CE\3\2\2\2DF\5"+
		"\24\13\2ED\3\2\2\2EF\3\2\2\2FH\3\2\2\2GI\5\34\17\2HG\3\2\2\2HI\3\2\2\2"+
		"IK\3\2\2\2JL\5\"\22\2KJ\3\2\2\2KL\3\2\2\2LN\3\2\2\2MO\5:\36\2NM\3\2\2"+
		"\2NO\3\2\2\2O\3\3\2\2\2PQ\7\37\2\2Q\5\3\2\2\2RS\7\3\2\2ST\5\b\5\2TU\7"+
		"\4\2\2UX\3\2\2\2VX\7\5\2\2WR\3\2\2\2WV\3\2\2\2X\7\3\2\2\2YZ\5\n\6\2Z["+
		"\7\6\2\2[\\\5\b\5\2\\_\3\2\2\2]_\5\n\6\2^Y\3\2\2\2^]\3\2\2\2_\t\3\2\2"+
		"\2`e\5\f\7\2ae\5\16\b\2be\5\20\t\2ce\5\22\n\2d`\3\2\2\2da\3\2\2\2db\3"+
		"\2\2\2dc\3\2\2\2e\13\3\2\2\2fg\7\7\2\2gh\7\37\2\2hi\7\b\2\2ij\5> \2j\r"+
		"\3\2\2\2kl\7\7\2\2lm\7\37\2\2mn\7\t\2\2no\7 \2\2op\7\6\2\2pq\7 \2\2qr"+
		"\7\4\2\2r\17\3\2\2\2st\7\7\2\2tu\7\37\2\2uv\7\t\2\2vw\7\35\2\2wx\7\6\2"+
		"\2xy\7\35\2\2yz\7\4\2\2z\21\3\2\2\2{|\7\7\2\2|}\7\37\2\2}~\5\32\16\2~"+
		"\177\7 \2\2\177\23\3\2\2\2\u0080\u0081\7\n\2\2\u0081\u0082\5\26\f\2\u0082"+
		"\u0083\7\13\2\2\u0083\u0086\3\2\2\2\u0084\u0086\7\f\2\2\u0085\u0080\3"+
		"\2\2\2\u0085\u0084\3\2\2\2\u0086\25\3\2\2\2\u0087\u0088\5\30\r\2\u0088"+
		"\u0089\7\6\2\2\u0089\u008a\5\26\f\2\u008a\u008d\3\2\2\2\u008b\u008d\5"+
		"\30\r\2\u008c\u0087\3\2\2\2\u008c\u008b\3\2\2\2\u008d\27\3\2\2\2\u008e"+
		"\u008f\7\7\2\2\u008f\u0091\7\37\2\2\u0090\u0092\5\32\16\2\u0091\u0090"+
		"\3\2\2\2\u0091\u0092\3\2\2\2\u0092\31\3\2\2\2\u0093\u0094\t\2\2\2\u0094"+
		"\33\3\2\2\2\u0095\u0096\7\17\2\2\u0096\u0097\5\36\20\2\u0097\u0098\7\20"+
		"\2\2\u0098\u009b\3\2\2\2\u0099\u009b\7\21\2\2\u009a\u0095\3\2\2\2\u009a"+
		"\u0099\3\2\2\2\u009b\35\3\2\2\2\u009c\u009d\5 \21\2\u009d\u009e\7\6\2"+
		"\2\u009e\u009f\5\36\20\2\u009f\u00a2\3\2\2\2\u00a0\u00a2\5 \21\2\u00a1"+
		"\u009c\3\2\2\2\u00a1\u00a0\3\2\2\2\u00a2\37\3\2\2\2\u00a3\u00a4\7\7\2"+
		"\2\u00a4\u00a5\7\37\2\2\u00a5!\3\2\2\2\u00a6\u00a7\7\r\2\2\u00a7\u00a8"+
		"\5$\23\2\u00a8\u00a9\7\16\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00ac\7\22\2\2"+
		"\u00ab\u00a6\3\2\2\2\u00ab\u00aa\3\2\2\2\u00ac#\3\2\2\2\u00ad\u00ae\5"+
		"&\24\2\u00ae\u00af\7\6\2\2\u00af\u00b0\5$\23\2\u00b0\u00b3\3\2\2\2\u00b1"+
		"\u00b3\5&\24\2\u00b2\u00ad\3\2\2\2\u00b2\u00b1\3\2\2\2\u00b3%\3\2\2\2"+
		"\u00b4\u00bb\5(\25\2\u00b5\u00bb\5*\26\2\u00b6\u00bb\5\62\32\2\u00b7\u00bb"+
		"\5.\30\2\u00b8\u00bb\5\66\34\2\u00b9\u00bb\58\35\2\u00ba\u00b4\3\2\2\2"+
		"\u00ba\u00b5\3\2\2\2\u00ba\u00b6\3\2\2\2\u00ba\u00b7\3\2\2\2\u00ba\u00b8"+
		"\3\2\2\2\u00ba\u00b9\3\2\2\2\u00bb\'\3\2\2\2\u00bc\u00bd\7\7\2\2\u00bd"+
		"\u00be\7\37\2\2\u00be)\3\2\2\2\u00bf\u00c0\7\23\2\2\u00c0\u00c1\7\37\2"+
		"\2\u00c1\u00c2\7\6\2\2\u00c2\u00c3\7 \2\2\u00c3\u00c4\7\6\2\2\u00c4\u00c5"+
		"\5,\27\2\u00c5\u00c6\7\13\2\2\u00c6\u00cd\3\2\2\2\u00c7\u00c8\7\23\2\2"+
		"\u00c8\u00c9\7\37\2\2\u00c9\u00ca\7\6\2\2\u00ca\u00cb\7 \2\2\u00cb\u00cd"+
		"\7\13\2\2\u00cc\u00bf\3\2\2\2\u00cc\u00c7\3\2\2\2\u00cd+\3\2\2\2\u00ce"+
		"\u00cf\t\3\2\2\u00cf-\3\2\2\2\u00d0\u00d1\7\3\2\2\u00d1\u00d2\5\60\31"+
		"\2\u00d2\u00d3\7\4\2\2\u00d3/\3\2\2\2\u00d4\u00d5\5\62\32\2\u00d5\u00d6"+
		"\7\6\2\2\u00d6\u00d7\5\60\31\2\u00d7\u00da\3\2\2\2\u00d8\u00da\5\62\32"+
		"\2\u00d9\u00d4\3\2\2\2\u00d9\u00d8\3\2\2\2\u00da\61\3\2\2\2\u00db\u00dc"+
		"\5\64\33\2\u00dc\u00dd\7\n\2\2\u00dd\u00de\7\37\2\2\u00de\u00df\7\13\2"+
		"\2\u00df\63\3\2\2\2\u00e0\u00e1\t\4\2\2\u00e1\65\3\2\2\2\u00e2\u00e3\7"+
		"\32\2\2\u00e3\u00e4\7\37\2\2\u00e4\67\3\2\2\2\u00e5\u00e6\7\33\2\2\u00e6"+
		"\u00e7\7\37\2\2\u00e7\u00e8\7\6\2\2\u00e8\u00e9\7 \2\2\u00e9\u00ea\7\13"+
		"\2\2\u00ea9\3\2\2\2\u00eb\u00ec\7\7\2\2\u00ec\u00ed\7 \2\2\u00ed;\3\2"+
		"\2\2\u00ee\u00ef\7\37\2\2\u00ef\u00f0\7\6\2\2\u00f0\u00f3\5<\37\2\u00f1"+
		"\u00f3\7\37\2\2\u00f2\u00ee\3\2\2\2\u00f2\u00f1\3\2\2\2\u00f3=\3\2\2\2"+
		"\u00f4\u00f5\t\5\2\2\u00f5?\3\2\2\2\25BEHKNW^d\u0085\u008c\u0091\u009a"+
		"\u00a1\u00ab\u00b2\u00ba\u00cc\u00d9\u00f2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}