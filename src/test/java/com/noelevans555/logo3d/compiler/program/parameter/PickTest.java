package com.noelevans555.logo3d.compiler.program.parameter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;
import com.noelevans555.logo3d.compiler.exception.CompilerException;

@RunWith(MockitoJUnitRunner.class)
public class PickTest extends AbstractParameterTest {

    private static final int TEST_LOOP_COUNT = 500;

    @Test
    public void evaluate_oneParameters_returnsParameterValue() throws CompilerException {
        Pick pickParameter = new Pick(false, ImmutableList.of(new NumericConstant(555)));
        assertEquals(555.0, pickParameter.evaluate(state).getNumeric("test"), DELTA);
    }

    @Test
    public void evaluate_twoParameters_returnsBothEqually() throws CompilerException {
        Pick pickParameter = new Pick(false, ImmutableList.of(new NumericConstant(10), new NumericConstant(20)));
        double averageOfResults = getAverageResultOfRuns(pickParameter);
        assertTrue(averageOfResults > 14 && averageOfResults < 16);
    }

    @Test
    public void evaluate_twoParametersNegated_returnsBothEqually() throws CompilerException {
        Pick pickParameter = new Pick(true, ImmutableList.of(new NumericConstant(20), new NumericConstant(30)));
        double averageOfResults = getAverageResultOfRuns(pickParameter);
        assertTrue(averageOfResults > -26 && averageOfResults < -24);
    }

    private double getAverageResultOfRuns(final Pick pickParameter) throws CompilerException {
        double sumOfResults = 0d;
        for (int i = 0; i < TEST_LOOP_COUNT; i++) {
            sumOfResults += pickParameter.evaluate(state).getNumeric("test");
        }
        return sumOfResults / TEST_LOOP_COUNT;
    }

}
