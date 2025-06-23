// Generated from D:/my/jquick-curl/jthornruleGrammer/json/JQuickJSON.g4 by ANTLR 4.13.2
package com.github.paohaijiao.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JQuickJSONParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JQuickJSONVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JQuickJSONParser#json}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJson(JQuickJSONParser.JsonContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(JQuickJSONParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONParser#object}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject(JQuickJSONParser.ObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONParser#pair}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPair(JQuickJSONParser.PairContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(JQuickJSONParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(JQuickJSONParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(JQuickJSONParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONParser#float}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloat(JQuickJSONParser.FloatContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONParser#double}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble(JQuickJSONParser.DoubleContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(JQuickJSONParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONParser#null}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull(JQuickJSONParser.NullContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONParser#date}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate(JQuickJSONParser.DateContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(JQuickJSONParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link JQuickJSONParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(JQuickJSONParser.IdentifierContext ctx);
}