package com.noelevans555.logo3d.compiler.program.instruction;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.exception.StopException;
import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.turtle.Turtle;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Stops execution of the current program scope.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Stop implements Instruction {

    @Override
    public void run(final State state, final Turtle turtle) throws CompilerException {
        throw new StopException();
    }

}
