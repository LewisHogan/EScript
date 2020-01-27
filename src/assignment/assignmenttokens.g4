grammar assignmenttokens;

// Order of Operations: (POW, MUL/DIV, ADD/SUB)
POW: '**';
MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';
SET: '=';

// Separates statements
SEP: ';';

// Math stuff
NUMBER: INTEGER | FLOAT;
FLOAT: INTEGER '.' INTEGER;
INTEGER: [0-9]+;

STRING: '"' .* '"';

// A variable must start with a letter and can then be followed by any combination of characters in the english alphabet
// or digits
VARIABLE: ([a-z]|[A-Z]|'_')+ ([a-z]|[A-Z]|[0-9]|'_')*;

WS: [ \t\r\n] -> skip;