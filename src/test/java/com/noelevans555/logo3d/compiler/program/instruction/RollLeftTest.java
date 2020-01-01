package com.noelevans555.logo3d.compiler.program.instruction;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RollLeftTest extends AbstractMotionInstructionTest {

    @InjectMocks
    private RollLeft rollLeft;

    @Test
    public void run_thenInstructsTurtleToMoveByExpectedAmount() throws Exception {
        rollLeft.run(state, turtle);
        verify(turtle).rollLeft(TEST_NUMERIC);
    }
}
