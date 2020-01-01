package com.noelevans555.logo3d.compiler.program.instruction;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TurnRightTest extends AbstractMotionInstructionTest {

    @InjectMocks
    private TurnRight turnRight;

    @Test
    public void run_thenInstructsTurtleToMoveByExpectedAmount() throws Exception {
        turnRight.run(state, turtle);
        verify(turtle).turnRight(TEST_NUMERIC);
    }
}
