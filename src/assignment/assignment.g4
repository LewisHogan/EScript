grammar assignment;

import assignmenttokens;

start: statement+;

// A statement is an individual unit of code.
// A statement can be made up of multiple statements.
// An if statement is compromised of a condition and a statement block
// to execute in the event of it being true
// there are also optional statement blocks for the any alternate conditions are true
// and if none of the conditions are true
// there is also a return expression statement
statement: LBRACE statement* RBRACE #StatementBlock
            | ID '=' (expression | condition) STATEMENTSEP #StatementAssignment
            | IF condition statement (ELSEIF condition statement)* (ELSE statement)? #StatementCondition
            | condition STATEMENTSEP #StatementConditionWithoutBranch
            | 'return' expression STATEMENTSEP #StatementReturn;

// An condition is a collection of expression's that must be evaluated according to the logical operands
condition: '(' condition ')' #ConditionBrackets
           | value=(TRUE|FALSE) #ConditionValue
           | expression #ConditionExpression
           | NOT condition #ConditionNot
           | left=condition op=EQUALS right=condition #ConditionOP
           | left=condition op=NOTEQUALS right=condition #ConditionOP
           | left=expression op=(GT | LT) right=expression #ConditionExpressionOP
           | left=expression op=(GTE | LTE) right=expression #ConditionExpressionOP
           | left=condition op=OR right=condition #ConditionOP
           | left=condition op=AND right=condition #ConditionOP;
// An expression is a collection of mathmetical operations
// in addition to variable identifiers and assignments
// unlike in statement, a variable being assigned does not require a seperator
// nor does it take a condition (that is resolved by the statement tree)
expression: left=expression op=POW right=expression #ExpressionOP
            | (SQRT) expression #ExpressionSQRT
            | left=expression op=(MUL | DIV | MOD) right=expression #ExpressionOP
            | left=expression op=(ADD | SUB) right=expression #ExpressionOP
            | SUB? val=(ID | NUMBER) #ExpressionValue
            | '(' (expression | condition) ')' #ExpressionBrackets
            | ID '=' (expression) #ExpressionAssignment
            | val=STRING #ExpressionValue;

// example code I want to work
// a=(b=2*3);
// a=2**b;
// a=3.0/2**3-1;
// name="hello world";

