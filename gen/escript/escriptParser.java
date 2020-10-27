// Generated from N:/University/Compilers/src/escript\escript.g4 by ANTLR 4.8
package escript;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class escriptParser extends Parser {
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
	public static final int
		RULE_start = 0, RULE_statement = 1, RULE_condition = 2, RULE_expression = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "statement", "condition", "expression"
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

	@Override
	public String getGrammarFileName() { return "escript.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public escriptParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(9); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(8);
				statement();
				}
				}
				setState(11); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUB) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << PRINT) | (1L << EOS) | (1L << LBRACE) | (1L << LPAREN) | (1L << FUNCTION) | (1L << RETURN) | (1L << NUMBER) | (1L << ID) | (1L << STRING))) != 0) );
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

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StatementForContext extends StatementContext {
		public TerminalNode FOR() { return getToken(escriptParser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(escriptParser.LPAREN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode EOS() { return getToken(escriptParser.EOS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(escriptParser.RPAREN, 0); }
		public StatementForContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterStatementFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitStatementFor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitStatementFor(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementEOSContext extends StatementContext {
		public TerminalNode EOS() { return getToken(escriptParser.EOS, 0); }
		public StatementEOSContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterStatementEOS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitStatementEOS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitStatementEOS(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementReturnContext extends StatementContext {
		public TerminalNode RETURN() { return getToken(escriptParser.RETURN, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode EOS() { return getToken(escriptParser.EOS, 0); }
		public StatementReturnContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterStatementReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitStatementReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitStatementReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementConditionContext extends StatementContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode EOS() { return getToken(escriptParser.EOS, 0); }
		public StatementConditionContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterStatementCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitStatementCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitStatementCondition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementWhileContext extends StatementContext {
		public TerminalNode WHILE() { return getToken(escriptParser.WHILE, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StatementWhileContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterStatementWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitStatementWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitStatementWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementModSetVarContext extends StatementContext {
		public Token left;
		public Token op;
		public ExpressionContext right;
		public TerminalNode EOS() { return getToken(escriptParser.EOS, 0); }
		public TerminalNode ID() { return getToken(escriptParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode MULSET() { return getToken(escriptParser.MULSET, 0); }
		public TerminalNode DIVSET() { return getToken(escriptParser.DIVSET, 0); }
		public TerminalNode MODSET() { return getToken(escriptParser.MODSET, 0); }
		public TerminalNode ADDSET() { return getToken(escriptParser.ADDSET, 0); }
		public TerminalNode SUBSET() { return getToken(escriptParser.SUBSET, 0); }
		public StatementModSetVarContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterStatementModSetVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitStatementModSetVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitStatementModSetVar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementPrintContext extends StatementContext {
		public TerminalNode PRINT() { return getToken(escriptParser.PRINT, 0); }
		public TerminalNode LPAREN() { return getToken(escriptParser.LPAREN, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(escriptParser.RPAREN, 0); }
		public TerminalNode EOS() { return getToken(escriptParser.EOS, 0); }
		public StatementPrintContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterStatementPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitStatementPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitStatementPrint(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementBlockContext extends StatementContext {
		public TerminalNode LBRACE() { return getToken(escriptParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(escriptParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementBlockContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterStatementBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitStatementBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitStatementBlock(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementAssignmentContext extends StatementContext {
		public TerminalNode ID() { return getToken(escriptParser.ID, 0); }
		public TerminalNode SET() { return getToken(escriptParser.SET, 0); }
		public TerminalNode EOS() { return getToken(escriptParser.EOS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public StatementAssignmentContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterStatementAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitStatementAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitStatementAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementBranchContext extends StatementContext {
		public TerminalNode IF() { return getToken(escriptParser.IF, 0); }
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> ELSEIF() { return getTokens(escriptParser.ELSEIF); }
		public TerminalNode ELSEIF(int i) {
			return getToken(escriptParser.ELSEIF, i);
		}
		public TerminalNode ELSE() { return getToken(escriptParser.ELSE, 0); }
		public StatementBranchContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterStatementBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitStatementBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitStatementBranch(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StatementFunctionDeclarationContext extends StatementContext {
		public TerminalNode FUNCTION() { return getToken(escriptParser.FUNCTION, 0); }
		public List<TerminalNode> ID() { return getTokens(escriptParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(escriptParser.ID, i);
		}
		public TerminalNode LPAREN() { return getToken(escriptParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(escriptParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(escriptParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(escriptParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> SEP() { return getTokens(escriptParser.SEP); }
		public TerminalNode SEP(int i) {
			return getToken(escriptParser.SEP, i);
		}
		public StatementFunctionDeclarationContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterStatementFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitStatementFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitStatementFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		int _la;
		try {
			int _alt;
			setState(114);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new StatementBlockContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(13);
				match(LBRACE);
				setState(17);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUB) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << PRINT) | (1L << EOS) | (1L << LBRACE) | (1L << LPAREN) | (1L << FUNCTION) | (1L << RETURN) | (1L << NUMBER) | (1L << ID) | (1L << STRING))) != 0)) {
					{
					{
					setState(14);
					statement();
					}
					}
					setState(19);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(20);
				match(RBRACE);
				}
				break;
			case 2:
				_localctx = new StatementAssignmentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(21);
				match(ID);
				setState(22);
				match(SET);
				setState(25);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(23);
					expression(0);
					}
					break;
				case 2:
					{
					setState(24);
					condition(0);
					}
					break;
				}
				setState(27);
				match(EOS);
				}
				break;
			case 3:
				_localctx = new StatementModSetVarContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(29);
				((StatementModSetVarContext)_localctx).left = match(ID);
				setState(30);
				((StatementModSetVarContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADDSET) | (1L << SUBSET) | (1L << MULSET) | (1L << DIVSET) | (1L << MODSET))) != 0)) ) {
					((StatementModSetVarContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(31);
				((StatementModSetVarContext)_localctx).right = expression(0);
				setState(32);
				match(EOS);
				}
				break;
			case 4:
				_localctx = new StatementBranchContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(34);
				match(IF);
				setState(35);
				condition(0);
				setState(36);
				statement();
				setState(43);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(37);
						match(ELSEIF);
						setState(38);
						condition(0);
						setState(39);
						statement();
						}
						} 
					}
					setState(45);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				}
				setState(48);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(46);
					match(ELSE);
					setState(47);
					statement();
					}
					break;
				}
				}
				break;
			case 5:
				_localctx = new StatementWhileContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(50);
				match(WHILE);
				setState(51);
				condition(0);
				setState(52);
				statement();
				}
				break;
			case 6:
				_localctx = new StatementForContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(54);
				match(FOR);
				setState(55);
				match(LPAREN);
				setState(56);
				statement();
				setState(57);
				condition(0);
				setState(58);
				match(EOS);
				setState(59);
				expression(0);
				setState(60);
				match(RPAREN);
				setState(61);
				statement();
				}
				break;
			case 7:
				_localctx = new StatementPrintContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(63);
				match(PRINT);
				setState(64);
				match(LPAREN);
				setState(65);
				condition(0);
				setState(66);
				match(RPAREN);
				setState(67);
				match(EOS);
				}
				break;
			case 8:
				_localctx = new StatementFunctionDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(69);
				match(FUNCTION);
				setState(70);
				match(ID);
				setState(71);
				match(LPAREN);
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(72);
					match(ID);
					setState(77);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SEP) {
						{
						{
						setState(73);
						match(SEP);
						setState(74);
						match(ID);
						}
						}
						setState(79);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(82);
				match(RPAREN);
				setState(83);
				match(LBRACE);
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUB) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << IF) | (1L << WHILE) | (1L << FOR) | (1L << PRINT) | (1L << EOS) | (1L << LBRACE) | (1L << LPAREN) | (1L << FUNCTION) | (1L << RETURN) | (1L << NUMBER) | (1L << ID) | (1L << STRING))) != 0)) {
					{
					{
					setState(84);
					statement();
					}
					}
					setState(89);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(90);
				match(RBRACE);
				}
				break;
			case 9:
				_localctx = new StatementFunctionDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(91);
				match(FUNCTION);
				setState(92);
				match(ID);
				setState(93);
				match(LPAREN);
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(94);
					match(ID);
					setState(99);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SEP) {
						{
						{
						setState(95);
						match(SEP);
						setState(96);
						match(ID);
						}
						}
						setState(101);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(104);
				match(RPAREN);
				setState(105);
				statement();
				}
				break;
			case 10:
				_localctx = new StatementReturnContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(106);
				match(RETURN);
				setState(107);
				condition(0);
				setState(108);
				match(EOS);
				}
				break;
			case 11:
				_localctx = new StatementConditionContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(110);
				condition(0);
				setState(111);
				match(EOS);
				}
				break;
			case 12:
				_localctx = new StatementEOSContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(113);
				match(EOS);
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

	public static class ConditionContext extends ParserRuleContext {
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
	 
		public ConditionContext() { }
		public void copyFrom(ConditionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ConditionComparisonContext extends ConditionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode EQUALS() { return getToken(escriptParser.EQUALS, 0); }
		public TerminalNode NOT_EQUALS() { return getToken(escriptParser.NOT_EQUALS, 0); }
		public TerminalNode GREATER_THAN() { return getToken(escriptParser.GREATER_THAN, 0); }
		public TerminalNode LESS_THAN() { return getToken(escriptParser.LESS_THAN, 0); }
		public TerminalNode GREATER_THAN_OR_EQUALS() { return getToken(escriptParser.GREATER_THAN_OR_EQUALS, 0); }
		public TerminalNode LESS_THAN_OR_EQUALS() { return getToken(escriptParser.LESS_THAN_OR_EQUALS, 0); }
		public ConditionComparisonContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterConditionComparison(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitConditionComparison(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitConditionComparison(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConditionTernaryContext extends ConditionContext {
		public ConditionContext left;
		public ConditionContext mid;
		public ConditionContext right;
		public TerminalNode QUESTION() { return getToken(escriptParser.QUESTION, 0); }
		public TerminalNode COLON() { return getToken(escriptParser.COLON, 0); }
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public ConditionTernaryContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterConditionTernary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitConditionTernary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitConditionTernary(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConditionExpressionContext extends ConditionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConditionExpressionContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterConditionExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitConditionExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitConditionExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConditionInvertedContext extends ConditionContext {
		public TerminalNode NOT() { return getToken(escriptParser.NOT, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public ConditionInvertedContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterConditionInverted(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitConditionInverted(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitConditionInverted(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConditionLogicContext extends ConditionContext {
		public ConditionContext left;
		public Token op;
		public ConditionContext right;
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public TerminalNode AND() { return getToken(escriptParser.AND, 0); }
		public TerminalNode OR() { return getToken(escriptParser.OR, 0); }
		public TerminalNode EQUALS() { return getToken(escriptParser.EQUALS, 0); }
		public TerminalNode NOT_EQUALS() { return getToken(escriptParser.NOT_EQUALS, 0); }
		public ConditionLogicContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterConditionLogic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitConditionLogic(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitConditionLogic(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConditionParenthesisContext extends ConditionContext {
		public TerminalNode LPAREN() { return getToken(escriptParser.LPAREN, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(escriptParser.RPAREN, 0); }
		public ConditionParenthesisContext(ConditionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterConditionParenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitConditionParenthesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitConditionParenthesis(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		return condition(0);
	}

	private ConditionContext condition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionContext _localctx = new ConditionContext(_ctx, _parentState);
		ConditionContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_condition, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				_localctx = new ConditionParenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(117);
				match(LPAREN);
				setState(118);
				condition(0);
				setState(119);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new ConditionExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(121);
				expression(0);
				}
				break;
			case 3:
				{
				_localctx = new ConditionInvertedContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122);
				match(NOT);
				setState(123);
				condition(5);
				}
				break;
			case 4:
				{
				_localctx = new ConditionComparisonContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(124);
				((ConditionComparisonContext)_localctx).left = expression(0);
				setState(125);
				((ConditionComparisonContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==EQUALS || _la==NOT_EQUALS) ) {
					((ConditionComparisonContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(126);
				((ConditionComparisonContext)_localctx).right = expression(0);
				}
				break;
			case 5:
				{
				_localctx = new ConditionComparisonContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128);
				((ConditionComparisonContext)_localctx).left = expression(0);
				setState(129);
				((ConditionComparisonContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER_THAN) | (1L << GREATER_THAN_OR_EQUALS) | (1L << LESS_THAN) | (1L << LESS_THAN_OR_EQUALS))) != 0)) ) {
					((ConditionComparisonContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(130);
				((ConditionComparisonContext)_localctx).right = expression(0);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(145);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(143);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new ConditionLogicContext(new ConditionContext(_parentctx, _parentState));
						((ConditionLogicContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(134);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(135);
						((ConditionLogicContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << EQUALS) | (1L << NOT_EQUALS))) != 0)) ) {
							((ConditionLogicContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(136);
						((ConditionLogicContext)_localctx).right = condition(3);
						}
						break;
					case 2:
						{
						_localctx = new ConditionTernaryContext(new ConditionContext(_parentctx, _parentState));
						((ConditionTernaryContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_condition);
						setState(137);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(138);
						match(QUESTION);
						setState(139);
						((ConditionTernaryContext)_localctx).mid = condition(0);
						setState(140);
						match(COLON);
						setState(141);
						((ConditionTernaryContext)_localctx).right = condition(2);
						}
						break;
					}
					} 
				}
				setState(147);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExpressionBinaryContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode POW() { return getToken(escriptParser.POW, 0); }
		public TerminalNode MUL() { return getToken(escriptParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(escriptParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(escriptParser.MOD, 0); }
		public TerminalNode ADD() { return getToken(escriptParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(escriptParser.SUB, 0); }
		public ExpressionBinaryContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterExpressionBinary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitExpressionBinary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitExpressionBinary(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionValueContext extends ExpressionContext {
		public Token val;
		public TerminalNode ID() { return getToken(escriptParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(escriptParser.NUMBER, 0); }
		public TerminalNode SUB() { return getToken(escriptParser.SUB, 0); }
		public TerminalNode NOT() { return getToken(escriptParser.NOT, 0); }
		public ExpressionValueContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterExpressionValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitExpressionValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitExpressionValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionBooleanContext extends ExpressionContext {
		public Token val;
		public TerminalNode TRUE() { return getToken(escriptParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(escriptParser.FALSE, 0); }
		public TerminalNode NOT() { return getToken(escriptParser.NOT, 0); }
		public ExpressionBooleanContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterExpressionBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitExpressionBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitExpressionBoolean(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionModSetVarContext extends ExpressionContext {
		public Token left;
		public Token op;
		public ExpressionContext right;
		public TerminalNode ID() { return getToken(escriptParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode MULSET() { return getToken(escriptParser.MULSET, 0); }
		public TerminalNode DIVSET() { return getToken(escriptParser.DIVSET, 0); }
		public TerminalNode MODSET() { return getToken(escriptParser.MODSET, 0); }
		public TerminalNode ADDSET() { return getToken(escriptParser.ADDSET, 0); }
		public TerminalNode SUBSET() { return getToken(escriptParser.SUBSET, 0); }
		public TerminalNode POWSET() { return getToken(escriptParser.POWSET, 0); }
		public ExpressionModSetVarContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterExpressionModSetVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitExpressionModSetVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitExpressionModSetVar(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionParenthesisContext extends ExpressionContext {
		public TerminalNode LPAREN() { return getToken(escriptParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(escriptParser.RPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode SUB() { return getToken(escriptParser.SUB, 0); }
		public TerminalNode NOT() { return getToken(escriptParser.NOT, 0); }
		public ExpressionParenthesisContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterExpressionParenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitExpressionParenthesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitExpressionParenthesis(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionAssignmentContext extends ExpressionContext {
		public TerminalNode ID() { return getToken(escriptParser.ID, 0); }
		public TerminalNode SET() { return getToken(escriptParser.SET, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionAssignmentContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterExpressionAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitExpressionAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitExpressionAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionFunctionCallContext extends ExpressionContext {
		public TerminalNode ID() { return getToken(escriptParser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(escriptParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(escriptParser.RPAREN, 0); }
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<TerminalNode> SEP() { return getTokens(escriptParser.SEP); }
		public TerminalNode SEP(int i) {
			return getToken(escriptParser.SEP, i);
		}
		public ExpressionFunctionCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterExpressionFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitExpressionFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitExpressionFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressionStringContext extends ExpressionContext {
		public Token val;
		public TerminalNode STRING() { return getToken(escriptParser.STRING, 0); }
		public ExpressionStringContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).enterExpressionString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof escriptListener ) ((escriptListener)listener).exitExpressionString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof escriptVisitor ) return ((escriptVisitor<? extends T>)visitor).visitExpressionString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				_localctx = new ExpressionModSetVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(149);
				((ExpressionModSetVarContext)_localctx).left = match(ID);
				setState(150);
				((ExpressionModSetVarContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADDSET) | (1L << SUBSET) | (1L << MULSET) | (1L << DIVSET) | (1L << MODSET) | (1L << POWSET))) != 0)) ) {
					((ExpressionModSetVarContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(151);
				((ExpressionModSetVarContext)_localctx).right = expression(7);
				}
				break;
			case 2:
				{
				_localctx = new ExpressionValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SUB || _la==NOT) {
					{
					setState(152);
					_la = _input.LA(1);
					if ( !(_la==SUB || _la==NOT) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(155);
				((ExpressionValueContext)_localctx).val = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==NUMBER || _la==ID) ) {
					((ExpressionValueContext)_localctx).val = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 3:
				{
				_localctx = new ExpressionParenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SUB || _la==NOT) {
					{
					setState(156);
					_la = _input.LA(1);
					if ( !(_la==SUB || _la==NOT) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(159);
				match(LPAREN);
				setState(162);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
				case 1:
					{
					setState(160);
					expression(0);
					}
					break;
				case 2:
					{
					setState(161);
					condition(0);
					}
					break;
				}
				setState(164);
				match(RPAREN);
				}
				break;
			case 4:
				{
				_localctx = new ExpressionAssignmentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(166);
				match(ID);
				setState(167);
				match(SET);
				setState(168);
				expression(4);
				}
				break;
			case 5:
				{
				_localctx = new ExpressionBooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(169);
					match(NOT);
					}
				}

				setState(172);
				((ExpressionBooleanContext)_localctx).val = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==TRUE || _la==FALSE) ) {
					((ExpressionBooleanContext)_localctx).val = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 6:
				{
				_localctx = new ExpressionFunctionCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(173);
				match(ID);
				setState(174);
				match(LPAREN);
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUB) | (1L << NOT) | (1L << TRUE) | (1L << FALSE) | (1L << LPAREN) | (1L << NUMBER) | (1L << ID) | (1L << STRING))) != 0)) {
					{
					setState(175);
					condition(0);
					setState(180);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SEP) {
						{
						{
						setState(176);
						match(SEP);
						setState(177);
						condition(0);
						}
						}
						setState(182);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(185);
				match(RPAREN);
				}
				break;
			case 7:
				{
				_localctx = new ExpressionStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(186);
				((ExpressionStringContext)_localctx).val = match(STRING);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(200);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(198);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionBinaryContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionBinaryContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(189);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(190);
						((ExpressionBinaryContext)_localctx).op = match(POW);
						setState(191);
						((ExpressionBinaryContext)_localctx).right = expression(11);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionBinaryContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionBinaryContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(192);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(193);
						((ExpressionBinaryContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MOD) | (1L << MUL) | (1L << DIV))) != 0)) ) {
							((ExpressionBinaryContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(194);
						((ExpressionBinaryContext)_localctx).right = expression(10);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionBinaryContext(new ExpressionContext(_parentctx, _parentState));
						((ExpressionBinaryContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(195);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(196);
						((ExpressionBinaryContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
							((ExpressionBinaryContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(197);
						((ExpressionBinaryContext)_localctx).right = expression(9);
						}
						break;
					}
					} 
				}
				setState(202);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return condition_sempred((ConditionContext)_localctx, predIndex);
		case 3:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean condition_sempred(ConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\60\u00ce\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\3\2\6\2\f\n\2\r\2\16\2\r\3\3\3\3\7\3\22\n\3\f"+
		"\3\16\3\25\13\3\3\3\3\3\3\3\3\3\3\3\5\3\34\n\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3,\n\3\f\3\16\3/\13\3\3\3\3\3\5\3\63"+
		"\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3N\n\3\f\3\16\3Q\13\3\5\3S\n\3\3"+
		"\3\3\3\3\3\7\3X\n\3\f\3\16\3[\13\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3d\n"+
		"\3\f\3\16\3g\13\3\5\3i\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3"+
		"u\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\5\4\u0087\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u0092\n\4\f\4\16"+
		"\4\u0095\13\4\3\5\3\5\3\5\3\5\3\5\5\5\u009c\n\5\3\5\3\5\5\5\u00a0\n\5"+
		"\3\5\3\5\3\5\5\5\u00a5\n\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00ad\n\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\7\5\u00b5\n\5\f\5\16\5\u00b8\13\5\5\5\u00ba\n\5\3\5"+
		"\3\5\5\5\u00be\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00c9\n\5\f"+
		"\5\16\5\u00cc\13\5\3\5\2\4\6\b\6\2\4\6\b\2\f\3\2\3\7\3\2\26\27\3\2\22"+
		"\25\4\2\20\21\26\27\3\2\3\b\4\2\17\17\30\30\4\2++..\3\2\31\32\3\2\13\r"+
		"\3\2\16\17\2\u00f3\2\13\3\2\2\2\4t\3\2\2\2\6\u0086\3\2\2\2\b\u00bd\3\2"+
		"\2\2\n\f\5\4\3\2\13\n\3\2\2\2\f\r\3\2\2\2\r\13\3\2\2\2\r\16\3\2\2\2\16"+
		"\3\3\2\2\2\17\23\7%\2\2\20\22\5\4\3\2\21\20\3\2\2\2\22\25\3\2\2\2\23\21"+
		"\3\2\2\2\23\24\3\2\2\2\24\26\3\2\2\2\25\23\3\2\2\2\26u\7&\2\2\27\30\7"+
		".\2\2\30\33\7\t\2\2\31\34\5\b\5\2\32\34\5\6\4\2\33\31\3\2\2\2\33\32\3"+
		"\2\2\2\34\35\3\2\2\2\35\36\7$\2\2\36u\3\2\2\2\37 \7.\2\2 !\t\2\2\2!\""+
		"\5\b\5\2\"#\7$\2\2#u\3\2\2\2$%\7\33\2\2%&\5\6\4\2&-\5\4\3\2\'(\7\34\2"+
		"\2()\5\6\4\2)*\5\4\3\2*,\3\2\2\2+\'\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2"+
		"\2\2.\62\3\2\2\2/-\3\2\2\2\60\61\7\35\2\2\61\63\5\4\3\2\62\60\3\2\2\2"+
		"\62\63\3\2\2\2\63u\3\2\2\2\64\65\7\36\2\2\65\66\5\6\4\2\66\67\5\4\3\2"+
		"\67u\3\2\2\289\7\37\2\29:\7\'\2\2:;\5\4\3\2;<\5\6\4\2<=\7$\2\2=>\5\b\5"+
		"\2>?\7(\2\2?@\5\4\3\2@u\3\2\2\2AB\7\"\2\2BC\7\'\2\2CD\5\6\4\2DE\7(\2\2"+
		"EF\7$\2\2Fu\3\2\2\2GH\7)\2\2HI\7.\2\2IR\7\'\2\2JO\7.\2\2KL\7#\2\2LN\7"+
		".\2\2MK\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2PS\3\2\2\2QO\3\2\2\2RJ\3"+
		"\2\2\2RS\3\2\2\2ST\3\2\2\2TU\7(\2\2UY\7%\2\2VX\5\4\3\2WV\3\2\2\2X[\3\2"+
		"\2\2YW\3\2\2\2YZ\3\2\2\2Z\\\3\2\2\2[Y\3\2\2\2\\u\7&\2\2]^\7)\2\2^_\7."+
		"\2\2_h\7\'\2\2`e\7.\2\2ab\7#\2\2bd\7.\2\2ca\3\2\2\2dg\3\2\2\2ec\3\2\2"+
		"\2ef\3\2\2\2fi\3\2\2\2ge\3\2\2\2h`\3\2\2\2hi\3\2\2\2ij\3\2\2\2jk\7(\2"+
		"\2ku\5\4\3\2lm\7*\2\2mn\5\6\4\2no\7$\2\2ou\3\2\2\2pq\5\6\4\2qr\7$\2\2"+
		"ru\3\2\2\2su\7$\2\2t\17\3\2\2\2t\27\3\2\2\2t\37\3\2\2\2t$\3\2\2\2t\64"+
		"\3\2\2\2t8\3\2\2\2tA\3\2\2\2tG\3\2\2\2t]\3\2\2\2tl\3\2\2\2tp\3\2\2\2t"+
		"s\3\2\2\2u\5\3\2\2\2vw\b\4\1\2wx\7\'\2\2xy\5\6\4\2yz\7(\2\2z\u0087\3\2"+
		"\2\2{\u0087\5\b\5\2|}\7\30\2\2}\u0087\5\6\4\7~\177\5\b\5\2\177\u0080\t"+
		"\3\2\2\u0080\u0081\5\b\5\2\u0081\u0087\3\2\2\2\u0082\u0083\5\b\5\2\u0083"+
		"\u0084\t\4\2\2\u0084\u0085\5\b\5\2\u0085\u0087\3\2\2\2\u0086v\3\2\2\2"+
		"\u0086{\3\2\2\2\u0086|\3\2\2\2\u0086~\3\2\2\2\u0086\u0082\3\2\2\2\u0087"+
		"\u0093\3\2\2\2\u0088\u0089\f\4\2\2\u0089\u008a\t\5\2\2\u008a\u0092\5\6"+
		"\4\5\u008b\u008c\f\3\2\2\u008c\u008d\7 \2\2\u008d\u008e\5\6\4\2\u008e"+
		"\u008f\7!\2\2\u008f\u0090\5\6\4\4\u0090\u0092\3\2\2\2\u0091\u0088\3\2"+
		"\2\2\u0091\u008b\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\7\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0097\b\5\1\2"+
		"\u0097\u0098\7.\2\2\u0098\u0099\t\6\2\2\u0099\u00be\5\b\5\t\u009a\u009c"+
		"\t\7\2\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2\2\2\u009d"+
		"\u00be\t\b\2\2\u009e\u00a0\t\7\2\2\u009f\u009e\3\2\2\2\u009f\u00a0\3\2"+
		"\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a4\7\'\2\2\u00a2\u00a5\5\b\5\2\u00a3"+
		"\u00a5\5\6\4\2\u00a4\u00a2\3\2\2\2\u00a4\u00a3\3\2\2\2\u00a5\u00a6\3\2"+
		"\2\2\u00a6\u00a7\7(\2\2\u00a7\u00be\3\2\2\2\u00a8\u00a9\7.\2\2\u00a9\u00aa"+
		"\7\t\2\2\u00aa\u00be\5\b\5\6\u00ab\u00ad\7\30\2\2\u00ac\u00ab\3\2\2\2"+
		"\u00ac\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00be\t\t\2\2\u00af\u00b0"+
		"\7.\2\2\u00b0\u00b9\7\'\2\2\u00b1\u00b6\5\6\4\2\u00b2\u00b3\7#\2\2\u00b3"+
		"\u00b5\5\6\4\2\u00b4\u00b2\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2"+
		"\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00ba\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9"+
		"\u00b1\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00be\7("+
		"\2\2\u00bc\u00be\7/\2\2\u00bd\u0096\3\2\2\2\u00bd\u009b\3\2\2\2\u00bd"+
		"\u009f\3\2\2\2\u00bd\u00a8\3\2\2\2\u00bd\u00ac\3\2\2\2\u00bd\u00af\3\2"+
		"\2\2\u00bd\u00bc\3\2\2\2\u00be\u00ca\3\2\2\2\u00bf\u00c0\f\f\2\2\u00c0"+
		"\u00c1\7\n\2\2\u00c1\u00c9\5\b\5\r\u00c2\u00c3\f\13\2\2\u00c3\u00c4\t"+
		"\n\2\2\u00c4\u00c9\5\b\5\f\u00c5\u00c6\f\n\2\2\u00c6\u00c7\t\13\2\2\u00c7"+
		"\u00c9\5\b\5\13\u00c8\u00bf\3\2\2\2\u00c8\u00c2\3\2\2\2\u00c8\u00c5\3"+
		"\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb"+
		"\t\3\2\2\2\u00cc\u00ca\3\2\2\2\31\r\23\33-\62ORYeht\u0086\u0091\u0093"+
		"\u009b\u009f\u00a4\u00ac\u00b6\u00b9\u00bd\u00c8\u00ca";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}