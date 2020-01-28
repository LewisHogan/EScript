grammar assignment;

import assignmenttokens;

start: statement+;

// A statement is basically an "action", code is a group of statements
statement: expression SEP | SEP;

expression:
            left=expression op=POW right=expression
            | left=expression op=(MUL|DIV) right=expression
            | left=expression op=(ADD|SUB) right=expression
            | VARIABLE op=SET right=expression
            | SUB? (NUMBER|VARIABLE)
            | VARIABLE
            | STRING
            | '(' expression ')';

//-3.14159+2*-4-5