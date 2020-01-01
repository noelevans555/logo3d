package com.noelevans555.logo3d.compiler.program.parameter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;

@RunWith(MockitoJUnitRunner.class)
public class ExpressionTest extends AbstractParameterTest {

    @Test
    public void evaluate_withSingleConstant_returnsConstant() throws Exception {
        Expression expression = new Expression(ImmutableList.of(new NumericConstant(24.1)), ImmutableList.of());
        double result = expression.evaluate(state).getNumeric("test");
        assertEquals(24.1, result, DELTA);
    }

    @Test
    public void evaluate_withAddedConstants_returnsSumOfConstants() throws Exception {
        Expression expression = new Expression(ImmutableList.of(new NumericConstant(53.1), new NumericConstant(152.6)),
                ImmutableList.of("+"));
        double result = expression.evaluate(state).getNumeric("test");
        assertEquals(205.7, result, DELTA);
    }

}
