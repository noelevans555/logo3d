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
public class GotoTest extends AbstractInstructionTest {

    private static final String VARIABLE_NAME = "myVariable";

    @Mock
    private Pose pose;

    @Before
    public void setup() throws Exception {
        when(state.retrievePose(VARIABLE_NAME)).thenReturn(pose);
    }

    @Test
    public void run_thenSetsTurtleToExpectedPose() throws Exception {
        Goto gotoInstruction = new Goto(VARIABLE_NAME);
        gotoInstruction.run(state, turtle);
        verify(turtle).setPose(pose);
    }

}
