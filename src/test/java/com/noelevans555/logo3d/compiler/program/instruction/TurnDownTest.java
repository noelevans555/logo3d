package com.noelevans555.logo3d.compiler.program.instruction;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TurnDownTest extends AbstractMotionInstructionTest {

    @InjectMocks
    private TurnDown turnDown;

    @Test
    public void run_thenInstructsTurtleToMoveByExpectedAmount() throws Exception {
        turnDown.run(state, turtle);
        verify(turtle).turnDown(TEST_NUMERIC);
    }
}
