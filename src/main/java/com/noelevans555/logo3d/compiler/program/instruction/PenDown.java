package com.noelevans555.logo3d.compiler.program.instruction;

import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.turtle.Turtle;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Instructs the turtle to record the lines traversed during onward movements.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PenDown implements Instruction {

    @Override
    public void run(final State state, final Turtle turtle) {
        turtle.setDrawing(true);
    }

}
