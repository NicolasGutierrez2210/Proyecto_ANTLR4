// Generated from Comandos.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ComandosParser}.
 */
public interface ComandosListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ComandosParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ComandosParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComandosParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ComandosParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ComandosParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion(ComandosParser.InstruccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ComandosParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion(ComandosParser.InstruccionContext ctx);
}