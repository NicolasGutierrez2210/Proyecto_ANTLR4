# Guía de ejecución del taller ANTLR 4

# Estructura del proyecto

```text
Proyecto_ANTLR4/
├── doc/
│   ├── arbol.png          # Imagen exportada del árbol sintáctico
│   └── arbol.ps           # Gráfico en PostScript
├── src/
│   ├── Comandos.g4        # Gramática fuente (Lexer y Parser)
│   ├── Main.java          # Programa principal
│   └── ...                # Archivos Java generados por ANTLR
├── tests/
│   ├── pruebas.txt        # Casos de prueba mixtos (válidos e inválidos)
│   └── validas.txt        # Casos de prueba válidos para la gráfica
├── .gitignore             
└── README.md              
## 1. Prerrequisitos y Configuración del Entorno

Antes de comenzar, asegúrate de tener instalados **Java JDK** y la herramienta **ANTLR 4**.

### Verificación de herramientas:
```bash
# 1. Verificar versión de Java
java -version

# 2. Verificar que la herramienta ANTLR esté accesible
antlr4
```
*(Si los comandos responden con sus versiones y opciones correspondientes, el entorno está listo.*

---

## 2. Preparación de los Archivos del Proyecto

Ubícate dentro de la carpeta principal del proyecto:
```bash
cd antlr_lab
```

Asegúrate de contar con los siguientes archivos base:

### A. Gramática (`Comandos.g4`)
```antlr
grammar Comandos;

// Regla inicial sintáctica
instruccion
    : MOSTRAR ID EOF
    | CARGAR ID EOF
    | GRAFICAR ID EOF
    ;

// Reglas léxicas (Tokens)
MOSTRAR  : 'mostrar' ;
CARGAR   : 'cargar' ;
GRAFICAR : 'graficar' ;
ID       : [a-zA-Z]+ ;
WS       : [ \t\r\n]+ -> skip ;
```

### B. Archivo de pruebas válidas (`entrada.txt`)
```text
mostrar ventas
```

---

## 3. Flujo de compilacion

El proceso estándar de ANTLR requiere generar el código fuente en Java a partir de la gramática y posteriormente compilar las clases generadas.

### Paso 1: Generar el lexer y parser
Ejecuta el generador de ANTLR sobre el archivo `.g4`100, 107]:
```bash
antlr4 Comandos.g4
```
*Esto generará automáticamente en tu directorio: `ComandosLexer.java`, `ComandosParser.java`, `ComandosListener.java`, `ComandosBaseListener.java`, además de archivos `.tokens`.*

### Paso 2: Compilar todos los archivos Java
Compila tanto los archivos generados como las dependencias de ANTLR:
```bash
javac Comandos*.java
```

---

## 4. Ejecución y Pruebas con `grun` (TestRig)

ANTLR incluye la herramienta de pruebas **TestRig** (invocada usualmente mediante el alias `grun`) para inspeccionar tokens y árboles sintácticos.

La sintaxis general es:
```bash
grun <NombreGramática> <ReglaInicial> [opciones] [archivoEntrada]
```

---

### Opción 1: Visualizar tokens reconocidos (`-tokens`)
Permite verificar la salida generada por el analizador léxico:
```bash
grun Comandos instruccion -tokens entrada.txt
```

**Salida esperada:**
```text
[@0,0:6='mostrar',<MOSTRAR>,1:0]
[@1,8:13='ventas',<ID>,1:8]
[@2,14:13='<EOF>',<EOF>,1:14]
```

---

### Opción 2: Visualizar arbol en formato texto LISP (`-tree`)
Permite comprobar la estructura jerárquica del parser en la terminal:
```bash
grun Comandos instruccion -tree entrada.txt
```

**Salida esperada:**
```text
(instruccion mostrar ventas <EOF>)
```

---

### Opción 3: Visualizar arbol gráfico (`-gui`)
Abre una ventana gráfica interactiva para ver el árbol visualmente:
```bash
grun Comandos instruccion -gui entrada.txt
```

---

## 5. Pruebas por consola (Casos válidos e inválidos)

Si deseas probar frases directamente desde la terminal sin necesidad de un archivo `.txt`:

```bash
grun Comandos instruccion -tree
```

1. Escribe la instrucción a evaluar (por ejemplo: `graficar ingresos` o `ventas mostrar`)146].
2. Presiona `Enter`.
3. Envía la señal de fin de archivo (**EOF**):
   * **Linux / macOS:** `Ctrl + D`
   * **Windows:** `Ctrl + Z` seguido de `Enter`

### Resumen de casos de prueba:

| Tipo de Caso | Entrada | Comportamiento Esperado |
| :--- | :--- | :--- |
| **Válido 1** | `mostrar ventas` | Reconocido exitosamente. Genera árbol `(instruccion mostrar ventas <EOF>)`. |
| **Válido 2** | `cargar datos` | Reconocido exitosamente. Genera árbol `(instruccion cargar datos <EOF>)`. |
| **Válido 3** | `graficar ingresos` | Reconocido exitosamente. Genera árbol `(instruccion graficar ingresos <EOF>)`. |
| **Inválido (Sintaxis)** | `ventas mostrar` | Error sintáctico: `extraneous input 'ventas' expecting {'mostrar', 'cargar', 'graficar'}`. |
| **Inválido (Léxico)** | `eliminar ventas` | Error léxico/sintáctico: el token `eliminar` no pertenece al vocabulario de comandos válidos. |
