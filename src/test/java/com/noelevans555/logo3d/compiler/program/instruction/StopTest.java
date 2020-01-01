package com.noelevans555.logo3d.compiler.program.instruction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.noelevans555.logo3d.compiler.exception.StopException;

@RunWith(MockitoJUnitRunner.class)
public class StopTest extends AbstractInstructionTest {

    private Stop stop = new Stop();

    @Test(expected = StopException.class)
    public void run_throwsStopException() throws Exception {
        stop.run(state, turtle);
    }

}
