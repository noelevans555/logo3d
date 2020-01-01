package com.noelevans555.logo3d.compiler.program.instruction;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.noelevans555.logo3d.compiler.program.parameter.Parameter;

@RunWith(MockitoJUnitRunner.class)
public class SetColorTest extends AbstractInstructionTest {

    @Mock
    private Parameter parameter;
    @InjectMocks
    private SetColor setColor;

    @Before
    public void setup() throws Exception {
        when(parameter.evaluate(state)).thenReturn(TEST_COLOR_RESULT);
    }

    @Test
    public void run_thenInstructsTurtleToDrawWithExpectedColor() throws Exception {
        setColor.run(state, turtle);
        verify(turtle).setColor(TEST_COLOR);
    }

}
