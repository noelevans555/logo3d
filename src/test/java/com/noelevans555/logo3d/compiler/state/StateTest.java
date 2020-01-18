package com.noelevans555.logo3d.compiler.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.noelevans555.logo3d.compiler.exception.EntityReferenceException;
import com.noelevans555.logo3d.compiler.exception.InternalException;
import com.noelevans555.logo3d.compiler.exception.RuntimeLimitException;
import com.noelevans555.logo3d.compiler.program.parameter.result.NumericResult;
import com.noelevans555.logo3d.compiler.program.parameter.result.Result;
import com.noelevans555.logo3d.compiler.turtle.Pose;
import com.noelevans555.logo3d.model.LogoPoint;

public class StateTest {

    private static final String TEST_NAME = "myVar";
    private static final Pose TEST_POSE = new Pose(new LogoPoint(0, 0, 0), new double[4][4]);
    private static final Result TEST_RESULT = new NumericResult(12.9);
    private static final Result ALT_RESULT = new NumericResult(5.2);

    private State state = new State(100);

    @Test
    public void pushStack_whenStackLimitNotReached_doesNotThrowException() throws Exception {
        for (int i = 0; i < 99; i++) {
            state.pushStack();
        }
    }

    @Test(expected = RuntimeLimitException.class)
    public void pushStack_whenStackLimitReached_throwsRuntimeLimitException() throws Exception {
        for (int i = 0; i < 100; i++) {
            state.pushStack();
        }
    }

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
    public void popStack_whenNoStacksRemain_throwsInternalException() throws Exception {
        for (int i = 0; i < 20; i++) {
            state.pushStack();
        }
        for (int i = 0; i < 21; i++) {
            state.popStack();
        }
    }

    @Test
    public void retrievePose_whenPoseStoredLocally_returnsExpectedPose() throws Exception {
        state.store(TEST_NAME, TEST_POSE, true);
        assertSame(TEST_POSE, state.retrievePose(TEST_NAME));
    }

    @Test
    public void retrieveResult_whenResultStoredLocally_returnsExpectedResult() throws Exception {
        state.store(TEST_NAME, TEST_RESULT, true);
        assertEquals(TEST_RESULT, state.retrieveResult(TEST_NAME));
    }

    @Test
    public void retrieveResult_whenResultStoredGlobally_returnsExpectedResult() throws Exception {
        state.store(TEST_NAME, TEST_RESULT, false);
        assertEquals(TEST_RESULT, state.retrieveResult(TEST_NAME));
    }

    @Test
    public void retrieveResult_whenAlternativeResultStoredLocally_bothResultsAreRetained() throws Exception {
        state.store(TEST_NAME, TEST_RESULT, true);
        state.pushStack();
        state.store(TEST_NAME, ALT_RESULT, true);
        // assert alternative value is set in local scope.
        assertEquals(ALT_RESULT, state.retrieveResult(TEST_NAME));
        // assert original value remains in outer scope.
        state.popStack();
        assertEquals(TEST_RESULT, state.retrieveResult(TEST_NAME));
    }

    @Test
    public void retrieveResult_whenAlternativeResultStoredGlobally_originalResultIsOverwritten() throws Exception {
        state.store(TEST_NAME, TEST_RESULT, false);
        state.pushStack();
        state.store(TEST_NAME, ALT_RESULT, false);
        // assert alternative value is set in local scope.
        assertEquals(ALT_RESULT, state.retrieveResult(TEST_NAME));
        // assert alternative value is also set in outer scope.
        state.popStack();
        assertEquals(ALT_RESULT, state.retrieveResult(TEST_NAME));
    }

    @Test(expected = EntityReferenceException.class)
    public void retrieveResult_whenResultOnlyStoredInDeeperScope_throwsEntityReferenceException() throws Exception {
        state.pushStack();
        state.store(TEST_NAME, TEST_RESULT, false);
        state.popStack();
        state.retrieveResult(TEST_NAME);
    }

}
