grammar assignment;

import assignmenttokens;

start: statement+;

// A statement is basically an "action", code is a group of statements
statement: expression SEP | SEP;

expression:
            left=expression op=POW right=expression
            | op=SQRT right=expression
            | left=expression op=(MUL|DIV|MOD) right=expression
            | left=expression op=(ADD|SUB) right=expression
            | SUB? (NUMBER|VARIABLE)
            | VARIABLE
            | STRING
            | VARIABLE op=SET right=expression
            | '(' expression ')';

//-3.14159+2*-4-5