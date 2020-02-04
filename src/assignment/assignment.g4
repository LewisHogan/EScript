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
statement: '{' statement* '}'
            | ID '=' (expression | condition) STATEMENTSEP
            | 'if' condition statement ('else if' condition statement)* ('else' statement)?
            | 'return' expression STATEMENTSEP;

// An condition is a collection of expression's that must be evaluated according to the logical operands
condition: '(' condition ')' #BCondition
           | expression #ExpressionCondition
           | NOT condition #NotCondition
           | condition EQUALS condition #EqualsCondition
           | condition NOTEQUALS condition #NotEqualsCondition
           | condition (GT | LT) condition #GTLTCondition
           | condition (GTE | LTE) condition #GTELTECondition
           | condition OR condition #OrCondition
           | condition AND condition #AndCondition;
// An expression is a collection of mathmetical operations
// in addition to variable identifiers and assignments
// unlike in statement, a variable being assigned does not require a seperator
// nor does it take a condition (that is resolved by the statement tree)
expression: expression (POW) expression #Pow
            | (SQRT) expression #Sqrt
            | expression op=(MUL | DIV | MOD) expression #MulOrDivOrMod
            | expression (ADD | SUB) expression #AddOrSub
            | SUB? (ID | NUMBER) #Value
            | '(' (expression | condition) ')' #BExpression
            | ID '=' (expression) #Assignment
            | SUB? VALUE #Value;

// example code I want to work
// a=(b=2*3)
// a=2**b
// a=3.0/2**3-1
// name="hello world"

