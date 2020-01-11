package com.noelevans555.logo3d.compiler.program.parameter;

import java.util.List;
import java.util.Random;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.program.parameter.result.Result;
import com.noelevans555.logo3d.compiler.state.State;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A parameter containing a candidate pool of underlying parameters. Upon
 * evaluation, this parameter will delegate to a randomly chosen parameter from
 * the candidate pool to produce the result.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Pick implements Parameter {

    private final boolean isNegated;
    private final List<Parameter> parameters;

    @Override
    public Result evaluate(final State state) throws CompilerException {
        int randomParameterIndex = new Random().nextInt(parameters.size());
        Parameter chosenParameter = parameters.get(randomParameterIndex);
        return chosenParameter.evaluate(state).conditionallyNegate(isNegated);
    }

}
