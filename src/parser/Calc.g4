grammar Calc;

// syntactic rules

program  : funcdef* body
         ;
funcdef : head '('body')'
         ;
head     : '(' func_id var_id* ')'
         ;
body     : var_def* expression EOF
         ;
var_def : var_id '=' expression
           ;
expression : LITERAL                                      # Literal
           | BOOL                                         # Boolean
           | var_id                                       # Variable
           | (MINUS | NOT) expression                     # UnaryMinus
           | <assoc = left> expression OPMAJOR expression # BinaryExpression
           | <assoc = left> expression (OPMINOR | MINUS) expression     # BinaryExpression
           | expression RELATIONAL expression             #BinaryExpression
           | <assoc = left> expression LOGICAL expression # BinaryExpression
           | <assoc = right>  expression '?' expression ':' expression   # ConditionalExpression
           |  func_id '(' expression* ')'                 # FunctionExpression
           | '(' expression ')'                           # ParExp

            ;


BOOL        : 'true' | 'false'
            ;
MINUS      : '-'
           ;
NOT        : '!'
           ;
var_id : IDENTIFIER
           ;
func_id : IDENTIFIER
           ;


// lexical rules

OPMINOR       : '+' | '&&' | '||'
;
OPMAJOR       : '*' | '/'  | '<'
         ;
RELATIONAL : '<=' | '<' | '>' | '>=' | '=='
            ;

LOGICAL : '&&' | '||'
        ;

IDENTIFIER : ('a'..'z')('a'..'z' | '0'..'9')*
         ;
LITERAL  : '0' | ('1'..'9')('0'..'9')*              
         ;
WS : [ \t\n\r]+ -> channel(HIDDEN) ;



