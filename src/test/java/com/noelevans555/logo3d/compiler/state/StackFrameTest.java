package com.noelevans555.logo3d.compiler.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.noelevans555.logo3d.compiler.program.parameter.result.EvaluationResult;
import com.noelevans555.logo3d.compiler.program.parameter.result.NumericResult;

public class StackFrameTest {

    private static final EvaluationResult TEST_RESULT = new NumericResult(4.3);
    private static final EvaluationResult ALT_RESULT = new NumericResult(0.1);

    private StackFrame stackFrame = new StackFrame();

    @Test
    public void resolve_whenResultStored_expectedResultIsReturned() {
        stackFrame.storeResult("myVar", TEST_RESULT);
        assertEquals(TEST_RESULT, stackFrame.resolve("MYVAR"));
    }

    @Test
    public void resolve_whenResultOverwritten_overwrittenResultIsReturned() {
        stackFrame.storeResult("myVar", TEST_RESULT);
        stackFrame.storeResult("MyVaR", ALT_RESULT);
        assertEquals(ALT_RESULT, stackFrame.resolve("myvar"));
    }

    @Test
    public void containsName_whenResultStored_returnsTrue() {
        stackFrame.storeResult("my_Var", TEST_RESULT);
        assertTrue(stackFrame.containsName("my_var"));
    }

    @Test
    public void containsName_whenResultNotStored_returnsFalse() {
        stackFrame.storeResult("my__Var", TEST_RESULT);
        assertFalse(stackFrame.containsName("my_var"));
    }

}
