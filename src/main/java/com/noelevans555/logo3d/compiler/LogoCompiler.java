package com.noelevans555.logo3d.compiler;

import java.util.List;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.exception.StopException;
import com.noelevans555.logo3d.compiler.program.Program;
import com.noelevans555.logo3d.compiler.program.ProgramFactory;
import com.noelevans555.logo3d.compiler.program.tokens.TokenReader;
import com.noelevans555.logo3d.compiler.program.tokens.Tokenizer;
import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.state.StateFactory;
import com.noelevans555.logo3d.compiler.turtle.Turtle;
import com.noelevans555.logo3d.compiler.turtle.TurtleFactory;
import com.noelevans555.logo3d.model.LogoLine;

import lombok.AllArgsConstructor;

/**
 * The Logo3d compiler, responsible for translating a Logo3d program into the
 * lines traversed when the program is run.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
public class LogoCompiler {

    private final Tokenizer tokenizer;
    private final ProgramFactory programFactory;
    private final StateFactory stateFactory;
    private final TurtleFactory turtleFactory;

    /**
     * Compiles and runs the specified Logo3d program using default compiler limits.
     * The method returns the lines traversed during program execution.
     *
     * @param program A Logo3d program defined as a single string.
     * @return The lines traversed when running the Logo3d program.
     * @throws CompilerException If the program cannot be compiled or run.
     */
    public List<LogoLine> compileAndRunProgram(final String program) throws CompilerException {
        return compileAndRunProgram(program, RuntimeLimits.DEFAULT_LIMITS);
    }

    /**
     * Compiles and runs the specified Logo3d program, returning the lines traversed
     * during execution.
     *
     * @param program A Logo3d program defined as a single string.
     * @param runtimeLimits Limits which are not to be exceeded during compilation.
     * @return The lines traversed when running the Logo3d program.
     * @throws CompilerException If the program cannot be compiled or run.
     */
    public List<LogoLine> compileAndRunProgram(final String program, final RuntimeLimits runtimeLimits)
            throws CompilerException {
        TokenReader tokenReader = tokenizer.tokenize(program);
        Program compiledProgram = programFactory.buildProgram(tokenReader);
        State state = stateFactory.buildState();
        Turtle turtle = turtleFactory.buildTurtle();
        try {
            compiledProgram.run(state, turtle);
        } catch (StopException stop) {
            // Deliberately consumed. Thrown by stop commands encountered in the top-level
            // program.
        }
        return turtle.getLogoLines();
    }

}
