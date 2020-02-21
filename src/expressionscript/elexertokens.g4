lexer grammar elexertokens;

// Common Expression Operations
POW: '**';
MOD: '%';
MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';
SET: '=';

// Boolean Operations
AND: '&&';
OR: '||';
GT: '>';
LT: '<';
GTE: '>=';
LTE: '<=';
EQUALS: '==';
NOTEQUALS: '!=';
NOT: '!';

// Boolean Tokens
TRUE: 'true';
FALSE: 'false';

// Flow Control Keywords
IF: 'if';
ELSEIF: 'else if';
ELSE: 'else';
WHILE: 'while';
FOR: 'for';
FUNCTION: 'function';
RETURN: 'return';
PRINT: 'print';

// Structure Tokens
SEP: ',';
EOS: ';';
LBRACE: '{';
RBRACE: '}';
LPAREN: '(';
RPAREN: ')';

NUMBER
    : FLOAT
    | INTEGER;

FLOAT: INTEGER '.' INTEGER;
INTEGER: [0-9]+;

// Identifier (used as variable names, function names, etc)
// Takes any character or _ in the english alphabet to start with, then any character, digit within the english language
// or the _ following the first character
ID: ([a-zA-Z]|'_')+([a-zA-Z0-9]|'_')*;
STRING: '"' .*? '"';

// Ignore whitespace
WS: [ \t\r\n] -> skip;