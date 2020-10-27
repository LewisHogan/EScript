// Generated from N:/University/Compilers/src/escript\escript.g4 by ANTLR 4.8
package escript;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link escriptParser}.
 */
public interface escriptListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link escriptParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(escriptParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link escriptParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(escriptParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementBlock}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementBlock(escriptParser.StatementBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementBlock}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementBlock(escriptParser.StatementBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementAssignment}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementAssignment(escriptParser.StatementAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementAssignment}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementAssignment(escriptParser.StatementAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementModSetVar}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementModSetVar(escriptParser.StatementModSetVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementModSetVar}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementModSetVar(escriptParser.StatementModSetVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementBranch}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementBranch(escriptParser.StatementBranchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementBranch}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementBranch(escriptParser.StatementBranchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementWhile}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementWhile(escriptParser.StatementWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementWhile}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementWhile(escriptParser.StatementWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementFor}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementFor(escriptParser.StatementForContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementFor}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementFor(escriptParser.StatementForContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementPrint}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementPrint(escriptParser.StatementPrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementPrint}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementPrint(escriptParser.StatementPrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementFunctionDeclaration}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementFunctionDeclaration(escriptParser.StatementFunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementFunctionDeclaration}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementFunctionDeclaration(escriptParser.StatementFunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementReturn}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementReturn(escriptParser.StatementReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementReturn}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementReturn(escriptParser.StatementReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementCondition}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementCondition(escriptParser.StatementConditionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementCondition}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementCondition(escriptParser.StatementConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementEOS}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementEOS(escriptParser.StatementEOSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementEOS}
	 * labeled alternative in {@link escriptParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementEOS(escriptParser.StatementEOSContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConditionComparison}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterConditionComparison(escriptParser.ConditionComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConditionComparison}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitConditionComparison(escriptParser.ConditionComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConditionTernary}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterConditionTernary(escriptParser.ConditionTernaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConditionTernary}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitConditionTernary(escriptParser.ConditionTernaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConditionExpression}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterConditionExpression(escriptParser.ConditionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConditionExpression}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitConditionExpression(escriptParser.ConditionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConditionInverted}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterConditionInverted(escriptParser.ConditionInvertedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConditionInverted}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitConditionInverted(escriptParser.ConditionInvertedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConditionLogic}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterConditionLogic(escriptParser.ConditionLogicContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConditionLogic}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitConditionLogic(escriptParser.ConditionLogicContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConditionParenthesis}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterConditionParenthesis(escriptParser.ConditionParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConditionParenthesis}
	 * labeled alternative in {@link escriptParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitConditionParenthesis(escriptParser.ConditionParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionBinary}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionBinary(escriptParser.ExpressionBinaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionBinary}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionBinary(escriptParser.ExpressionBinaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionValue}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionValue(escriptParser.ExpressionValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionValue}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionValue(escriptParser.ExpressionValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionBoolean}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionBoolean(escriptParser.ExpressionBooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionBoolean}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionBoolean(escriptParser.ExpressionBooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionModSetVar}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionModSetVar(escriptParser.ExpressionModSetVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionModSetVar}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionModSetVar(escriptParser.ExpressionModSetVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionParenthesis}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionParenthesis(escriptParser.ExpressionParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionParenthesis}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionParenthesis(escriptParser.ExpressionParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionAssignment}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionAssignment(escriptParser.ExpressionAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionAssignment}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionAssignment(escriptParser.ExpressionAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionFunctionCall}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionFunctionCall(escriptParser.ExpressionFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionFunctionCall}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionFunctionCall(escriptParser.ExpressionFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionString}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionString(escriptParser.ExpressionStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionString}
	 * labeled alternative in {@link escriptParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionString(escriptParser.ExpressionStringContext ctx);
}