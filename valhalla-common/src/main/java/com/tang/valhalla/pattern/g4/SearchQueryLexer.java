// Generated from /Users/lzhou5/github/valhalla/valhalla-common/src/main/java/com/tang/valhalla/pattern/g4/SearchQuery.g4 by ANTLR 4.7
package com.tang.valhalla.pattern.g4;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SearchQueryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, BOOL=27, DATEKEY=28, STR=29, CHIN_STR=30, KEY=31, 
		NUM=32;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "BOOL", "DATEKEY", "STR", "CHIN_STR", "KEY", "NUM"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'['", "']'", "'[]'", "','", "'@'", "'='", "'~'", "'=['", "'('", 
		"')'", "'()'", "'<'", "'>'", "'{'", "'}'", "'{}'", "'<>'", "'top('", "'desc'", 
		"'asc'", "'max'", "'avg'", "'min'", "'sum'", "'$'", "'histo('"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "BOOL", "DATEKEY", "STR", "CHIN_STR", "KEY", "NUM"
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


	public SearchQueryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SearchQuery.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\"\u00cd\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3"+
		"\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20"+
		"\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34"+
		"\u009b\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\6\36\u00b4\n\36"+
		"\r\36\16\36\u00b5\3\36\3\36\3\37\3\37\6\37\u00bc\n\37\r\37\16\37\u00bd"+
		"\3\37\3\37\3 \3 \7 \u00c4\n \f \16 \u00c7\13 \3!\6!\u00ca\n!\r!\16!\u00cb"+
		"\2\2\"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35"+
		"9\36;\37= ?!A\"\3\2\7\3\2\62;\b\2\"\"/\60\62;C\\aac|\4\2\u4e02\u9fa7\uf902"+
		"\ufa2f\5\2C\\aac|\7\2\60\60\62;C\\aac|\2\u00d1\2\3\3\2\2\2\2\5\3\2\2\2"+
		"\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3"+
		"\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2"+
		"\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2"+
		"\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2"+
		"\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2"+
		"\2\2\2A\3\2\2\2\3C\3\2\2\2\5E\3\2\2\2\7G\3\2\2\2\tJ\3\2\2\2\13L\3\2\2"+
		"\2\rN\3\2\2\2\17P\3\2\2\2\21R\3\2\2\2\23U\3\2\2\2\25W\3\2\2\2\27Y\3\2"+
		"\2\2\31\\\3\2\2\2\33^\3\2\2\2\35`\3\2\2\2\37b\3\2\2\2!d\3\2\2\2#g\3\2"+
		"\2\2%j\3\2\2\2\'o\3\2\2\2)t\3\2\2\2+x\3\2\2\2-|\3\2\2\2/\u0080\3\2\2\2"+
		"\61\u0084\3\2\2\2\63\u0088\3\2\2\2\65\u008a\3\2\2\2\67\u009a\3\2\2\29"+
		"\u009c\3\2\2\2;\u00b1\3\2\2\2=\u00b9\3\2\2\2?\u00c1\3\2\2\2A\u00c9\3\2"+
		"\2\2CD\7]\2\2D\4\3\2\2\2EF\7_\2\2F\6\3\2\2\2GH\7]\2\2HI\7_\2\2I\b\3\2"+
		"\2\2JK\7.\2\2K\n\3\2\2\2LM\7B\2\2M\f\3\2\2\2NO\7?\2\2O\16\3\2\2\2PQ\7"+
		"\u0080\2\2Q\20\3\2\2\2RS\7?\2\2ST\7]\2\2T\22\3\2\2\2UV\7*\2\2V\24\3\2"+
		"\2\2WX\7+\2\2X\26\3\2\2\2YZ\7*\2\2Z[\7+\2\2[\30\3\2\2\2\\]\7>\2\2]\32"+
		"\3\2\2\2^_\7@\2\2_\34\3\2\2\2`a\7}\2\2a\36\3\2\2\2bc\7\177\2\2c \3\2\2"+
		"\2de\7}\2\2ef\7\177\2\2f\"\3\2\2\2gh\7>\2\2hi\7@\2\2i$\3\2\2\2jk\7v\2"+
		"\2kl\7q\2\2lm\7r\2\2mn\7*\2\2n&\3\2\2\2op\7f\2\2pq\7g\2\2qr\7u\2\2rs\7"+
		"e\2\2s(\3\2\2\2tu\7c\2\2uv\7u\2\2vw\7e\2\2w*\3\2\2\2xy\7o\2\2yz\7c\2\2"+
		"z{\7z\2\2{,\3\2\2\2|}\7c\2\2}~\7x\2\2~\177\7i\2\2\177.\3\2\2\2\u0080\u0081"+
		"\7o\2\2\u0081\u0082\7k\2\2\u0082\u0083\7p\2\2\u0083\60\3\2\2\2\u0084\u0085"+
		"\7u\2\2\u0085\u0086\7w\2\2\u0086\u0087\7o\2\2\u0087\62\3\2\2\2\u0088\u0089"+
		"\7&\2\2\u0089\64\3\2\2\2\u008a\u008b\7j\2\2\u008b\u008c\7k\2\2\u008c\u008d"+
		"\7u\2\2\u008d\u008e\7v\2\2\u008e\u008f\7q\2\2\u008f\u0090\7*\2\2\u0090"+
		"\66\3\2\2\2\u0091\u0092\7v\2\2\u0092\u0093\7t\2\2\u0093\u0094\7w\2\2\u0094"+
		"\u009b\7g\2\2\u0095\u0096\7h\2\2\u0096\u0097\7c\2\2\u0097\u0098\7n\2\2"+
		"\u0098\u0099\7u\2\2\u0099\u009b\7g\2\2\u009a\u0091\3\2\2\2\u009a\u0095"+
		"\3\2\2\2\u009b8\3\2\2\2\u009c\u009d\7\64\2\2\u009d\u009e\7\62\2\2\u009e"+
		"\u009f\7\63\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\t\2\2\2\u00a1\u00a2\7"+
		"/\2\2\u00a2\u00a3\t\2\2\2\u00a3\u00a4\t\2\2\2\u00a4\u00a5\7/\2\2\u00a5"+
		"\u00a6\t\2\2\2\u00a6\u00a7\t\2\2\2\u00a7\u00a8\7a\2\2\u00a8\u00a9\t\2"+
		"\2\2\u00a9\u00aa\t\2\2\2\u00aa\u00ab\7<\2\2\u00ab\u00ac\t\2\2\2\u00ac"+
		"\u00ad\t\2\2\2\u00ad\u00ae\7<\2\2\u00ae\u00af\t\2\2\2\u00af\u00b0\t\2"+
		"\2\2\u00b0:\3\2\2\2\u00b1\u00b3\7$\2\2\u00b2\u00b4\t\3\2\2\u00b3\u00b2"+
		"\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6"+
		"\u00b7\3\2\2\2\u00b7\u00b8\7$\2\2\u00b8<\3\2\2\2\u00b9\u00bb\7$\2\2\u00ba"+
		"\u00bc\t\4\2\2\u00bb\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bb\3\2"+
		"\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\7$\2\2\u00c0"+
		">\3\2\2\2\u00c1\u00c5\t\5\2\2\u00c2\u00c4\t\6\2\2\u00c3\u00c2\3\2\2\2"+
		"\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6@\3"+
		"\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00ca\t\2\2\2\u00c9\u00c8\3\2\2\2\u00ca"+
		"\u00cb\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00ccB\3\2\2\2"+
		"\b\2\u009a\u00b5\u00bd\u00c5\u00cb\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}