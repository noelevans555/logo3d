package com.noelevans555.logo3d.compiler.program.instruction;

import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.turtle.Turtle;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Instructs the turtle to perform onward movements without recording lines.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PenUp implements Instruction {

    @Override
    public void run(final State state, final Turtle turtle) {
        turtle.setDrawing(false);
    }

}
