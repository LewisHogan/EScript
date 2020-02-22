lexer grammar elexertokens;

// Variable Operations
ADDSET: ADD SET;
SUBSET: SUB SET;
MULSET: MUL SET;
DIVSET: DIV SET;
SET: '=';

// Common Expression Operations
POW: '**';
MOD: '%';
MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';

// Boolean Operations
AND: '&&';
OR: '||';
GREATER_THAN: '>';
GREATER_THAN_OR_EQUALS: '>=';
LESS_THAN: '<';
LESS_THAN_OR_EQUALS: '<=';
EQUALS: '==';
NOT_EQUALS: '!=';
NOT: '!';

TRUE: 'true';
FALSE: 'false';

// Flow Control
IF: 'if';
ELSEIF: 'else if';
ELSE: 'else';
WHILE: 'while';
FOR: 'for';
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

// Identifier (used for variable names, possibly functions in the future, etc)
// Matches any character in the english alphabet (or number) or a _, with the exception that the first character
// is not allowed to be an underscore.
ID: ([a-zA-Z]|'_')+([a-zA-Z|0-9]|'_')*;

// Strings match absolutely every character between the quote marks.
// They are non greedy so the second they reach another quote, the string ends
// However quote characters can be included due to the special casing of the escaped quote character.
STRING: '"' ('\\"'|.)*? '"';

// Ignore all whitespace
WS: [ \t\r\n] -> skip;