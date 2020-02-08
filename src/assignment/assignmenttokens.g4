lexer grammar assignmenttokens;

// Order of Arithmetic Operations: (POW, SQRT, MUL/DIV/MOD, ADD/SUB)
POW: '**';
SQRT: '//';
MOD: '%';
MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';
SET: '=';

// Order of boolean comparisons (AND, OR, GREATER THAN/LESS THAN, EQUALS, NOT)
AND: '&&';
OR: '||';
GT: '>';
LT: '<';
GTE: '>=';
LTE: '<=';
EQUALS: '==';
NOTEQUALS: '!=';
NOT: '!';

IF: 'if';
ELSEIF: 'else if';
ELSE: 'else';

STATEMENTSEP: ';';

// An ID must start with a letter (or the _ character)
// and can then be followed by any combination of characters in the english alphabet
// or digits (or the _ character)
ID: ([a-z]|[A-Z]|'_')+ ([a-z]|[A-Z]|[0-9]|'_')*;

NUMBER: FLOAT | INTEGER;
FLOAT: INTEGER '.' INTEGER;
INTEGER: '0' | [1-9][0-9]*;

STRING: '"' .*? '"';

WS: [ \t\r\n] -> skip;