package com.noelevans555.logo3d.compiler.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.noelevans555.logo3d.compiler.program.parameter.result.Result;
import com.noelevans555.logo3d.compiler.program.parameter.result.NumericResult;

public class StackFrameTest {

    private static final Result TEST_RESULT = new NumericResult(4.3);
    private static final Result ALT_RESULT = new NumericResult(0.1);

    private StackFrame<Result> stackFrame = new StackFrame<>();

    @Test
    public void retrieve_whenResultStored_expectedResultIsReturned() {
        stackFrame.store("myVar", TEST_RESULT);
        assertEquals(TEST_RESULT, stackFrame.retrieve("MYVAR"));
    }

    @Test
    public void retrieve_whenResultOverwritten_overwrittenResultIsReturned() {
        stackFrame.store("myVar", TEST_RESULT);
        stackFrame.store("MyVaR", ALT_RESULT);
        assertEquals(ALT_RESULT, stackFrame.retrieve("myvar"));
    }

    @Test
    public void containsName_whenResultStored_returnsTrue() {
        stackFrame.store("my_Var", TEST_RESULT);
        assertTrue(stackFrame.containsName("my_var"));
    }

    @Test
    public void containsName_whenResultNotStored_returnsFalse() {
        stackFrame.store("my__Var", TEST_RESULT);
        assertFalse(stackFrame.containsName("my_var"));
    }

}
