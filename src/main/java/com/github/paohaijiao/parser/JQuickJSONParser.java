// Generated from D:/my/jquick-curl/jthornruleGrammer/json/JQuickJSON.g4 by ANTLR 4.13.2
package com.github.paohaijiao.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class JQuickJSONParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		NULL=10, TRUE=11, FALSE=12, STRING=13, ID=14, DATETIMETYPE=15, DATE=16, 
		NUMBER=17, WS=18;
	public static final int
		RULE_json = 0, RULE_value = 1, RULE_object = 2, RULE_pair = 3, RULE_array = 4, 
		RULE_string = 5, RULE_number = 6, RULE_float = 7, RULE_double = 8, RULE_bool = 9, 
		RULE_null = 10, RULE_date = 11, RULE_variable = 12, RULE_identifier = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"json", "value", "object", "pair", "array", "string", "number", "float", 
			"double", "bool", "null", "date", "variable", "identifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "','", "'}'", "':'", "'['", "']'", "'f'", "'d'", "'${'", 
			"'null'", "'true'", "'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "NULL", "TRUE", 
			"FALSE", "STRING", "ID", "DATETIMETYPE", "DATE", "NUMBER", "WS"
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
	public String getGrammarFileName() { return "JQuickJSON.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JQuickJSONParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JsonContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public JsonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_json; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).enterJson(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).exitJson(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickJSONVisitor ) return ((JQuickJSONVisitor<? extends T>)visitor).visitJson(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JsonContext json() throws RecognitionException {
		JsonContext _localctx = new JsonContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_json);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public FloatContext float_() {
			return getRuleContext(FloatContext.class,0);
		}
		public DoubleContext double_() {
			return getRuleContext(DoubleContext.class,0);
		}
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public NullContext null_() {
			return getRuleContext(NullContext.class,0);
		}
		public DateContext date() {
			return getRuleContext(DateContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickJSONVisitor ) return ((JQuickJSONVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_value);
		try {
			setState(40);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				object();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(31);
				array();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(32);
				string();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(33);
				number();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(34);
				float_();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(35);
				double_();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(36);
				bool();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(37);
				null_();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(38);
				date();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(39);
				variable();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ObjectContext extends ParserRuleContext {
		public List<PairContext> pair() {
			return getRuleContexts(PairContext.class);
		}
		public PairContext pair(int i) {
			return getRuleContext(PairContext.class,i);
		}
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).enterObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).exitObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickJSONVisitor ) return ((JQuickJSONVisitor<? extends T>)visitor).visitObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_object);
		int _la;
		try {
			setState(55);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				match(T__0);
				setState(43);
				pair();
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(44);
					match(T__1);
					setState(45);
					pair();
					}
					}
					setState(50);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(51);
				match(T__2);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(53);
				match(T__0);
				setState(54);
				match(T__2);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PairContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public PairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).enterPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).exitPair(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickJSONVisitor ) return ((JQuickJSONVisitor<? extends T>)visitor).visitPair(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			string();
			setState(58);
			match(T__3);
			setState(59);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).exitArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickJSONVisitor ) return ((JQuickJSONVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_array);
		int _la;
		try {
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				match(T__4);
				setState(62);
				value();
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(63);
					match(T__1);
					setState(64);
					value();
					}
					}
					setState(69);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(70);
				match(T__5);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				match(T__4);
				setState(73);
				match(T__5);
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

	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(JQuickJSONParser.STRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickJSONVisitor ) return ((JQuickJSONVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(STRING);
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

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(JQuickJSONParser.NUMBER, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickJSONVisitor ) return ((JQuickJSONVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(NUMBER);
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

	@SuppressWarnings("CheckReturnValue")
	public static class FloatContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(JQuickJSONParser.NUMBER, 0); }
		public FloatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_float; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).enterFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).exitFloat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickJSONVisitor ) return ((JQuickJSONVisitor<? extends T>)visitor).visitFloat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FloatContext float_() throws RecognitionException {
		FloatContext _localctx = new FloatContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_float);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(NUMBER);
			setState(81);
			match(T__6);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DoubleContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(JQuickJSONParser.NUMBER, 0); }
		public DoubleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).enterDouble(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).exitDouble(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickJSONVisitor ) return ((JQuickJSONVisitor<? extends T>)visitor).visitDouble(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoubleContext double_() throws RecognitionException {
		DoubleContext _localctx = new DoubleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_double);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(NUMBER);
			setState(84);
			match(T__7);
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

	@SuppressWarnings("CheckReturnValue")
	public static class BoolContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(JQuickJSONParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(JQuickJSONParser.FALSE, 0); }
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).exitBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickJSONVisitor ) return ((JQuickJSONVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_bool);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class NullContext extends ParserRuleContext {
		public TerminalNode NULL() { return getToken(JQuickJSONParser.NULL, 0); }
		public NullContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_null; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).enterNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).exitNull(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickJSONVisitor ) return ((JQuickJSONVisitor<? extends T>)visitor).visitNull(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NullContext null_() throws RecognitionException {
		NullContext _localctx = new NullContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_null);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(NULL);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DateContext extends ParserRuleContext {
		public TerminalNode DATE() { return getToken(JQuickJSONParser.DATE, 0); }
		public TerminalNode DATETIMETYPE() { return getToken(JQuickJSONParser.DATETIMETYPE, 0); }
		public DateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).enterDate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).exitDate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickJSONVisitor ) return ((JQuickJSONVisitor<? extends T>)visitor).visitDate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DateContext date() throws RecognitionException {
		DateContext _localctx = new DateContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_date);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			_la = _input.LA(1);
			if ( !(_la==DATETIMETYPE || _la==DATE) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class VariableContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickJSONVisitor ) return ((JQuickJSONVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(T__8);
			setState(93);
			identifier();
			setState(94);
			match(T__2);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(JQuickJSONParser.ID, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JQuickJSONListener ) ((JQuickJSONListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JQuickJSONVisitor ) return ((JQuickJSONVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(ID);
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
		"\u0004\u0001\u0012c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001)\b\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0005\u0002/\b\u0002\n\u0002\f\u00022\t\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00028\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0005\u0004B\b\u0004\n\u0004\f\u0004E\t\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004K\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001"+
		"\r\u0000\u0000\u000e\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u0000\u0002\u0001\u0000\u000b\f\u0001\u0000\u000f\u0010"+
		"a\u0000\u001c\u0001\u0000\u0000\u0000\u0002(\u0001\u0000\u0000\u0000\u0004"+
		"7\u0001\u0000\u0000\u0000\u00069\u0001\u0000\u0000\u0000\bJ\u0001\u0000"+
		"\u0000\u0000\nL\u0001\u0000\u0000\u0000\fN\u0001\u0000\u0000\u0000\u000e"+
		"P\u0001\u0000\u0000\u0000\u0010S\u0001\u0000\u0000\u0000\u0012V\u0001"+
		"\u0000\u0000\u0000\u0014X\u0001\u0000\u0000\u0000\u0016Z\u0001\u0000\u0000"+
		"\u0000\u0018\\\u0001\u0000\u0000\u0000\u001a`\u0001\u0000\u0000\u0000"+
		"\u001c\u001d\u0003\u0002\u0001\u0000\u001d\u0001\u0001\u0000\u0000\u0000"+
		"\u001e)\u0003\u0004\u0002\u0000\u001f)\u0003\b\u0004\u0000 )\u0003\n\u0005"+
		"\u0000!)\u0003\f\u0006\u0000\")\u0003\u000e\u0007\u0000#)\u0003\u0010"+
		"\b\u0000$)\u0003\u0012\t\u0000%)\u0003\u0014\n\u0000&)\u0003\u0016\u000b"+
		"\u0000\')\u0003\u0018\f\u0000(\u001e\u0001\u0000\u0000\u0000(\u001f\u0001"+
		"\u0000\u0000\u0000( \u0001\u0000\u0000\u0000(!\u0001\u0000\u0000\u0000"+
		"(\"\u0001\u0000\u0000\u0000(#\u0001\u0000\u0000\u0000($\u0001\u0000\u0000"+
		"\u0000(%\u0001\u0000\u0000\u0000(&\u0001\u0000\u0000\u0000(\'\u0001\u0000"+
		"\u0000\u0000)\u0003\u0001\u0000\u0000\u0000*+\u0005\u0001\u0000\u0000"+
		"+0\u0003\u0006\u0003\u0000,-\u0005\u0002\u0000\u0000-/\u0003\u0006\u0003"+
		"\u0000.,\u0001\u0000\u0000\u0000/2\u0001\u0000\u0000\u00000.\u0001\u0000"+
		"\u0000\u000001\u0001\u0000\u0000\u000013\u0001\u0000\u0000\u000020\u0001"+
		"\u0000\u0000\u000034\u0005\u0003\u0000\u000048\u0001\u0000\u0000\u0000"+
		"56\u0005\u0001\u0000\u000068\u0005\u0003\u0000\u00007*\u0001\u0000\u0000"+
		"\u000075\u0001\u0000\u0000\u00008\u0005\u0001\u0000\u0000\u00009:\u0003"+
		"\n\u0005\u0000:;\u0005\u0004\u0000\u0000;<\u0003\u0002\u0001\u0000<\u0007"+
		"\u0001\u0000\u0000\u0000=>\u0005\u0005\u0000\u0000>C\u0003\u0002\u0001"+
		"\u0000?@\u0005\u0002\u0000\u0000@B\u0003\u0002\u0001\u0000A?\u0001\u0000"+
		"\u0000\u0000BE\u0001\u0000\u0000\u0000CA\u0001\u0000\u0000\u0000CD\u0001"+
		"\u0000\u0000\u0000DF\u0001\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000"+
		"FG\u0005\u0006\u0000\u0000GK\u0001\u0000\u0000\u0000HI\u0005\u0005\u0000"+
		"\u0000IK\u0005\u0006\u0000\u0000J=\u0001\u0000\u0000\u0000JH\u0001\u0000"+
		"\u0000\u0000K\t\u0001\u0000\u0000\u0000LM\u0005\r\u0000\u0000M\u000b\u0001"+
		"\u0000\u0000\u0000NO\u0005\u0011\u0000\u0000O\r\u0001\u0000\u0000\u0000"+
		"PQ\u0005\u0011\u0000\u0000QR\u0005\u0007\u0000\u0000R\u000f\u0001\u0000"+
		"\u0000\u0000ST\u0005\u0011\u0000\u0000TU\u0005\b\u0000\u0000U\u0011\u0001"+
		"\u0000\u0000\u0000VW\u0007\u0000\u0000\u0000W\u0013\u0001\u0000\u0000"+
		"\u0000XY\u0005\n\u0000\u0000Y\u0015\u0001\u0000\u0000\u0000Z[\u0007\u0001"+
		"\u0000\u0000[\u0017\u0001\u0000\u0000\u0000\\]\u0005\t\u0000\u0000]^\u0003"+
		"\u001a\r\u0000^_\u0005\u0003\u0000\u0000_\u0019\u0001\u0000\u0000\u0000"+
		"`a\u0005\u000e\u0000\u0000a\u001b\u0001\u0000\u0000\u0000\u0005(07CJ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}