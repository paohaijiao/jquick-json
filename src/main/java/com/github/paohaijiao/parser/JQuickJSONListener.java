// Generated from D:/idea/jthornruleGrammer/json/JQuickJSON.g4 by ANTLR 4.13.2
package com.github.paohaijiao.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JQuickJSONParser}.
 */
public interface JQuickJSONListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link JQuickJSONParser#json}.
     *
     * @param ctx the parse tree
     */
    void enterJson(JQuickJSONParser.JsonContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickJSONParser#json}.
     *
     * @param ctx the parse tree
     */
    void exitJson(JQuickJSONParser.JsonContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickJSONParser#value}.
     *
     * @param ctx the parse tree
     */
    void enterValue(JQuickJSONParser.ValueContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickJSONParser#value}.
     *
     * @param ctx the parse tree
     */
    void exitValue(JQuickJSONParser.ValueContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickJSONParser#object}.
     *
     * @param ctx the parse tree
     */
    void enterObject(JQuickJSONParser.ObjectContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickJSONParser#object}.
     *
     * @param ctx the parse tree
     */
    void exitObject(JQuickJSONParser.ObjectContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickJSONParser#pair}.
     *
     * @param ctx the parse tree
     */
    void enterPair(JQuickJSONParser.PairContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickJSONParser#pair}.
     *
     * @param ctx the parse tree
     */
    void exitPair(JQuickJSONParser.PairContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickJSONParser#array}.
     *
     * @param ctx the parse tree
     */
    void enterArray(JQuickJSONParser.ArrayContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickJSONParser#array}.
     *
     * @param ctx the parse tree
     */
    void exitArray(JQuickJSONParser.ArrayContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickJSONParser#string}.
     *
     * @param ctx the parse tree
     */
    void enterString(JQuickJSONParser.StringContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickJSONParser#string}.
     *
     * @param ctx the parse tree
     */
    void exitString(JQuickJSONParser.StringContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickJSONParser#number}.
     *
     * @param ctx the parse tree
     */
    void enterNumber(JQuickJSONParser.NumberContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickJSONParser#number}.
     *
     * @param ctx the parse tree
     */
    void exitNumber(JQuickJSONParser.NumberContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickJSONParser#bool}.
     *
     * @param ctx the parse tree
     */
    void enterBool(JQuickJSONParser.BoolContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickJSONParser#bool}.
     *
     * @param ctx the parse tree
     */
    void exitBool(JQuickJSONParser.BoolContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickJSONParser#null}.
     *
     * @param ctx the parse tree
     */
    void enterNull(JQuickJSONParser.NullContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickJSONParser#null}.
     *
     * @param ctx the parse tree
     */
    void exitNull(JQuickJSONParser.NullContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickJSONParser#variable}.
     *
     * @param ctx the parse tree
     */
    void enterVariable(JQuickJSONParser.VariableContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickJSONParser#variable}.
     *
     * @param ctx the parse tree
     */
    void exitVariable(JQuickJSONParser.VariableContext ctx);

    /**
     * Enter a parse tree produced by {@link JQuickJSONParser#identifier}.
     *
     * @param ctx the parse tree
     */
    void enterIdentifier(JQuickJSONParser.IdentifierContext ctx);

    /**
     * Exit a parse tree produced by {@link JQuickJSONParser#identifier}.
     *
     * @param ctx the parse tree
     */
    void exitIdentifier(JQuickJSONParser.IdentifierContext ctx);
}