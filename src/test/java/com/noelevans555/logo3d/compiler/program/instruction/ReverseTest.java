package com.noelevans555.logo3d.compiler.program.instruction;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReverseTest extends AbstractMotionInstructionTest {

    @InjectMocks
    private Reverse reverse;

    @Test
    public void run_thenInstructsTurtleToMoveByExpectedAmount() throws Exception {
        reverse.run(state, turtle);
        verify(turtle).moveForward(TEST_NUMERIC * -1);
    }
}
