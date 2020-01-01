package com.noelevans555.logo3d.compiler.program.instruction;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RollRightTest extends AbstractMotionInstructionTest {

    @InjectMocks
    private RollRight rollRight;

    @Test
    public void run_thenInstructsTurtleToMoveByExpectedAmount() throws Exception {
        rollRight.run(state, turtle);
        verify(turtle).rollRight(TEST_NUMERIC);
    }
}
