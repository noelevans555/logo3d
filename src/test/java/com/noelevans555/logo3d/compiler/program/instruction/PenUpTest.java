package com.noelevans555.logo3d.compiler.program.instruction;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PenUpTest extends AbstractInstructionTest {

    private PenUp penUp = new PenUp();

    @Test
    public void runCommand_thenInstructsTurtleToStopDrawing() throws Exception {
        penUp.run(state, turtle);
        verify(turtle).setDrawing(false);
    }

}
