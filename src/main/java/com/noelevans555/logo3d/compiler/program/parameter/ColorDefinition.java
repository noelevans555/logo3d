package com.noelevans555.logo3d.compiler.program.parameter;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.program.parameter.result.ColorResult;
import com.noelevans555.logo3d.compiler.program.parameter.result.Result;
import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.model.LogoColor;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Defines a Logo3d color by combining three parameters to compute respective
 * red, green and blue component strengths.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ColorDefinition implements Parameter {

    private static final String OPERATION = "DefineColor";

    private final boolean isNegated;
    private final Parameter redParameter;
    private final Parameter greenParameter;
    private final Parameter blueParameter;

    @Override
    public Result evaluate(final State state) throws CompilerException {
        return new ColorResult(new LogoColor(
                redParameter.evaluate(state).getNumeric(OPERATION),
                greenParameter.evaluate(state).getNumeric(OPERATION),
                blueParameter.evaluate(state).getNumeric(OPERATION)))
                    .conditionallyNegate(isNegated);
    }

}
