package com.noelevans555.logo3d;

import java.util.List;

import com.google.common.annotations.VisibleForTesting;
import com.noelevans555.logo3d.compiler.LogoCompiler;
import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.program.ProgramFactory;
import com.noelevans555.logo3d.compiler.program.instruction.InstructionFactory;
import com.noelevans555.logo3d.compiler.program.parameter.ParameterFactory;
import com.noelevans555.logo3d.compiler.program.tokens.Tokenizer;
import com.noelevans555.logo3d.compiler.state.StateFactory;
import com.noelevans555.logo3d.compiler.turtle.TurtleFactory;
import com.noelevans555.logo3d.model.LogoLine;

/**
 * A simple executable demonstrating how to construct and run the LogoCompiler.
 * The results are passed to the DemoVisualizer for display.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

public class DemoApplication {

    /**
     * Launches the demo application, constructing the LogoCompiler and running it
     * on an example Logo3d program before passing the results to the DemoVisualizer
     * for display.
     *
     * @param args Command line arguments (unused).
     * @throws CompilerException If the LogoCompiler is unable to compile the
     *         example Logo3d program.
     */
    public static void main(final String[] args) throws CompilerException {
        DemoApplication demoApplication = new DemoApplication();
        List<LogoLine> logoLines = demoApplication.compileAndRunProgram(DemoPrograms.GRID_SPHERE);
        DemoVisualizer demoVisualizer = new DemoVisualizer(logoLines);
        demoVisualizer.display();
    }

    /**
     * Constructs the LogoCompiler and runs it on the specified Logo3d program.
     *
     * @param program The Logo3d program to run.
     * @return The LogoLines produced by the Compiler when running the program.
     * @throws CompilerException If the LogoCompiler is unable to compile the
     *         specified Logo3d program.
     */
    @VisibleForTesting
    List<LogoLine> compileAndRunProgram(final String program) throws CompilerException {
        LogoCompiler logoCompiler = buildCompiler();
        return logoCompiler.compileAndRunProgram(program);
    }

    /**
     * Construct the Logo3d compiler. Replace this logic according to your project's
     * dependency injection framework.
     *
     * @return A Logo3d compiler which is ready to compile programs.
     */
    private LogoCompiler buildCompiler() {
        Tokenizer tokenizer = new Tokenizer();
        InstructionFactory instructionFactory = new InstructionFactory();
        ParameterFactory parameterFactory = new ParameterFactory();
        ProgramFactory programFactory = new ProgramFactory(instructionFactory, parameterFactory);
        StateFactory stateFactory = new StateFactory();
        TurtleFactory turtleFactory = new TurtleFactory();
        return new LogoCompiler(tokenizer, programFactory, stateFactory, turtleFactory);
    }

}
