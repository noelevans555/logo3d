package com.noelevans555.logo3d.compiler.state;

import com.noelevans555.logo3d.compiler.exception.SemanticException;
import com.noelevans555.logo3d.compiler.program.parameter.result.EvaluationResult;

/**
 * Tracks the current value of variable parameters during execution of a Logo3d
 * program.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */
public class State {

    /**
     * Adds a new stack frame to the evaluation stack.
     */
    public void pushStack() {
        // TODO
    }

    /**
     * Removes the most recently added stack frame from the evaluation stack.
     */
    public void popStack() {
        // TODO
    }

    /**
     * Stores an evaluation result in the program state for later retrieval using
     * the specified name.
     *
     * @param name The name by which the evaluation result can be retrieved.
     * @param result The evaluation result to store.
     * @param isLocalScope Whether the value should be stored independently of
     *        values stored with the same name at previous stack frames (otherwise
     *        any value stored with matching name at a previous stack frame will be
     *        overwritten).
     */
    public void storeResult(final String name, final EvaluationResult result, final boolean isLocalScope) {
        // TODO
    }

    /**
     * Retrieves an evaluation result with the specified name, searching stack
     * frames starting from the most recently added.
     *
     * @param name The name of evaluation result to retrieve.
     * @return The value of the named evaluation result (if defined).
     * @throws SemanticException If no evaluation result is stored with the
     *         specified name.
     */
    public EvaluationResult resolve(final String name) throws SemanticException {
        // TODO
        return null;
    }

}
