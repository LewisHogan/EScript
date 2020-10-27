// Generated from N:/University/Compilers/src/escript\escript.g4 by ANTLR 4.8
package escript;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class escriptLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ADDSET=1, SUBSET=2, MULSET=3, DIVSET=4, MODSET=5, POWSET=6, SET=7, POW=8, 
		MOD=9, MUL=10, DIV=11, ADD=12, SUB=13, AND=14, OR=15, GREATER_THAN=16, 
		GREATER_THAN_OR_EQUALS=17, LESS_THAN=18, LESS_THAN_OR_EQUALS=19, EQUALS=20, 
		NOT_EQUALS=21, NOT=22, TRUE=23, FALSE=24, IF=25, ELSEIF=26, ELSE=27, WHILE=28, 
		FOR=29, QUESTION=30, COLON=31, PRINT=32, SEP=33, EOS=34, LBRACE=35, RBRACE=36, 
		LPAREN=37, RPAREN=38, FUNCTION=39, RETURN=40, NUMBER=41, FLOAT=42, INTEGER=43, 
		ID=44, STRING=45, WS=46;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"ADDSET", "SUBSET", "MULSET", "DIVSET", "MODSET", "POWSET", "SET", "POW", 
			"MOD", "MUL", "DIV", "ADD", "SUB", "AND", "OR", "GREATER_THAN", "GREATER_THAN_OR_EQUALS", 
			"LESS_THAN", "LESS_THAN_OR_EQUALS", "EQUALS", "NOT_EQUALS", "NOT", "TRUE", 
			"FALSE", "IF", "ELSEIF", "ELSE", "WHILE", "FOR", "QUESTION", "COLON", 
			"PRINT", "SEP", "EOS", "LBRACE", "RBRACE", "LPAREN", "RPAREN", "FUNCTION", 
			"RETURN", "NUMBER", "FLOAT", "INTEGER", "ID", "STRING", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, "'='", "'**'", "'%'", "'*'", 
			"'/'", "'+'", "'-'", "'&&'", "'||'", "'>'", "'>='", "'<'", "'<='", "'=='", 
			"'!='", "'!'", "'true'", "'false'", "'if'", "'else if'", "'else'", "'while'", 
			"'for'", "'?'", "':'", "'print'", "','", "';'", "'{'", "'}'", "'('", 
			"')'", "'function'", "'return'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ADDSET", "SUBSET", "MULSET", "DIVSET", "MODSET", "POWSET", "SET", 
			"POW", "MOD", "MUL", "DIV", "ADD", "SUB", "AND", "OR", "GREATER_THAN", 
			"GREATER_THAN_OR_EQUALS", "LESS_THAN", "LESS_THAN_OR_EQUALS", "EQUALS", 
			"NOT_EQUALS", "NOT", "TRUE", "FALSE", "IF", "ELSEIF", "ELSE", "WHILE", 
			"FOR", "QUESTION", "COLON", "PRINT", "SEP", "EOS", "LBRACE", "RBRACE", 
			"LPAREN", "RPAREN", "FUNCTION", "RETURN", "NUMBER", "FLOAT", "INTEGER", 
			"ID", "STRING", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public escriptLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "escript.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\60\u010a\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f"+
		"\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\22\3"+
		"\22\3\22\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3"+
		"\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3"+
		"\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3 \3 \3!\3"+
		"!\3!\3!\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3(\3(\3(\3"+
		"(\3(\3(\3(\3)\3)\3)\3)\3)\3)\3)\3*\3*\5*\u00e6\n*\3+\3+\3+\3+\3,\6,\u00ed"+
		"\n,\r,\16,\u00ee\3-\6-\u00f2\n-\r-\16-\u00f3\3-\7-\u00f7\n-\f-\16-\u00fa"+
		"\13-\3.\3.\3.\3.\7.\u0100\n.\f.\16.\u0103\13.\3.\3.\3/\3/\3/\3/\3\u0101"+
		"\2\60\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60\3\2\6\3\2\62;\5\2C\\aac|\7"+
		"\2\62;C\\aac|~~\5\2\13\f\17\17\"\"\2\u010f\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2"+
		"\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M"+
		"\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2"+
		"\2\2\2[\3\2\2\2\2]\3\2\2\2\3_\3\2\2\2\5b\3\2\2\2\7e\3\2\2\2\th\3\2\2\2"+
		"\13k\3\2\2\2\rn\3\2\2\2\17q\3\2\2\2\21s\3\2\2\2\23v\3\2\2\2\25x\3\2\2"+
		"\2\27z\3\2\2\2\31|\3\2\2\2\33~\3\2\2\2\35\u0080\3\2\2\2\37\u0083\3\2\2"+
		"\2!\u0086\3\2\2\2#\u0088\3\2\2\2%\u008b\3\2\2\2\'\u008d\3\2\2\2)\u0090"+
		"\3\2\2\2+\u0093\3\2\2\2-\u0096\3\2\2\2/\u0098\3\2\2\2\61\u009d\3\2\2\2"+
		"\63\u00a3\3\2\2\2\65\u00a6\3\2\2\2\67\u00ae\3\2\2\29\u00b3\3\2\2\2;\u00b9"+
		"\3\2\2\2=\u00bd\3\2\2\2?\u00bf\3\2\2\2A\u00c1\3\2\2\2C\u00c7\3\2\2\2E"+
		"\u00c9\3\2\2\2G\u00cb\3\2\2\2I\u00cd\3\2\2\2K\u00cf\3\2\2\2M\u00d1\3\2"+
		"\2\2O\u00d3\3\2\2\2Q\u00dc\3\2\2\2S\u00e5\3\2\2\2U\u00e7\3\2\2\2W\u00ec"+
		"\3\2\2\2Y\u00f1\3\2\2\2[\u00fb\3\2\2\2]\u0106\3\2\2\2_`\5\31\r\2`a\5\17"+
		"\b\2a\4\3\2\2\2bc\5\33\16\2cd\5\17\b\2d\6\3\2\2\2ef\5\25\13\2fg\5\17\b"+
		"\2g\b\3\2\2\2hi\5\27\f\2ij\5\17\b\2j\n\3\2\2\2kl\5\23\n\2lm\5\17\b\2m"+
		"\f\3\2\2\2no\5\21\t\2op\5\17\b\2p\16\3\2\2\2qr\7?\2\2r\20\3\2\2\2st\7"+
		",\2\2tu\7,\2\2u\22\3\2\2\2vw\7\'\2\2w\24\3\2\2\2xy\7,\2\2y\26\3\2\2\2"+
		"z{\7\61\2\2{\30\3\2\2\2|}\7-\2\2}\32\3\2\2\2~\177\7/\2\2\177\34\3\2\2"+
		"\2\u0080\u0081\7(\2\2\u0081\u0082\7(\2\2\u0082\36\3\2\2\2\u0083\u0084"+
		"\7~\2\2\u0084\u0085\7~\2\2\u0085 \3\2\2\2\u0086\u0087\7@\2\2\u0087\"\3"+
		"\2\2\2\u0088\u0089\7@\2\2\u0089\u008a\7?\2\2\u008a$\3\2\2\2\u008b\u008c"+
		"\7>\2\2\u008c&\3\2\2\2\u008d\u008e\7>\2\2\u008e\u008f\7?\2\2\u008f(\3"+
		"\2\2\2\u0090\u0091\7?\2\2\u0091\u0092\7?\2\2\u0092*\3\2\2\2\u0093\u0094"+
		"\7#\2\2\u0094\u0095\7?\2\2\u0095,\3\2\2\2\u0096\u0097\7#\2\2\u0097.\3"+
		"\2\2\2\u0098\u0099\7v\2\2\u0099\u009a\7t\2\2\u009a\u009b\7w\2\2\u009b"+
		"\u009c\7g\2\2\u009c\60\3\2\2\2\u009d\u009e\7h\2\2\u009e\u009f\7c\2\2\u009f"+
		"\u00a0\7n\2\2\u00a0\u00a1\7u\2\2\u00a1\u00a2\7g\2\2\u00a2\62\3\2\2\2\u00a3"+
		"\u00a4\7k\2\2\u00a4\u00a5\7h\2\2\u00a5\64\3\2\2\2\u00a6\u00a7\7g\2\2\u00a7"+
		"\u00a8\7n\2\2\u00a8\u00a9\7u\2\2\u00a9\u00aa\7g\2\2\u00aa\u00ab\7\"\2"+
		"\2\u00ab\u00ac\7k\2\2\u00ac\u00ad\7h\2\2\u00ad\66\3\2\2\2\u00ae\u00af"+
		"\7g\2\2\u00af\u00b0\7n\2\2\u00b0\u00b1\7u\2\2\u00b1\u00b2\7g\2\2\u00b2"+
		"8\3\2\2\2\u00b3\u00b4\7y\2\2\u00b4\u00b5\7j\2\2\u00b5\u00b6\7k\2\2\u00b6"+
		"\u00b7\7n\2\2\u00b7\u00b8\7g\2\2\u00b8:\3\2\2\2\u00b9\u00ba\7h\2\2\u00ba"+
		"\u00bb\7q\2\2\u00bb\u00bc\7t\2\2\u00bc<\3\2\2\2\u00bd\u00be\7A\2\2\u00be"+
		">\3\2\2\2\u00bf\u00c0\7<\2\2\u00c0@\3\2\2\2\u00c1\u00c2\7r\2\2\u00c2\u00c3"+
		"\7t\2\2\u00c3\u00c4\7k\2\2\u00c4\u00c5\7p\2\2\u00c5\u00c6\7v\2\2\u00c6"+
		"B\3\2\2\2\u00c7\u00c8\7.\2\2\u00c8D\3\2\2\2\u00c9\u00ca\7=\2\2\u00caF"+
		"\3\2\2\2\u00cb\u00cc\7}\2\2\u00ccH\3\2\2\2\u00cd\u00ce\7\177\2\2\u00ce"+
		"J\3\2\2\2\u00cf\u00d0\7*\2\2\u00d0L\3\2\2\2\u00d1\u00d2\7+\2\2\u00d2N"+
		"\3\2\2\2\u00d3\u00d4\7h\2\2\u00d4\u00d5\7w\2\2\u00d5\u00d6\7p\2\2\u00d6"+
		"\u00d7\7e\2\2\u00d7\u00d8\7v\2\2\u00d8\u00d9\7k\2\2\u00d9\u00da\7q\2\2"+
		"\u00da\u00db\7p\2\2\u00dbP\3\2\2\2\u00dc\u00dd\7t\2\2\u00dd\u00de\7g\2"+
		"\2\u00de\u00df\7v\2\2\u00df\u00e0\7w\2\2\u00e0\u00e1\7t\2\2\u00e1\u00e2"+
		"\7p\2\2\u00e2R\3\2\2\2\u00e3\u00e6\5U+\2\u00e4\u00e6\5W,\2\u00e5\u00e3"+
		"\3\2\2\2\u00e5\u00e4\3\2\2\2\u00e6T\3\2\2\2\u00e7\u00e8\5W,\2\u00e8\u00e9"+
		"\7\60\2\2\u00e9\u00ea\5W,\2\u00eaV\3\2\2\2\u00eb\u00ed\t\2\2\2\u00ec\u00eb"+
		"\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef"+
		"X\3\2\2\2\u00f0\u00f2\t\3\2\2\u00f1\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2"+
		"\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f8\3\2\2\2\u00f5\u00f7"+
		"\t\4\2\2\u00f6\u00f5\3\2\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8"+
		"\u00f9\3\2\2\2\u00f9Z\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\u0101\7$\2\2\u00fc"+
		"\u00fd\7^\2\2\u00fd\u0100\7$\2\2\u00fe\u0100\13\2\2\2\u00ff\u00fc\3\2"+
		"\2\2\u00ff\u00fe\3\2\2\2\u0100\u0103\3\2\2\2\u0101\u0102\3\2\2\2\u0101"+
		"\u00ff\3\2\2\2\u0102\u0104\3\2\2\2\u0103\u0101\3\2\2\2\u0104\u0105\7$"+
		"\2\2\u0105\\\3\2\2\2\u0106\u0107\t\5\2\2\u0107\u0108\3\2\2\2\u0108\u0109"+
		"\b/\2\2\u0109^\3\2\2\2\13\2\u00e5\u00ee\u00f1\u00f3\u00f6\u00f8\u00ff"+
		"\u0101\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}