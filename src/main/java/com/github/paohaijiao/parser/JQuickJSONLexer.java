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
            T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
            NULL = 10, TRUE = 11, FALSE = 12, STRING = 13, ID = 14, DATETIMETYPE = 15, DATE = 16,
            NUMBER = 17, WS = 18;
    public static String[] channelNames = {
            "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
    };

    public static String[] modeNames = {
            "DEFAULT_MODE"
    };

    private static String[] makeRuleNames() {
        return new String[]{
                "T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8",
                "NULL", "TRUE", "FALSE", "STRING", "ID", "ESC", "UNICODE", "HEX", "SAFECODEPOINT",
                "DATETIMETYPE", "DATE", "TIME", "YEAR", "MONTH", "DAY", "HOUR", "MINUTE",
                "SECOND", "SPACE", "NUMBER", "INT", "EXP", "WS"
        };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[]{
                null, "'{'", "','", "'}'", "':'", "'['", "']'", "'f'", "'d'", "'${'",
                "'null'", "'true'", "'false'"
        };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[]{
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
            "\u0004\u0000\u0012\u00e9\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002" +
                    "\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002" +
                    "\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002" +
                    "\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002" +
                    "\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e" +
                    "\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011" +
                    "\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014" +
                    "\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017" +
                    "\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a" +
                    "\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d" +
                    "\u0002\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0001\u0000\u0001\u0000" +
                    "\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003" +
                    "\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006" +
                    "\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001" +
                    "\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b" +
                    "\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001" +
                    "\f\u0001\f\u0005\fh\b\f\n\f\f\fk\t\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005" +
                    "\fq\b\f\n\f\f\ft\t\f\u0001\f\u0003\fw\b\f\u0001\r\u0001\r\u0005\r{\b\r" +
                    "\n\r\f\r~\t\r\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u0083\b" +
                    "\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001" +
                    "\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012\u0001" +
                    "\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001" +
                    "\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001" +
                    "\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u009f\b\u0013\u0001\u0014\u0003" +
                    "\u0014\u00a2\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001" +
                    "\u0014\u0003\u0014\u00a9\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001" +
                    "\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0003\u0016\u00b2\b\u0016\u0001" +
                    "\u0017\u0001\u0017\u0003\u0017\u00b6\b\u0017\u0001\u0018\u0001\u0018\u0003" +
                    "\u0018\u00ba\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001" +
                    "\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001c\u0003\u001c\u00c5" +
                    "\b\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0004\u001c\u00ca\b\u001c" +
                    "\u000b\u001c\f\u001c\u00cb\u0003\u001c\u00ce\b\u001c\u0001\u001c\u0003" +
                    "\u001c\u00d1\b\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0005\u001d\u00d6" +
                    "\b\u001d\n\u001d\f\u001d\u00d9\t\u001d\u0003\u001d\u00db\b\u001d\u0001" +
                    "\u001e\u0001\u001e\u0003\u001e\u00df\b\u001e\u0001\u001e\u0001\u001e\u0001" +
                    "\u001f\u0004\u001f\u00e4\b\u001f\u000b\u001f\f\u001f\u00e5\u0001\u001f" +
                    "\u0001\u001f\u0000\u0000 \u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004" +
                    "\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017" +
                    "\f\u0019\r\u001b\u000e\u001d\u0000\u001f\u0000!\u0000#\u0000%\u000f\'" +
                    "\u0010)\u0000+\u0000-\u0000/\u00001\u00003\u00005\u00007\u00009\u0011" +
                    ";\u0000=\u0000?\u0012\u0001\u0000\r\u0002\u0000\"\"\\\\\u0002\u0000\'" +
                    "\'\\\\\u0003\u0000AZ__az\u0004\u000009AZ__az\b\u0000\"\"//\\\\bbffnnr" +
                    "rtt\u0003\u000009AFaf\u0003\u0000\u0000\u001f\"\"\\\\\u0001\u000009\u0002" +
                    "\u0000\t\t  \u0001\u000019\u0002\u0000EEee\u0002\u0000++--\u0003\u0000" +
                    "\t\n\r\r  \u00ef\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001" +
                    "\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001" +
                    "\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000" +
                    "\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000" +
                    "\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000" +
                    "\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000" +
                    "\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000" +
                    "\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000" +
                    "\u00009\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0001A" +
                    "\u0001\u0000\u0000\u0000\u0003C\u0001\u0000\u0000\u0000\u0005E\u0001\u0000" +
                    "\u0000\u0000\u0007G\u0001\u0000\u0000\u0000\tI\u0001\u0000\u0000\u0000" +
                    "\u000bK\u0001\u0000\u0000\u0000\rM\u0001\u0000\u0000\u0000\u000fO\u0001" +
                    "\u0000\u0000\u0000\u0011Q\u0001\u0000\u0000\u0000\u0013T\u0001\u0000\u0000" +
                    "\u0000\u0015Y\u0001\u0000\u0000\u0000\u0017^\u0001\u0000\u0000\u0000\u0019" +
                    "v\u0001\u0000\u0000\u0000\u001bx\u0001\u0000\u0000\u0000\u001d\u007f\u0001" +
                    "\u0000\u0000\u0000\u001f\u0084\u0001\u0000\u0000\u0000!\u008a\u0001\u0000" +
                    "\u0000\u0000#\u008c\u0001\u0000\u0000\u0000%\u008e\u0001\u0000\u0000\u0000" +
                    "\'\u009e\u0001\u0000\u0000\u0000)\u00a1\u0001\u0000\u0000\u0000+\u00aa" +
                    "\u0001\u0000\u0000\u0000-\u00af\u0001\u0000\u0000\u0000/\u00b3\u0001\u0000" +
                    "\u0000\u00001\u00b7\u0001\u0000\u0000\u00003\u00bb\u0001\u0000\u0000\u0000" +
                    "5\u00be\u0001\u0000\u0000\u00007\u00c1\u0001\u0000\u0000\u00009\u00c4" +
                    "\u0001\u0000\u0000\u0000;\u00da\u0001\u0000\u0000\u0000=\u00dc\u0001\u0000" +
                    "\u0000\u0000?\u00e3\u0001\u0000\u0000\u0000AB\u0005{\u0000\u0000B\u0002" +
                    "\u0001\u0000\u0000\u0000CD\u0005,\u0000\u0000D\u0004\u0001\u0000\u0000" +
                    "\u0000EF\u0005}\u0000\u0000F\u0006\u0001\u0000\u0000\u0000GH\u0005:\u0000" +
                    "\u0000H\b\u0001\u0000\u0000\u0000IJ\u0005[\u0000\u0000J\n\u0001\u0000" +
                    "\u0000\u0000KL\u0005]\u0000\u0000L\f\u0001\u0000\u0000\u0000MN\u0005f" +
                    "\u0000\u0000N\u000e\u0001\u0000\u0000\u0000OP\u0005d\u0000\u0000P\u0010" +
                    "\u0001\u0000\u0000\u0000QR\u0005$\u0000\u0000RS\u0005{\u0000\u0000S\u0012" +
                    "\u0001\u0000\u0000\u0000TU\u0005n\u0000\u0000UV\u0005u\u0000\u0000VW\u0005" +
                    "l\u0000\u0000WX\u0005l\u0000\u0000X\u0014\u0001\u0000\u0000\u0000YZ\u0005" +
                    "t\u0000\u0000Z[\u0005r\u0000\u0000[\\\u0005u\u0000\u0000\\]\u0005e\u0000" +
                    "\u0000]\u0016\u0001\u0000\u0000\u0000^_\u0005f\u0000\u0000_`\u0005a\u0000" +
                    "\u0000`a\u0005l\u0000\u0000ab\u0005s\u0000\u0000bc\u0005e\u0000\u0000" +
                    "c\u0018\u0001\u0000\u0000\u0000di\u0005\"\u0000\u0000eh\u0003\u001d\u000e" +
                    "\u0000fh\b\u0000\u0000\u0000ge\u0001\u0000\u0000\u0000gf\u0001\u0000\u0000" +
                    "\u0000hk\u0001\u0000\u0000\u0000ig\u0001\u0000\u0000\u0000ij\u0001\u0000" +
                    "\u0000\u0000jl\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000lw\u0005" +
                    "\"\u0000\u0000mr\u0005\'\u0000\u0000nq\u0003\u001d\u000e\u0000oq\b\u0001" +
                    "\u0000\u0000pn\u0001\u0000\u0000\u0000po\u0001\u0000\u0000\u0000qt\u0001" +
                    "\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000" +
                    "su\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000uw\u0005\'\u0000\u0000" +
                    "vd\u0001\u0000\u0000\u0000vm\u0001\u0000\u0000\u0000w\u001a\u0001\u0000" +
                    "\u0000\u0000x|\u0007\u0002\u0000\u0000y{\u0007\u0003\u0000\u0000zy\u0001" +
                    "\u0000\u0000\u0000{~\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000" +
                    "|}\u0001\u0000\u0000\u0000}\u001c\u0001\u0000\u0000\u0000~|\u0001\u0000" +
                    "\u0000\u0000\u007f\u0082\u0005\\\u0000\u0000\u0080\u0083\u0007\u0004\u0000" +
                    "\u0000\u0081\u0083\u0003\u001f\u000f\u0000\u0082\u0080\u0001\u0000\u0000" +
                    "\u0000\u0082\u0081\u0001\u0000\u0000\u0000\u0083\u001e\u0001\u0000\u0000" +
                    "\u0000\u0084\u0085\u0005u\u0000\u0000\u0085\u0086\u0003!\u0010\u0000\u0086" +
                    "\u0087\u0003!\u0010\u0000\u0087\u0088\u0003!\u0010\u0000\u0088\u0089\u0003" +
                    "!\u0010\u0000\u0089 \u0001\u0000\u0000\u0000\u008a\u008b\u0007\u0005\u0000" +
                    "\u0000\u008b\"\u0001\u0000\u0000\u0000\u008c\u008d\b\u0006\u0000\u0000" +
                    "\u008d$\u0001\u0000\u0000\u0000\u008e\u008f\u0003\'\u0013\u0000\u008f" +
                    "\u0090\u0005 \u0000\u0000\u0090\u0091\u0003)\u0014\u0000\u0091&\u0001" +
                    "\u0000\u0000\u0000\u0092\u0093\u0003+\u0015\u0000\u0093\u0094\u0005-\u0000" +
                    "\u0000\u0094\u0095\u0003-\u0016\u0000\u0095\u0096\u0005-\u0000\u0000\u0096" +
                    "\u0097\u0003/\u0017\u0000\u0097\u009f\u0001\u0000\u0000\u0000\u0098\u0099" +
                    "\u0003+\u0015\u0000\u0099\u009a\u0005/\u0000\u0000\u009a\u009b\u0003-" +
                    "\u0016\u0000\u009b\u009c\u0005/\u0000\u0000\u009c\u009d\u0003/\u0017\u0000" +
                    "\u009d\u009f\u0001\u0000\u0000\u0000\u009e\u0092\u0001\u0000\u0000\u0000" +
                    "\u009e\u0098\u0001\u0000\u0000\u0000\u009f(\u0001\u0000\u0000\u0000\u00a0" +
                    "\u00a2\u0005T\u0000\u0000\u00a1\u00a0\u0001\u0000\u0000\u0000\u00a1\u00a2" +
                    "\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a4" +
                    "\u00031\u0018\u0000\u00a4\u00a5\u0005:\u0000\u0000\u00a5\u00a8\u00033" +
                    "\u0019\u0000\u00a6\u00a7\u0005:\u0000\u0000\u00a7\u00a9\u00035\u001a\u0000" +
                    "\u00a8\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000" +
                    "\u00a9*\u0001\u0000\u0000\u0000\u00aa\u00ab\u0007\u0007\u0000\u0000\u00ab" +
                    "\u00ac\u0007\u0007\u0000\u0000\u00ac\u00ad\u0007\u0007\u0000\u0000\u00ad" +
                    "\u00ae\u0007\u0007\u0000\u0000\u00ae,\u0001\u0000\u0000\u0000\u00af\u00b1" +
                    "\u0007\u0007\u0000\u0000\u00b0\u00b2\u0007\u0007\u0000\u0000\u00b1\u00b0" +
                    "\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2.\u0001" +
                    "\u0000\u0000\u0000\u00b3\u00b5\u0007\u0007\u0000\u0000\u00b4\u00b6\u0007" +
                    "\u0007\u0000\u0000\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b5\u00b6\u0001" +
                    "\u0000\u0000\u0000\u00b60\u0001\u0000\u0000\u0000\u00b7\u00b9\u0007\u0007" +
                    "\u0000\u0000\u00b8\u00ba\u0007\u0007\u0000\u0000\u00b9\u00b8\u0001\u0000" +
                    "\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba2\u0001\u0000\u0000" +
                    "\u0000\u00bb\u00bc\u0007\u0007\u0000\u0000\u00bc\u00bd\u0007\u0007\u0000" +
                    "\u0000\u00bd4\u0001\u0000\u0000\u0000\u00be\u00bf\u0007\u0007\u0000\u0000" +
                    "\u00bf\u00c0\u0007\u0007\u0000\u0000\u00c06\u0001\u0000\u0000\u0000\u00c1" +
                    "\u00c2\u0007\b\u0000\u0000\u00c28\u0001\u0000\u0000\u0000\u00c3\u00c5" +
                    "\u0005-\u0000\u0000\u00c4\u00c3\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001" +
                    "\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000\u0000\u00c6\u00cd\u0003" +
                    ";\u001d\u0000\u00c7\u00c9\u0005.\u0000\u0000\u00c8\u00ca\u0007\u0007\u0000" +
                    "\u0000\u00c9\u00c8\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000" +
                    "\u0000\u00cb\u00c9\u0001\u0000\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000" +
                    "\u0000\u00cc\u00ce\u0001\u0000\u0000\u0000\u00cd\u00c7\u0001\u0000\u0000" +
                    "\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u00d0\u0001\u0000\u0000" +
                    "\u0000\u00cf\u00d1\u0003=\u001e\u0000\u00d0\u00cf\u0001\u0000\u0000\u0000" +
                    "\u00d0\u00d1\u0001\u0000\u0000\u0000\u00d1:\u0001\u0000\u0000\u0000\u00d2" +
                    "\u00db\u00050\u0000\u0000\u00d3\u00d7\u0007\t\u0000\u0000\u00d4\u00d6" +
                    "\u0007\u0007\u0000\u0000\u00d5\u00d4\u0001\u0000\u0000\u0000\u00d6\u00d9" +
                    "\u0001\u0000\u0000\u0000\u00d7\u00d5\u0001\u0000\u0000\u0000\u00d7\u00d8" +
                    "\u0001\u0000\u0000\u0000\u00d8\u00db\u0001\u0000\u0000\u0000\u00d9\u00d7" +
                    "\u0001\u0000\u0000\u0000\u00da\u00d2\u0001\u0000\u0000\u0000\u00da\u00d3" +
                    "\u0001\u0000\u0000\u0000\u00db<\u0001\u0000\u0000\u0000\u00dc\u00de\u0007" +
                    "\n\u0000\u0000\u00dd\u00df\u0007\u000b\u0000\u0000\u00de\u00dd\u0001\u0000" +
                    "\u0000\u0000\u00de\u00df\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000" +
                    "\u0000\u0000\u00e0\u00e1\u0003;\u001d\u0000\u00e1>\u0001\u0000\u0000\u0000" +
                    "\u00e2\u00e4\u0007\f\u0000\u0000\u00e3\u00e2\u0001\u0000\u0000\u0000\u00e4" +
                    "\u00e5\u0001\u0000\u0000\u0000\u00e5\u00e3\u0001\u0000\u0000\u0000\u00e5" +
                    "\u00e6\u0001\u0000\u0000\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000\u00e7" +
                    "\u00e8\u0006\u001f\u0000\u0000\u00e8@\u0001\u0000\u0000\u0000\u0016\u0000" +
                    "giprv|\u0082\u009e\u00a1\u00a8\u00b1\u00b5\u00b9\u00c4\u00cb\u00cd\u00d0" +
                    "\u00d7\u00da\u00de\u00e5\u0001\u0006\u0000\u0000";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}