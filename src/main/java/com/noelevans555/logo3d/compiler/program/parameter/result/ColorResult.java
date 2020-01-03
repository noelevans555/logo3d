package com.noelevans555.logo3d.compiler.program.parameter.result;

import static com.noelevans555.logo3d.model.LogoColor.MAX_COMPONENT_VALUE;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.exception.InternalException;
import com.noelevans555.logo3d.compiler.exception.SemanticException;
import com.noelevans555.logo3d.model.LogoColor;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Represents the color evaluation result of a Logo3d parameter. Can be combined
 * with other color results to determine the result of larger expressions.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ColorResult extends EvaluationResult {

    private final LogoColor color;

    @Override
    public ColorResult negate() {
        return new ColorResult(new LogoColor(MAX_COMPONENT_VALUE - color.getRed(),
                MAX_COMPONENT_VALUE - color.getGreen(), MAX_COMPONENT_VALUE - color.getBlue()));
    }

    @Override
    public ColorResult combine(final String operator, final EvaluationResult operand) throws CompilerException {
        if (operand instanceof NumericResult) {
            throw new SemanticException(SemanticException.CANNOT_COMBINE_COLOR_AND_NUMERIC, operator);
        }
        LogoColor operandColor = operand.getColor(operator);
        switch (operator) {
        case "+":
            return new ColorResult(addColor(operandColor));
        case "-":
            return new ColorResult(subtractColor(operandColor));
        case "*":
            return new ColorResult(multiplyColor(operandColor));
        case "/":
            return new ColorResult(divideColor(operandColor));
        default:
            throw new InternalException(String.format("Invalid operator: '%s'", operator));
        }
    }

    /**
     * Computes a new color by adding the component values of the specified color to
     * the values of this color.
     *
     * @param operandColor Color whose component values to add to this color.
     * @return Color with added component values.
     */
    private LogoColor addColor(final LogoColor operandColor) {
        return new LogoColor(color.getRed() + operandColor.getRed(), color.getGreen() + operandColor.getGreen(),
                color.getBlue() + operandColor.getBlue());
    }

    /**
     * Computes a new color by subtracting the component values of the specified
     * color from the values of this color.
     *
     * @param operandColor Color whose component values to subtract from this color.
     * @return Color with subtracted component values.
     */
    private LogoColor subtractColor(final LogoColor operandColor) {
        return new LogoColor(color.getRed() - operandColor.getRed(), color.getGreen() - operandColor.getGreen(),
                color.getBlue() - operandColor.getBlue());
    }

    /**
     * Computes a new color by multiplying the component values of the specified
     * color with the values of this color.
     *
     * @param operandColor Color whose component values to multiply with this color.
     * @return Color with multiplied component values.
     */
    private LogoColor multiplyColor(final LogoColor operandColor) {
        return new LogoColor(color.getRed() * operandColor.getRed(), color.getGreen() * operandColor.getGreen(),
                color.getBlue() * operandColor.getBlue());
    }

    /**
     * Computes a new color by dividing the component values of this color by the
     * values of the specified color.
     *
     * @param operandColor Color whose component values to divide from this color.
     * @return Color with divided component values.
     */
    private LogoColor divideColor(final LogoColor operandColor) {
        return new LogoColor(color.getRed() / operandColor.getRed(), color.getGreen() / operandColor.getGreen(),
                color.getBlue() / operandColor.getBlue());
    }

    @Override
    public LogoColor getColor(final String commandName) {
        return color;
    }

}
