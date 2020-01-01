package com.noelevans555.logo3d.compiler.program.parameter;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.program.parameter.result.EvaluationResult;
import com.noelevans555.logo3d.compiler.state.State;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A parameter which looks up its realtime value by name from the program state.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class VariableReference implements Parameter {

    private final boolean isNegated;
    private final String variableName;

    @Override
    public EvaluationResult evaluate(final State state) throws CompilerException {
        return state.resolve(variableName).conditionallyNegate(isNegated);
    }

}
