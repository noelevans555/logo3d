package com.noelevans555.logo3d.compiler.program.parameter;

import static com.noelevans555.logo3d.model.LogoColor.MAX_COMPONENT_VALUE;

import java.util.Random;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.program.parameter.result.ColorResult;
import com.noelevans555.logo3d.compiler.program.parameter.result.EvaluationResult;
import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.model.LogoColor;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A parameter which upon evaluation generates a color with randomly chosen red,
 * green and blue component values.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RandomColor implements Parameter {

    private final boolean isNegated;

    @Override
    public EvaluationResult evaluate(final State state) throws CompilerException {
        Random random = new Random();
        return new ColorResult(new LogoColor(
                random.nextDouble() * MAX_COMPONENT_VALUE,
                random.nextDouble() * MAX_COMPONENT_VALUE,
                random.nextDouble() * MAX_COMPONENT_VALUE))
                        .conditionallyNegate(isNegated);
    }

}
