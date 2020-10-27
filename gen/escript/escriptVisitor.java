// Generated from N:/University/Compilers/src/escript\escript.g4 by ANTLR 4.8
package escript;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link escriptParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface escriptVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link escriptParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(escriptParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementBlock}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementBlock(escriptParser.StatementBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementAssignment}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementAssignment(escriptParser.StatementAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementModSetVar}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementModSetVar(escriptParser.StatementModSetVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementBranch}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementBranch(escriptParser.StatementBranchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementWhile}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementWhile(escriptParser.StatementWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementFor}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementFor(escriptParser.StatementForContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementPrint}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementPrint(escriptParser.StatementPrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementFunctionDeclaration}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementFunctionDeclaration(escriptParser.StatementFunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementReturn}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementReturn(escriptParser.StatementReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementCondition}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementCondition(escriptParser.StatementConditionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementEOS}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementEOS(escriptParser.StatementEOSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConditionComparison}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionComparison(escriptParser.ConditionComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConditionTernary}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionTernary(escriptParser.ConditionTernaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConditionExpression}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionExpression(escriptParser.ConditionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConditionInverted}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionInverted(escriptParser.ConditionInvertedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConditionLogic}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionLogic(escriptParser.ConditionLogicContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConditionParenthesis}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionParenthesis(escriptParser.ConditionParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionBinary}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionBinary(escriptParser.ExpressionBinaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionValue}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionValue(escriptParser.ExpressionValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionBoolean}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionBoolean(escriptParser.ExpressionBooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionModSetVar}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionModSetVar(escriptParser.ExpressionModSetVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionParenthesis}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionParenthesis(escriptParser.ExpressionParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionAssignment}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAssignment(escriptParser.ExpressionAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionFunctionCall}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionFunctionCall(escriptParser.ExpressionFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionString}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionString(escriptParser.ExpressionStringContext ctx);
}