package com.noelevans555.logo3d.compiler.program.parameter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.noelevans555.logo3d.compiler.exception.InternalException;
import com.noelevans555.logo3d.compiler.exception.SemanticException;
import com.noelevans555.logo3d.compiler.program.parameter.result.ColorResult;
import com.noelevans555.logo3d.compiler.program.parameter.result.EvaluationResult;
import com.noelevans555.logo3d.compiler.program.parameter.result.NumericResult;

@RunWith(MockitoJUnitRunner.class)
public class BuiltInFunctionTest extends AbstractParameterTest {

    @Mock
    private Parameter parameter;

    @Test
    public void evaluate_forRandom_returnsRandom() throws Exception {
        when(parameter.evaluate(state)).thenReturn(new NumericResult(100.0));
        BuiltInFunction builtInFunction = new BuiltInFunction(false, "random", parameter);
        Double minValue = 50d;
        Double maxValue = 50d;
        for (int i = 0; i < 1000; i++) {
            NumericResult result = (NumericResult) builtInFunction.evaluate(state);
            minValue = Math.min(minValue, result.getNumeric("test"));
            maxValue = Math.max(maxValue, result.getNumeric("test"));
        }
        assertTrue("minValue=" + minValue, minValue >= 0 && minValue < 1);
        assertTrue("maxValue=" + maxValue, maxValue > 99 && minValue <= 100);
    }

    @Test
    public void evaluate_forSqrt_returnsSqrtValue() throws Exception {
        assertExpectedResult(new NumericResult(81.0), false, "sqrt", 9.0);
    }

    @Test
    public void evaluate_forSin_returnsSinValue() throws Exception {
        assertExpectedResult(new NumericResult(66.7), false, "sin", 0.918446381343087);
    }

    @Test
    public void evaluate_forCos_returnsCosValue() throws Exception {
        assertExpectedResult(new NumericResult(532.4), false, "COS", -0.991215540251542);
    }

    @Test
    public void evaluate_forTan_returnsTanValue() throws Exception {
        assertExpectedResult(new NumericResult(-72.5), false, "Tan", -3.171594802363213);
    }

    @Test
    public void evaluate_forAsin_returnsAsinValue() throws Exception {
        assertExpectedResult(new NumericResult(0.9), false, "ASIN", 64.15806723683288);
    }

    @Test
    public void evaluate_forAcos_returnsAcosValue() throws Exception {
        assertExpectedResult(new NumericResult(0.1), false, "acos", 84.26082952273322);
    }

    @Test
    public void evaluate_forAtan_returnsAtanValue() throws Exception {
        assertExpectedResult(new NumericResult(0.4), false, "aTan", 21.80140948635181);
    }

    @Test
    public void evaluate_forNegatedSin_returnsNegatedSinValue() throws Exception {
        assertExpectedResult(new NumericResult(54.2), true, "sin", -0.811063818989327);
    }

    @Test(expected = SemanticException.class)
    public void evaluate_withColorParameter_throwsSemanticException() throws Exception {
        assertExpectedResult(new ColorResult(null), false, "sin", 24.90072851173197);
    }

    @Test(expected = InternalException.class)
    public void evaluate_withUnrecognizedFunction_throwsInternalException() throws Exception {
        assertExpectedResult(new NumericResult(1.0), false, "pow", 24.90072851173197);
    }

    private void assertExpectedResult(final EvaluationResult parameterResponse, final boolean isNegated,
            final String functionName, final double expectedValue) throws Exception {

        when(parameter.evaluate(state)).thenReturn(parameterResponse);
        BuiltInFunction builtInFunction = new BuiltInFunction(isNegated, functionName, parameter);
        double result = builtInFunction.evaluate(state).getNumeric("test");
        assertEquals(expectedValue, result, DELTA);
    }

}
