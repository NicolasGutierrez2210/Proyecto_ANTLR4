grammar Comandos;

// --- REGLAS SINTÁCTICAS (comienzan con minúscula) ---
program     : instruccion+ EOF ;
instruccion : MOSTRAR ID
            | CARGAR ID
            | GRAFICAR ID
            ;

// --- REGLAS LÉXICAS (comienzan con mayúscula) ---
MOSTRAR  : 'mostrar' ;
CARGAR   : 'cargar' ;
GRAFICAR : 'graficar' ;

ID       : [a-zA-Z]+ ;
WS       : [ \t\r\n]+ -> skip ;
