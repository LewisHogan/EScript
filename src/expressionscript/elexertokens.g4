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

// Boolean Values
TRUE: 'true';
FALSE: 'false';

// Flow Control Keywords
IF: 'if';
ELSEIF: 'else if';
ELSE: 'else';
WHILE: 'while';
FOR: 'for';

// Structure Tokens
EOS: ';';
LBRACE: '{';
RBRACE: '}';
LPAREN: '(';
RPAREN: ')';

// Identifier (used as variable names, function names, etc)
// Takes any character or _ in the english alphabet to start with, then any character, digit within the english language
// or the _ following the first character
ID: ([a-zA-Z]|'_')+([a-zA-Z0-9])*;

// Ignore whitespace
WS: [ \t\r\n] -> skip;