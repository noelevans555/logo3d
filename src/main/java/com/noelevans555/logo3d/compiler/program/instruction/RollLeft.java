package com.noelevans555.logo3d.compiler.program.instruction;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.program.parameter.Parameter;
import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.turtle.Turtle;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Instructs the turtle to roll left by the angle specified by the instruction's
 * parameter.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RollLeft implements Instruction {

    private final Parameter parameter;

    @Override
    public void run(final State state, final Turtle turtle) throws CompilerException {
        double angle = parameter.evaluate(state).getNumeric(getClass().getSimpleName());
        turtle.rollLeft(angle);
    }

}
