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

---

# Preguntas de Reglas

### 1. Explicación de las reglas léxicas utilizadas

El **analizador léxico** (*lexer*) lee la secuencia de caracteres del código fuente de entrada y la convierte en una secuencia de tokens o símbolos con significado gramatical[cite: 1].

* **`MOSTRAR`, `CARGAR`, `GRAFICAR`:** Reglas para palabras reservadas. Reconocen únicamente las cadenas de texto literales `'mostrar'`, `'cargar'` y `'graficar'`.
* **`ID`:** Regla para identificadores. Utiliza la expresión regular `[a-zA-Z]+` para reconocer palabras compuestas por una o más letras mayúsculas o minúsculas (por ejemplo: `ventas`, `clientes`, `ingresos`).
* **`WS`:** Regla para espacios en blanco. Reconoce espacios, tabulaciones y saltos de línea `[ \t\r\n]+`. Incluye la directiva `-> skip` para indicarle al lexer que los ignore y no los envíe al parser.

---

### 2. Explicación de las reglas sintácticas

El **analizador sintáctico** (*parser*) toma los tokens generados por el lexer y verifica que estén organizados según la estructura y jerarquía definidas en la gramática[cite: 1].

* **`program`:** Es la regla inicial del analizador sintáctico. Exige que el archivo de entrada contenga una o más instrucciones continuas (`instruccion+`) y finalice obligatoriamente con el símbolo de fin de archivo (`EOF`).
* **`instruccion`:** Regla que define la estructura válida de un comando. Mediante el operador de alternativa (`|`), establece que una instrucción debe estar compuesta por una de las tres palabras reservadas seguida de un identificador:
  * `MOSTRAR ID`
  * `CARGAR ID`
  * `GRAFICAR ID`

---

### 3. Identificación de al menos dos casos de error

1. **Caso 1: Error Sintáctico por Orden Incorrecto de Tokens**
   * **Entrada de prueba:** `ventas mostrar`
   * **Explicación:** Los tokens reconocidos (`ID` y `MOSTRAR`) son válidos individualmente por el lexer. Sin embargo, el parser rechaza la secuencia porque no coincide con ninguna alternativa de la regla `instruccion`, la cual espera siempre el verbo antes del objeto (`MOSTRAR ID`).

2. **Caso 2: Error Léxico / Sintáctico por Comando No Reconocido**
   * **Entrada de prueba:** `eliminar ventas`
   * **Explicación:** La palabra `eliminar` no coincide con ninguna de las palabras clave de la gramática (`MOSTRAR`, `CARGAR`, `GRAFICAR`). El lexer la categoriza genéricamente como un `ID`. Posteriormente, el parser recibe la secuencia `<ID> <ID>` (`eliminar ventas`), la cual no encaja en la estructura sintáctica de una `instruccion` y genera un error.

---

### 4. Conclusiones sobre la diferencia entre lexer y parser

| Característica | Analizador Léxico (*Lexer*) | Analizador Sintáctico (*Parser*) |
| :--- | :--- | :--- |
| **Entrada** | Secuencia o flujo de caracteres del código fuente[cite: 1]. | Flujo continuo de tokens generado por el lexer[cite: 1]. |
| **Salida** | Lista o flujo de tokens identificados[cite: 1]. | Árbol de análisis sintáctico (*Parse Tree*)[cite: 1]. |
| **Nivel de análisis** | Identifica componentes individuales y sus tipos (palabras reservadas, identificadores, números). | Verifica la relación y estructura jerárquica entre tokens según la gramática[cite: 1]. |
| **Reglas en ANTLR** | Comienzan con letra **mayúscula** (ej. `ID`, `MOSTRAR`). | Comienzan con letra **minúscula** (ej. `program`, `instruccion`). |
