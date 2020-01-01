package com.noelevans555.logo3d.compiler.program.parameter.result;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.noelevans555.logo3d.compiler.exception.InternalException;
import com.noelevans555.logo3d.compiler.exception.SemanticException;
import com.noelevans555.logo3d.model.LogoColor;

public class ColorResultTest {

    private static final double DELTA = 1.0E-9;

    @Test
    public void conditionallyNegate_whenPositive_returnsNegatedResult() throws Exception {
        ColorResult result = makeColorResult(30.0, 72.2, 254.9);
        assertColorsAreEqual(new LogoColor(225.0, 182.8, 0.1), result.conditionallyNegate(true).getColor("test"));
    }

    @Test
    public void conditionallyNegate_whenNegative_returnsUnchangedResult() throws Exception {
        ColorResult result = makeColorResult(132.1, 145.4, 92.7);
        assertColorsAreEqual(new LogoColor(132.1, 145.4, 92.7), result.conditionallyNegate(false).getColor("test"));
    }

    @Test
    public void combine_whenAdd_returnsAddedResult() throws Exception {
        ColorResult result = makeColorResult(152.8, 251.5, 81.3);
        EvaluationResult operand = makeColorResult(23.5, 1.4, 46.1);
        assertColorsAreEqual(new LogoColor(176.3, 252.9, 127.4), result.combine("+", operand).getColor("test"));
    }

    @Test
    public void combine_whenSubtract_returnsSubtractedResult() throws Exception {
        ColorResult result = makeColorResult(46.5, 218.3, 195.4);
        EvaluationResult operand = makeColorResult(24.4, 115.1, 37.8);
        assertColorsAreEqual(new LogoColor(22.1, 103.2, 157.6), result.combine("-", operand).getColor("test"));
    }

    @Test
    public void combine_whenMultiply_returnsMultipliedResult() throws Exception {
        ColorResult result = makeColorResult(25.2, 184.8, 12.4);
        EvaluationResult operand = makeColorResult(8.5, 0.5, 10.25);
        assertColorsAreEqual(new LogoColor(214.2, 92.4, 127.1), result.combine("*", operand).getColor("test"));
    }

    @Test
    public void combine_whenDivide_returnsDividedResult() throws Exception {
        ColorResult result = makeColorResult(150.4, 107.1, 229.06);
        EvaluationResult operand = makeColorResult(4.0, 8.5, 1.3);
        assertColorsAreEqual(new LogoColor(37.6, 12.6, 176.2), result.combine("/", operand).getColor("test"));
    }

    @Test(expected = SemanticException.class)
    public void combine_withNumeric_throwsSemanticException() throws Exception {
        ColorResult result = makeColorResult(53.3, 13.1, 82.5);
        EvaluationResult operand = new NumericResult(5.262);
        result.combine("+", operand);
    }

    @Test(expected = InternalException.class)
    public void combine_withUnrecognizedOperator_throwsInternalException() throws Exception {
        ColorResult result = makeColorResult(30.05, 72, 99);
        EvaluationResult operand = makeColorResult(1.12, 52.12, 61.7);
        result.combine("|", operand);
    }

    @Test(expected = SemanticException.class)
    public void getNumeric_throwsSemanticException() throws Exception {
        EvaluationResult result = makeColorResult(20, 60, 90);
        result.getNumeric("forward");
    }

    @Test
    public void getColor_returnsColor() throws Exception {
        EvaluationResult result = makeColorResult(20, 60, 90);
        assertColorsAreEqual(new LogoColor(20, 60, 90), result.getColor("setcolor"));
    }

    private ColorResult makeColorResult(final double red, final double green, final double blue) {
        return new ColorResult(new LogoColor(red, green, blue));
    }

    private void assertColorsAreEqual(final LogoColor expected, final LogoColor actual) {
        assertEquals("unequal red", expected.getRed(), actual.getRed(), DELTA);
        assertEquals("unequal green", expected.getGreen(), actual.getGreen(), DELTA);
        assertEquals("unequal blue", expected.getBlue(), actual.getBlue(), DELTA);
    }

}
