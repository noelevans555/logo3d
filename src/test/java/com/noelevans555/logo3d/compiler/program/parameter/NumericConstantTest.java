package com.noelevans555.logo3d.compiler.program.parameter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NumericConstantTest extends AbstractParameterTest {

    @Test
    public void evaluate_whenNotNegated_returnSameConstant() throws Exception {
        NumericConstant constant = new NumericConstant(false, 1253.1);
        double result = constant.evaluate(state).getNumeric("test");
        assertEquals(1253.1, result, DELTA);
    }

    @Test
    public void evaluate_whenNegated_returnsNegatedConstant() throws Exception {
        NumericConstant constant = new NumericConstant(true, 14522.3);
        double result = constant.evaluate(state).getNumeric("test");
        assertEquals(-14522.3, result, DELTA);
    }

}
