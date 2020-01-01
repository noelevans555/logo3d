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

    private static final int ONE_EIGHTY = 180;

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
            functionResult = Math.sin(degreesToRadians(parameterValue));
            break;
        case "cos":
            functionResult = Math.cos(degreesToRadians(parameterValue));
            break;
        case "tan":
            functionResult = Math.tan(degreesToRadians(parameterValue));
            break;
        case "asin":
            functionResult = radiansToDegrees(Math.asin(parameterValue));
            break;
        case "acos":
            functionResult = radiansToDegrees(Math.acos(parameterValue));
            break;
        case "atan":
            functionResult = radiansToDegrees(Math.atan(parameterValue));
            break;
        default:
            throw new InternalException("Unrecognized built-in function: " + functionName);
        }

        return new NumericResult(functionResult).conditionallyNegate(isNegated);
    }

    /**
     * Converts a value specified in degrees to the equivalent value expressed in
     * radians.
     *
     * @param degrees Value specified in degrees.
     * @return Equivalent value expressed in radians.
     */
    private double degreesToRadians(final double degrees) {
        return degrees * Math.PI / ONE_EIGHTY;
    }

    /**
     * Converts a value specified in radians to the equivalent value expressed in
     * degrees.
     *
     * @param radians Value specified in radians.
     * @return Equivalent value expressed in degrees.
     */
    private double radiansToDegrees(final double radians) {
        return radians * ONE_EIGHTY / Math.PI;
    }

}
