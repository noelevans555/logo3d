package com.noelevans555.logo3d.compiler.program.instruction;

import org.junit.Test;

import com.noelevans555.logo3d.compiler.exception.RuntimeLimitException;

public class TimeoutCheckTest extends AbstractInstructionTest {

    private TimeoutCheck timeoutCheck = new TimeoutCheck(1);

    @Test
    public void run_whenTimeoutHasNotOccured_doesNotThrowException() throws Exception {
        Thread.sleep(500);
        timeoutCheck.run(state, turtle);
    }

    @Test(expected = RuntimeLimitException.class)
    public void run_whenTimeoutHasOccured_throwsLimitException() throws Exception {
        Thread.sleep(1001);
        timeoutCheck.run(state, turtle);
    }

}
