package com.noelevans555.logo3d.compiler.program.parameter;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.program.parameter.result.Result;
import com.noelevans555.logo3d.compiler.program.parameter.result.NumericResult;
import com.noelevans555.logo3d.compiler.state.State;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Defines a numeric constant, the basis of all Logo3d parameters.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class NumericConstant implements Parameter {

    private final boolean isNegated;
    private final double value;

    /**
     * Constructor.
     *
     * @param value The value of the constant.
     */
    public NumericConstant(final double value) {
        this.isNegated = false;
        this.value = value;
    }

    @Override
    public Result evaluate(final State state) throws CompilerException {
        return new NumericResult(value).conditionallyNegate(isNegated);
    }

}
