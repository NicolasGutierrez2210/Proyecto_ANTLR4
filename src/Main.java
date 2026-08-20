import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        // 1. Leer el archivo de entrada
        String inputFile = "pruebas.txt";
        FileInputStream is = new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);

        // 2. Crear Lexer
        ComandosLexer lexer = new ComandosLexer(input);

        // 3. Obtener Flujo de Tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 4. Imprimir tokens reconocidos por el Lexer
        tokens.fill();
        System.out.println("=== TOKENS RECONOCIDOS ===");
        for (Token tok : tokens.getTokens()) {
            System.out.println(tok);
        }

        // 5. Crear Parser
        ComandosParser parser = new ComandosParser(tokens);
        ParseTree tree = parser.program(); // Regla inicial

        // 6. Imprimir Árbol Sintáctico en formato texto
        System.out.println("\n=== ÁRBOL SINTÁCTICO ===");
        System.out.println(tree.toStringTree(parser));
    }
}
