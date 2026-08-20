# Preguntas de analisis- ANTLR4

### 1. ¿Cuál es la diferencia entre un lexema y un token?
* **Lexema:** Es la palabra o texto exacto tal cual como lo escribimos en el código (por ejemplo, el texto `mostrar`, `ventas` o el número `105`).
* **Token:** Es la categoría o etiqueta que le pone el analizador para saber de qué tipo es (por ejemplo, `MOSTRAR`, `ID` o `ENTERO`).

---

### 2. ¿Cuál es la responsabilidad del lexer?
Su trabajo es leer el archivo de entrada carácter por carácter y armar paquetitos con sentido llamados tokens. De paso, se deshace de todo lo que no nos interesa para la sintaxis, como los espacios en blanco, tabulaciones y saltos de línea.

---

### 3. ¿Cuál es la responsabilidad del parser?
Recibe la lista de tokens que le pasa el lexer y revisa si están ordenados según las reglas de nuestra gramática. Si todo cuadra y tiene sentido, arma el árbol sintáctico (el árbol con la estructura del programa).

---

### 4. ¿Por qué las reglas léxicas comienzan con mayúscula en ANTLR?
Es la convención que exige ANTLR para identificar que esa regla le pertenece al lexer y sirve para crear un token (por ejemplo, `ID`, `CARGAR` o `WS`).

---

### 5. ¿Por qué las reglas sintácticas comienzan con minúscula?
Porque es la forma en que ANTLR sabe que la regla es del parser. Estas reglas definen la estructura y cómo se van a combinar los tokens y otras reglas (por ejemplo, `instruccion`, `expr` o `message`).

---

### 6. ¿Cuál es la función de `-> skip`?
Le dice al lexer que cuando encuentre ese patrón (como espacios o saltos de línea), simplemente lo ignore y lo bote a la basura, sin generar ningún token ni enviárselo al parser.

---

### 7. ¿Qué representa `EOF`?
Significa *End of File* (Fin de archivo). Se pone al final de la regla principal para asegurarnos de que el parser lea todo el archivo completo y no deje cosas sueltas o sin revisar al final.

---

### 8. ¿Qué información representa un árbol sintáctico?
Muestra gráficamente cómo se estructuran las instrucciones del código. En las puntas u hojas quedan las palabras y valores reales (tokens), y en las ramas intermedias se ve qué reglas gramaticales se fueron aplicando para armarlo.

---

### 9. ¿Cuál es la diferencia entre Listener y Visitor?
* **Listener:** ANTLR recorre todo el árbol de forma automática por nosotros y va disparando funciones cuando entra o sale de cada regla.
* **Visitor:** Nosotros tenemos el control total; decidimos manualmente a qué ramas del árbol entrar y cuándo visitarlas usando métodos como `visit()`.

---

### 10. ¿Cómo podría utilizarse ANTLR para construir un lenguaje de dominio específico (DSL)?
Primero escribimos un archivo `.g4` con los comandos propios que queremos para nuestra herramienta (por ejemplo, órdenes para bases de datos o para un robot). Luego hacemos que ANTLR genere el analizador y, usando un Visitor o Listener, programamos qué código o acción real debe ejecutarse con cada comando.
