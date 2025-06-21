// Generated from D:/idea/jthornruleGrammer/json/JQuickJSON.g4 by ANTLR 4.13.2
package com.github.paohaijiao.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class JQuickJSONLexer extends Lexer {
    static {
        RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int
            T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, NULL = 8, TRUE = 9,
            FALSE = 10, STRING = 11, ID = 12, NUMBER = 13, WS = 14;
    public static String[] channelNames = {
            "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
    };

    public static String[] modeNames = {
            "DEFAULT_MODE"
    };

    private static String[] makeRuleNames() {
        return new String[]{
                "T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "NULL", "TRUE",
                "FALSE", "STRING", "ID", "ESC", "UNICODE", "HEX", "SAFECODEPOINT", "NUMBER",
                "INT", "EXP", "WS"
        };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[]{
                null, "'{'", "','", "'}'", "':'", "'['", "']'", "'${'", "'null'", "'true'",
                "'false'"
        };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, null, null, null, null, null, null, null, "NULL", "TRUE", "FALSE",
                "STRING", "ID", "NUMBER", "WS"
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


    public JQuickJSONLexer(CharStream input) {
        super(input);
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @Override
    public String getGrammarFileName() {
        return "JQuickJSON.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public String[] getChannelNames() {
        return channelNames;
    }

    @Override
    public String[] getModeNames() {
        return modeNames;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public static final String _serializedATN =
            "\u0004\u0000\u000e\u008e\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002" +
                    "\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002" +
                    "\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002" +
                    "\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002" +
                    "\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e" +
                    "\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011" +
                    "\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000" +
                    "\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003" +
                    "\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006" +
                    "\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007" +
                    "\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001" +
                    "\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0005\nL\b\n\n\n\f\nO\t\n\u0001" +
                    "\n\u0001\n\u0001\u000b\u0001\u000b\u0005\u000bU\b\u000b\n\u000b\f\u000b" +
                    "X\t\u000b\u0001\f\u0001\f\u0001\f\u0003\f]\b\f\u0001\r\u0001\r\u0001\r" +
                    "\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f" +
                    "\u0001\u0010\u0003\u0010j\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010" +
                    "\u0004\u0010o\b\u0010\u000b\u0010\f\u0010p\u0003\u0010s\b\u0010\u0001" +
                    "\u0010\u0003\u0010v\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0005" +
                    "\u0011{\b\u0011\n\u0011\f\u0011~\t\u0011\u0003\u0011\u0080\b\u0011\u0001" +
                    "\u0012\u0001\u0012\u0003\u0012\u0084\b\u0012\u0001\u0012\u0001\u0012\u0001" +
                    "\u0013\u0004\u0013\u0089\b\u0013\u000b\u0013\f\u0013\u008a\u0001\u0013" +
                    "\u0001\u0013\u0000\u0000\u0014\u0001\u0001\u0003\u0002\u0005\u0003\u0007" +
                    "\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b" +
                    "\u0017\f\u0019\u0000\u001b\u0000\u001d\u0000\u001f\u0000!\r#\u0000%\u0000" +
                    "\'\u000e\u0001\u0000\n\u0003\u0000AZ__az\u0004\u000009AZ__az\b\u0000\"" +
                    "\"//\\\\bbffnnrrtt\u0003\u000009AFaf\u0003\u0000\u0000\u001f\"\"\\\\\u0001" +
                    "\u000009\u0001\u000019\u0002\u0000EEee\u0002\u0000++--\u0003\u0000\t\n" +
                    "\r\r  \u0093\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000" +
                    "\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000" +
                    "\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000" +
                    "\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000" +
                    "\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000" +
                    "\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000" +
                    "\u0000!\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0001" +
                    ")\u0001\u0000\u0000\u0000\u0003+\u0001\u0000\u0000\u0000\u0005-\u0001" +
                    "\u0000\u0000\u0000\u0007/\u0001\u0000\u0000\u0000\t1\u0001\u0000\u0000" +
                    "\u0000\u000b3\u0001\u0000\u0000\u0000\r5\u0001\u0000\u0000\u0000\u000f" +
                    "8\u0001\u0000\u0000\u0000\u0011=\u0001\u0000\u0000\u0000\u0013B\u0001" +
                    "\u0000\u0000\u0000\u0015H\u0001\u0000\u0000\u0000\u0017R\u0001\u0000\u0000" +
                    "\u0000\u0019Y\u0001\u0000\u0000\u0000\u001b^\u0001\u0000\u0000\u0000\u001d" +
                    "d\u0001\u0000\u0000\u0000\u001ff\u0001\u0000\u0000\u0000!i\u0001\u0000" +
                    "\u0000\u0000#\u007f\u0001\u0000\u0000\u0000%\u0081\u0001\u0000\u0000\u0000" +
                    "\'\u0088\u0001\u0000\u0000\u0000)*\u0005{\u0000\u0000*\u0002\u0001\u0000" +
                    "\u0000\u0000+,\u0005,\u0000\u0000,\u0004\u0001\u0000\u0000\u0000-.\u0005" +
                    "}\u0000\u0000.\u0006\u0001\u0000\u0000\u0000/0\u0005:\u0000\u00000\b\u0001" +
                    "\u0000\u0000\u000012\u0005[\u0000\u00002\n\u0001\u0000\u0000\u000034\u0005" +
                    "]\u0000\u00004\f\u0001\u0000\u0000\u000056\u0005$\u0000\u000067\u0005" +
                    "{\u0000\u00007\u000e\u0001\u0000\u0000\u000089\u0005n\u0000\u00009:\u0005" +
                    "u\u0000\u0000:;\u0005l\u0000\u0000;<\u0005l\u0000\u0000<\u0010\u0001\u0000" +
                    "\u0000\u0000=>\u0005t\u0000\u0000>?\u0005r\u0000\u0000?@\u0005u\u0000" +
                    "\u0000@A\u0005e\u0000\u0000A\u0012\u0001\u0000\u0000\u0000BC\u0005f\u0000" +
                    "\u0000CD\u0005a\u0000\u0000DE\u0005l\u0000\u0000EF\u0005s\u0000\u0000" +
                    "FG\u0005e\u0000\u0000G\u0014\u0001\u0000\u0000\u0000HM\u0005\"\u0000\u0000" +
                    "IL\u0003\u0019\f\u0000JL\u0003\u001f\u000f\u0000KI\u0001\u0000\u0000\u0000" +
                    "KJ\u0001\u0000\u0000\u0000LO\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000" +
                    "\u0000MN\u0001\u0000\u0000\u0000NP\u0001\u0000\u0000\u0000OM\u0001\u0000" +
                    "\u0000\u0000PQ\u0005\"\u0000\u0000Q\u0016\u0001\u0000\u0000\u0000RV\u0007" +
                    "\u0000\u0000\u0000SU\u0007\u0001\u0000\u0000TS\u0001\u0000\u0000\u0000" +
                    "UX\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000" +
                    "\u0000W\u0018\u0001\u0000\u0000\u0000XV\u0001\u0000\u0000\u0000Y\\\u0005" +
                    "\\\u0000\u0000Z]\u0007\u0002\u0000\u0000[]\u0003\u001b\r\u0000\\Z\u0001" +
                    "\u0000\u0000\u0000\\[\u0001\u0000\u0000\u0000]\u001a\u0001\u0000\u0000" +
                    "\u0000^_\u0005u\u0000\u0000_`\u0003\u001d\u000e\u0000`a\u0003\u001d\u000e" +
                    "\u0000ab\u0003\u001d\u000e\u0000bc\u0003\u001d\u000e\u0000c\u001c\u0001" +
                    "\u0000\u0000\u0000de\u0007\u0003\u0000\u0000e\u001e\u0001\u0000\u0000" +
                    "\u0000fg\b\u0004\u0000\u0000g \u0001\u0000\u0000\u0000hj\u0005-\u0000" +
                    "\u0000ih\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000jk\u0001\u0000" +
                    "\u0000\u0000kr\u0003#\u0011\u0000ln\u0005.\u0000\u0000mo\u0007\u0005\u0000" +
                    "\u0000nm\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000pn\u0001\u0000" +
                    "\u0000\u0000pq\u0001\u0000\u0000\u0000qs\u0001\u0000\u0000\u0000rl\u0001" +
                    "\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000su\u0001\u0000\u0000\u0000" +
                    "tv\u0003%\u0012\u0000ut\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000" +
                    "v\"\u0001\u0000\u0000\u0000w\u0080\u00050\u0000\u0000x|\u0007\u0006\u0000" +
                    "\u0000y{\u0007\u0005\u0000\u0000zy\u0001\u0000\u0000\u0000{~\u0001\u0000" +
                    "\u0000\u0000|z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}\u0080" +
                    "\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000\u007fw\u0001\u0000" +
                    "\u0000\u0000\u007fx\u0001\u0000\u0000\u0000\u0080$\u0001\u0000\u0000\u0000" +
                    "\u0081\u0083\u0007\u0007\u0000\u0000\u0082\u0084\u0007\b\u0000\u0000\u0083" +
                    "\u0082\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084" +
                    "\u0085\u0001\u0000\u0000\u0000\u0085\u0086\u0003#\u0011\u0000\u0086&\u0001" +
                    "\u0000\u0000\u0000\u0087\u0089\u0007\t\u0000\u0000\u0088\u0087\u0001\u0000" +
                    "\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u0088\u0001\u0000" +
                    "\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000\u008b\u008c\u0001\u0000" +
                    "\u0000\u0000\u008c\u008d\u0006\u0013\u0000\u0000\u008d(\u0001\u0000\u0000" +
                    "\u0000\r\u0000KMV\\ipru|\u007f\u0083\u008a\u0001\u0006\u0000\u0000";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}