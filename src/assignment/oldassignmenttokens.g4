grammar oldassignmenttokens;

// Order of Operations: (POW, SQRT, MUL/DIV/MOD, ADD/SUB)
POW: '**';
SQRT: '//';
MOD: '%';
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

STRING: '"' .*? '"';

// A variable must start with a letter (or the _ character)
// and can then be followed by any combination of characters in the english alphabet
// or digits (or the _ character)
VARIABLE: ([a-z]|[A-Z]|'_')+ ([a-z]|[A-Z]|[0-9]|'_')*;

WS: [ \t\r\n] -> skip;

// Handles no input correctly -> returns nothing
// Handles malformed input correctly -> generates error
// Moves each statement onto a new line
// Puts exactly one space between each word/token

//2**-1