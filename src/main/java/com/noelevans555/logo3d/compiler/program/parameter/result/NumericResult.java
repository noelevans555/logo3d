package com.noelevans555.logo3d.compiler.program.parameter.result;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.exception.InternalException;
import com.noelevans555.logo3d.compiler.exception.SemanticException;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Represents the numeric evaluation result of a Logo3d parameter. Can be
 * combined with other numeric results to determine the result of larger
 * expressions.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class NumericResult extends EvaluationResult {

    private final double value;

    @Override
    public NumericResult negate() {
        return new NumericResult(value * -1);
    }

    @Override
    public NumericResult combine(final String operator, final EvaluationResult operand) throws CompilerException {
        if (operand instanceof ColorResult) {
            throw new SemanticException(SemanticException.CANNOT_COMBINE_COLOR_AND_NUMERIC, operator);
        }
        double operandValue = operand.getNumeric(operator);
        switch (operator) {
        case "+":
            return new NumericResult(value + operandValue);
        case "-":
            return new NumericResult(value - operandValue);
        case "*":
            return new NumericResult(value * operandValue);
        case "/":
            return new NumericResult(value / operandValue);
        default:
            throw new InternalException(String.format("Invalid operator: '%s'", operator));
        }
    }

    @Override
    public double getNumeric(final String commandName) {
        return value;
    }

}
