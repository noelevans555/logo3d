package com.noelevans555.logo3d.compiler.program.parameter.result;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.noelevans555.logo3d.compiler.exception.InternalException;
import com.noelevans555.logo3d.compiler.exception.SemanticException;
import com.noelevans555.logo3d.model.LogoColor;

public class NumericResultTest {

    private static final double DELTA = 1.0E-9;

    @Test
    public void conditionallyNegate_whenPositive_returnsNegatedResult() throws Exception {
        NumericResult result = new NumericResult(44.1);
        assertEquals(-44.1, result.conditionallyNegate(true).getNumeric("test"), DELTA);
    }

    @Test
    public void conditionallyNegate_whenNegative_returnsUnchangedResult() throws Exception {
        NumericResult result = new NumericResult(1753.0);
        assertEquals(1753.0, result.conditionallyNegate(false).getNumeric("test"), DELTA);
    }

    @Test
    public void combine_whenAdd_returnsAddedResult() throws Exception {
        NumericResult result = new NumericResult(532.5);
        Result operand = new NumericResult(21.2);
        assertEquals(553.7, result.combine("+", operand).getNumeric("test"), DELTA);
    }

    @Test
    public void combine_whenSubtract_returnsSubtractedResult() throws Exception {
        NumericResult result = new NumericResult(241.4);
        Result operand = new NumericResult(3628.1);
        assertEquals(-3386.7, result.combine("-", operand).getNumeric("test"), DELTA);
    }

    @Test
    public void combine_whenMultiply_returnsMultipliedResult() throws Exception {
        NumericResult result = new NumericResult(1.3);
        Result operand = new NumericResult(-281.0);
        assertEquals(-365.3, result.combine("*", operand).getNumeric("test"), DELTA);
    }

    @Test
    public void combine_whenDivide_returnsMultipliedResult() throws Exception {
        NumericResult result = new NumericResult(5994.5);
        Result operand = new NumericResult(-9.5);
        assertEquals(-631.0, result.combine("/", operand).getNumeric("test"), DELTA);
    }

    @Test(expected = SemanticException.class)
    public void combine_withColor_throwsSemanticException() throws Exception {
        NumericResult result = new NumericResult(1.0);
        Result operand = new ColorResult(new LogoColor(0, 0, 0));
        result.combine("+", operand);
    }

    @Test(expected = InternalException.class)
    public void combine_withUnrecognizedOperator_throwsInternalException() throws Exception {
        NumericResult result = new NumericResult(1.0);
        Result operand = new NumericResult(2.0);
        result.combine("|", operand);
    }

    @Test
    public void getNumeric_returnsNumeric() throws Exception {
        Result result = new NumericResult(523.2);
        assertEquals(523.2, result.getNumeric("forward"), DELTA);
    }

    @Test(expected = SemanticException.class)
    public void getColor_throwsSemanticException() throws Exception {
        Result result = new NumericResult(674.1);
        result.getColor("setcolor");
    }

}
