package com.noelevans555.logo3d.compiler.program.parameter;

import java.util.List;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.program.parameter.result.Result;
import com.noelevans555.logo3d.compiler.state.State;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Defines a arithmetic expression of Logo3d parameters, combined using plus,
 * minus, multiply and divide operators.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Expression implements Parameter {

    private final List<Parameter> operands;
    private final List<String> operators;

    @Override
    public Result evaluate(final State state) throws CompilerException {
        Result result = operands.get(0).evaluate(state);
        for (int i = 0; i < operators.size(); i++) {
            Result nextResult = operands.get(i + 1).evaluate(state);
            result = result.combine(operators.get(i), nextResult);
        }
        return result;
    }

}
