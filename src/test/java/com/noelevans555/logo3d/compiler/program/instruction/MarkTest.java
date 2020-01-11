package com.noelevans555.logo3d.compiler.program.instruction;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.noelevans555.logo3d.compiler.turtle.Pose;

@RunWith(MockitoJUnitRunner.class)
public class MarkTest extends AbstractInstructionTest {

    private static final String VARIABLE_NAME = "myVariable";

    @Mock
    private Pose pose;

    @Before
    public void setup() throws Exception {
        when(turtle.getPose()).thenReturn(pose);
    }

    @Test
    public void run_withLocalScope_thenStoresExpectedPoseInProgramState() throws Exception {
        Mark mark = new Mark(true, VARIABLE_NAME);
        mark.run(state, turtle);
        verify(state).store(VARIABLE_NAME, pose, true);
    }

    @Test
    public void run_withGlobalScope_thenStoresExpectedPoseInProgramState() throws Exception {
        Mark mark = new Mark(false, VARIABLE_NAME);
        mark.run(state, turtle);
        verify(state).store(VARIABLE_NAME, pose, false);
    }

}
