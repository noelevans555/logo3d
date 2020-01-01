package com.noelevans555.logo3d.compiler.program.instruction;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TurnLeftTest extends AbstractMotionInstructionTest {

    @InjectMocks
    private TurnLeft turnLeft;

    @Test
    public void run_thenInstructsTurtleToMoveByExpectedAmount() throws Exception {
        turnLeft.run(state, turtle);
        verify(turtle).turnLeft(TEST_NUMERIC);
    }
}
