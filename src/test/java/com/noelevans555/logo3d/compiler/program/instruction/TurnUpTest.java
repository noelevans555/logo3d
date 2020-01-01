package com.noelevans555.logo3d.compiler.program.instruction;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TurnUpTest extends AbstractMotionInstructionTest {

    @InjectMocks
    private TurnUp turnUp;

    @Test
    public void run_thenInstructsTurtleToMoveByExpectedAmount() throws Exception {
        turnUp.run(state, turtle);
        verify(turtle).turnUp(TEST_NUMERIC);
    }
}
