package com.noelevans555.logo3d.compiler.program.instruction;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.mockito.Mock;

import com.noelevans555.logo3d.compiler.program.parameter.Parameter;

public class AbstractMotionInstructionTest extends AbstractInstructionTest {

    @Mock
    private Parameter parameter;

    @Before
    public void setup() throws Exception {
        when(parameter.evaluate(state)).thenReturn(TEST_NUMERIC_RESULT);
    }

}
