package com.noelevans555.logo3d.compiler.program.parameter;

import com.noelevans555.logo3d.compiler.exception.EntityReferenceException;
import com.noelevans555.logo3d.compiler.program.parameter.result.Result;
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
    public Result evaluate(final State state) throws EntityReferenceException {
        return state.retrieveResult(variableName).conditionallyNegate(isNegated);
    }

}
