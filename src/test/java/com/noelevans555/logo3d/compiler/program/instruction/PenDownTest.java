package com.noelevans555.logo3d.compiler.program.instruction;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PenDownTest extends AbstractInstructionTest {

    private PenDown penDown = new PenDown();

    @Test
    public void runCommand_thenInstructsTurtleToStartDrawing() throws Exception {
        penDown.run(state, turtle);
        verify(turtle).setDrawing(true);
    }

}
