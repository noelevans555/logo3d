package com.noelevans555.logo3d.compiler.program.instruction;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.program.parameter.Parameter;
import com.noelevans555.logo3d.compiler.program.parameter.result.Result;
import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.turtle.Turtle;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Creates a new variable with specified name and value (as determined by
 * parameter evaluation) in the program state.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Make implements Instruction {

    private final Boolean isLocalScope;
    private final String name;
    private final Parameter parameter;

    @Override
    public void run(final State state, final Turtle turtle) throws CompilerException {
        Result parameterValue = parameter.evaluate(state);
        state.store(name, parameterValue, isLocalScope);
    }

}
