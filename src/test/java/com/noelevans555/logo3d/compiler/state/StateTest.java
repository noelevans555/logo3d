package com.noelevans555.logo3d.compiler.state;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.noelevans555.logo3d.compiler.exception.EntityReferenceException;
import com.noelevans555.logo3d.compiler.exception.InternalException;
import com.noelevans555.logo3d.compiler.program.parameter.result.EvaluationResult;
import com.noelevans555.logo3d.compiler.program.parameter.result.NumericResult;

public class StateTest {

    private static final String TEST_NAME = "myVar";
    private static final EvaluationResult TEST_RESULT = new NumericResult(12.9);
    private static final EvaluationResult ALT_RESULT = new NumericResult(5.2);

    private State state = new State();

    @Test
    public void popStack_whenStacksRemain_doesNotThrowException() throws Exception {
        for (int i = 0; i < 20; i++) {
            state.pushStack();
        }
        for (int i = 0; i < 20; i++) {
            state.popStack();
        }
    }

    @Test(expected = InternalException.class)
    public void popStack_whenNoStacksRemain_throwsException() throws Exception {
        for (int i = 0; i < 20; i++) {
            state.pushStack();
        }
        for (int i = 0; i < 21; i++) {
            state.popStack();
        }
    }

    @Test
    public void resolve_whenResultStoredLocally_returnsExpectedResult() throws Exception {
        state.storeResult(TEST_NAME, TEST_RESULT, true);
        assertEquals(TEST_RESULT, state.resolve(TEST_NAME));
    }

    @Test
    public void resolve_whenResultStoredGlobally_returnsExpectedResult() throws Exception {
        state.storeResult(TEST_NAME, TEST_RESULT, false);
        assertEquals(TEST_RESULT, state.resolve(TEST_NAME));
    }

    @Test
    public void resolve_whenAlternativeResultStoredLocally_bothResultsAreRetained() throws Exception {
        state.storeResult(TEST_NAME, TEST_RESULT, true);
        state.pushStack();
        state.storeResult(TEST_NAME, ALT_RESULT, true);
        // assert alternative value is set in local scope.
        assertEquals(ALT_RESULT, state.resolve(TEST_NAME));
        // assert original value remains in outer scope.
        state.popStack();
        assertEquals(TEST_RESULT, state.resolve(TEST_NAME));
    }

    @Test
    public void resolve_whenAlternativeResultStoredGlobally_originalResultIsOverwritten() throws Exception {
        state.storeResult(TEST_NAME, TEST_RESULT, false);
        state.pushStack();
        state.storeResult(TEST_NAME, ALT_RESULT, false);
        // assert alternative value is set in local scope.
        assertEquals(ALT_RESULT, state.resolve(TEST_NAME));
        // assert alternative value is also set in outer scope.
        state.popStack();
        assertEquals(ALT_RESULT, state.resolve(TEST_NAME));
    }

    @Test(expected = EntityReferenceException.class)
    public void resolve_whenResultOnlyStoredInDeeperScope_throwsBadEntityException() throws Exception {
        state.pushStack();
        state.storeResult(TEST_NAME, TEST_RESULT, false);
        state.popStack();
        state.resolve(TEST_NAME);
    }

}
