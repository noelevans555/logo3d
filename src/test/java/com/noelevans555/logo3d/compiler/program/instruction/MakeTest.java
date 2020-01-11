package com.noelevans555.logo3d.compiler.program.instruction;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.noelevans555.logo3d.compiler.program.parameter.Parameter;

@RunWith(MockitoJUnitRunner.class)
public class MakeTest extends AbstractInstructionTest {

    private static final String VARIABLE_NAME = "myVariable";

    @Mock
    private Parameter parameter;

    @Before
    public void setup() throws Exception {
        when(parameter.evaluate(state)).thenReturn(TEST_COLOR_RESULT);
    }

    @Test
    public void run_withLocalScope_thenStoresExpectedValueInProgramState() throws Exception {
        Make make = new Make(true, VARIABLE_NAME, parameter);
        make.run(state, turtle);
        verify(state).store(VARIABLE_NAME, TEST_COLOR_RESULT, true);
    }

    @Test
    public void run_withGlobalScope_thenStoresExpectedValueInProgramState() throws Exception {
        Make make = new Make(false, VARIABLE_NAME, parameter);
        make.run(state, turtle);
        verify(state).store(VARIABLE_NAME, TEST_COLOR_RESULT, false);
    }

}
