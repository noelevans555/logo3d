package com.noelevans555.logo3d.compiler.program.instruction;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.exception.StopException;
import com.noelevans555.logo3d.compiler.program.parameter.Parameter;
import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.turtle.Turtle;
import com.noelevans555.logo3d.model.LogoColor;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Instructs the turtle to draw onward lines in the color returned by the
 * instruction's parameter.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SetColor implements Instruction {

    private final Parameter parameter;

    @Override
    public void run(final State state, final Turtle turtle) throws StopException, CompilerException {
        LogoColor color = parameter.evaluate(state).getColor(getClass().getSimpleName());
        turtle.setColor(color);
    }

}
