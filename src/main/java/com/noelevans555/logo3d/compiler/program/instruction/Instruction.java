package com.noelevans555.logo3d.compiler.program.instruction;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.turtle.Turtle;

/**
 * An individual action that can be performed as part of a Logo3d program.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */
public interface Instruction {

    /**
     * Run the instruction.
     *
     * @param state State in which variables can be stored and resolved.
     * @param turtle Turtle to manipulate while running.
     * @throws CompilerException If the instruction fails to run to completion.
     */
    void run(State state, Turtle turtle) throws CompilerException;

}
