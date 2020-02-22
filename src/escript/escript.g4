grammar escript;

import elexertokens;

// Entry point for the Parser.
start: statement+;

// A single major unit of execution.
statement
	: LBRACE statement* RBRACE #StatementBlock // Allows us to use multiple statements in control blocks like for loops
	| ID SET (expression|condition) #StatementAssignment
	| IF condition statement (ELSEIF condition statement)* (ELSE statement)? #StatementBranch
	| WHILE condition statement #StatementWhile
	| FOR statement condition EOS expression statement #StatementFor
	| PRINT LPAREN condition RPAREN EOS #StatementPrint
	| condition EOS #StatementCondition; // Allows conditions (and expressions) to be evaluated without assigning them.

// A condition is something that can ultimately evaluate to either TRUE or FALSE.
// This includes conditions which return sub conditions.
// All values not an integer type and equivalent to 0 should be considered truthy, if compared somehow.
condition
	: LPAREN condition RPAREN #ConditionParenthesis
	| expression #ConditionExpression
	| NOT condition #ConditionInverted
	| left=expression op=(EQUALS|NOT_EQUALS) right=expression #ConditionComparison
	| left=expression op=(GREATER_THAN|LESS_THAN|GREATER_THAN_OR_EQUALS|LESS_THAN_OR_EQUALS|LESS_THAN) right=expression #ConditionComparison
	| left=condition op=(AND|OR|EQUALS|NOT_EQUALS) right=condition #ConditionLogic;

// An expression is a single minor unit of evaluation.
// e.g. 2 + 4
// An expression can be composed of sub expressions.
expression
	: left=expression op=POW right=expression #ExpressionBinary
	| left=expression op=(MUL|DIV|MOD) right=expression #ExpressionBinary
	| left=expression op=(ADD|SUB) right=expression #ExpressionBinary
	| (SUB|NOT)? val=(ID|NUMBER) #ExpressionValue
	| (SUB|NOT)? LPAREN (expression|condition) RPAREN #ExpressionParenthesis
	| ID SET expression #ExpressionAssignment // This is needed if we plan to support inline assignments a = (b = 2) + 1
	| (NOT)? val=(TRUE|FALSE) #ExpressionBoolean
	| val=STRING #ExpressionString;