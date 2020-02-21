grammar escript;

import elexertokens;
// start Parse Tree is the entry point for any real visitors
start: statement+;

// A statement is a logical block of code that can be executed
// a statement can be composed of smaller statements
statement
    : LBRACE statement* RBRACE #StatementBlock
    | ID SET (expression | condition) EOS #StatementAssignment
    | IF condition statement (ELSEIF condition statement)* (ELSE statement)? #StatementBranch
    | WHILE condition statement #StatementWhile
    | FOR LPAREN statement condition EOS expression RPAREN statement #StatementFor
    | PRINT LPAREN condition RPAREN EOS #StatementPrint
    | condition EOS #StatementCondition // TODO: Double check if I ever actually want to use this
    | RETURN expression EOS #StatementReturn; // TODO: Decide if this goes in expression script or not

// A condition is something that can ultimately evaluate to either TRUE or FALSE
// this can include operations on sub conditions, and the use of logic like NOT, AND or OR
// all values not considered 0 are considered truthy (e.g. if (1) should resolve to true)
condition
    : LPAREN condition RPAREN #ConditionParens
    | expression #ConditionExpression
    | NOT condition #ConditionInverted
    | left=expression op=(EQUALS|NOTEQUALS) right=expression #ConditionComparison
    | left=expression op=(GT|GTE|LT|LTE) right=expression #ConditionComparison
    | left=condition op=(AND|OR|EQUALS|NOTEQUALS) right=condition #ConditionLogic;

array: '[' expression (SEP expression)* ']';

// An expression is a unit of evaluation
// common examples of expressions would be things like 3 + 2 or 2**4
// expressions can be composed of sub expressions
expression
    : left=expression op=POW right=expression #ExpressionUnary
    | left=expression op=(MUL|DIV|MOD) right=expression #ExpressionUnary
    | left=expression op=(ADD|SUB) right=expression #ExpressionUnary
    | (SUB|NOT)? val=(ID|NUMBER) #ExpressionValue //TODO: Should also be able to take a NUMBER here
    | SUB? LPAREN (expression|condition) RPAREN #ExpressionParens
    | ID SET expression #ExpressionAssignment // This one is needed so we can nest assignments inside expressions
    | val=(TRUE|FALSE) #ExpressionBoolean
    | val=array #ExpressionArray // This needs to go last otherwise it gets in the way of other rules
    | val=STRING #ExpressionValue; // This needs to go last otherwise it gets in the way of other rules
    // TODO: Double check STRING needs to be made its on rule at the end