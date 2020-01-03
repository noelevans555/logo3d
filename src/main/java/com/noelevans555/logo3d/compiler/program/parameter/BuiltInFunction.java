package com.noelevans555.logo3d.compiler.program.parameter;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.exception.InternalException;
import com.noelevans555.logo3d.compiler.program.parameter.result.EvaluationResult;
import com.noelevans555.logo3d.compiler.program.parameter.result.NumericResult;
import com.noelevans555.logo3d.compiler.state.State;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Represents a mathematical function which is recognized by the compiler and
 * can be used to transform the result of Logo3d parameters. All built-in
 * trigonometric functions operate on values expressed in degrees.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BuiltInFunction implements Parameter {

    private final boolean isNegated;
    private final String functionName;
    private final Parameter parameter;

    @Override
    public EvaluationResult evaluate(final State state) throws CompilerException {

        double parameterValue = parameter.evaluate(state).getNumeric(functionName);

        double functionResult;
        switch (functionName.toLowerCase()) {
        case "random":
            functionResult = Math.random() * parameterValue;
            break;
        case "sqrt":
            functionResult = Math.sqrt(parameterValue);
            break;
        case "sin":
            functionResult = Math.sin(Math.toRadians(parameterValue));
            break;
        case "cos":
            functionResult = Math.cos(Math.toRadians(parameterValue));
            break;
        case "tan":
            functionResult = Math.tan(Math.toRadians(parameterValue));
            break;
        case "asin":
            functionResult = Math.toDegrees(Math.asin(parameterValue));
            break;
        case "acos":
            functionResult = Math.toDegrees(Math.acos(parameterValue));
            break;
        case "atan":
            functionResult = Math.toDegrees(Math.atan(parameterValue));
            break;
        default:
            throw new InternalException("Unrecognized built-in function: " + functionName);
        }

        return new NumericResult(functionResult).conditionallyNegate(isNegated);
    }

}
