package com.noelevans555.logo3d.compiler.program.instruction;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.turtle.Turtle;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Directly transports the turtle to a pose (location and orientation) recorded
 * with the specified name in the program state. The turtle does not record the
 * line traversed by the movement.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Goto implements Instruction {

    private final String name;

    @Override
    public void run(final State state, final Turtle turtle) throws CompilerException {
        turtle.setPose(state.retrievePose(name));
    }

}
