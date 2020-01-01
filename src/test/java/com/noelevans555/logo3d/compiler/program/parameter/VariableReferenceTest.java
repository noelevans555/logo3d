package com.noelevans555.logo3d.compiler.program.parameter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.program.parameter.result.NumericResult;

@RunWith(MockitoJUnitRunner.class)
public class VariableReferenceTest extends AbstractParameterTest {

    @Before
    public void setup() throws Exception {
        when(state.resolve("myVariable")).thenReturn(new NumericResult(4.1));
    }

    @Test
    public void evaluate_withVariableName_returnsStateResult() throws CompilerException {
        VariableReference variableParameter = new VariableReference(false, "myVariable");
        double result = variableParameter.evaluate(state).getNumeric("test");
        assertEquals(4.1, result, DELTA);
    }

    @Test
    public void evaluate_withVariableNameAndNegated_returnsNegatedStateResult() throws CompilerException {
        VariableReference variableParameter = new VariableReference(true, "myVariable");
        double result = variableParameter.evaluate(state).getNumeric("test");
        assertEquals(-4.1, result, DELTA);
    }

}
