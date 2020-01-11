package com.noelevans555.logo3d.compiler.program.instruction;

import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.turtle.Turtle;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Records the turtle's current pose (location and orientation) using a
 * specified name in the program state.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Mark implements Instruction {

    private final Boolean isLocalScope;
    private final String name;

    @Override
    public void run(final State state, final Turtle turtle) {
        state.store(name, turtle.getPose(), isLocalScope);
    }

}
