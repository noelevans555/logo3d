package com.noelevans555.logo3d.compiler.program;

import java.util.List;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.program.instruction.Instruction;
import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.turtle.Turtle;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The root of an Abstract Syntax Tree (AST) which represents a valid Logo3d
 * program.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Program {

    private final List<Instruction> instructions;

    /**
     * Runs the program.
     *
     * @param state State in which variables can be stored and resolved.
     * @param turtle Turtle to manipulate while running the program.
     * @throws CompilerException If the program fails to run to completion.
     */
    public void run(final State state, final Turtle turtle) throws CompilerException {
        for (Instruction instruction : instructions) {
            instruction.run(state, turtle);
        }
    }

}
