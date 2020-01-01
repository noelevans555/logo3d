package com.noelevans555.logo3d.compiler.program.instruction;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.noelevans555.logo3d.compiler.exception.StopException;
import com.noelevans555.logo3d.compiler.program.Program;
import com.noelevans555.logo3d.compiler.program.parameter.Parameter;

@RunWith(MockitoJUnitRunner.class)
public class RepeatTest extends AbstractInstructionTest {

    @Mock
    private Program program;
    @Mock
    private Parameter parameter;
    @InjectMocks
    private Repeat repeat;

    @Before
    public void setup() throws Exception {
        when(parameter.evaluate(state)).thenReturn(TEST_NUMERIC_RESULT);
    }

    @Test
    public void run_thenRunsRepeatBodyExpectedNumberOfTimes() throws Exception {
        repeat.run(state, turtle);
        verify(program, times((int) Math.floor(TEST_NUMERIC))).run(state, turtle);
    }

    @Test
    public void run_withStopException_thenExitsCleanly() throws Exception {
        doThrow(new StopException()).when(program).run(state, turtle);
        repeat.run(state, turtle);
        verify(program, times(1)).run(state, turtle);
    }

    @After
    public void verifyState() {
        InOrder inOrder = inOrder(state);
        inOrder.verify(state).pushStack();
        inOrder.verify(state).popStack();
    }
}
