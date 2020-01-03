package com.noelevans555.logo3d.compiler.state;

import java.util.ArrayList;
import java.util.List;

import com.noelevans555.logo3d.compiler.exception.EntityReferenceException;
import com.noelevans555.logo3d.compiler.exception.InternalException;
import com.noelevans555.logo3d.compiler.program.parameter.result.EvaluationResult;

/**
 * Tracks the current value of variable parameters during execution of a Logo3d
 * program. All processing is case-insensitive, i.e. names with only case
 * differences will be treated as overwrites, and the value can be retrieved
 * with any name which features only case differences.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */
public class State {

    private final List<StackFrame> stackFrames = new ArrayList<>();

    /**
     * Constructor. Initializes a new state with a single stack frame and no stored
     * evaluation results.
     */
    State() {
        pushStack();
    }

    /**
     * Adds a new stack frame to the evaluation stack.
     */
    public void pushStack() {
        stackFrames.add(new StackFrame());
    }

    /**
     * Removes the most recently added stack frame from the evaluation stack.
     *
     * @throws InternalException If no removable stack frames are present.
     */
    public void popStack() throws InternalException {
        if (stackFrames.size() == 1) {
            throw new InternalException("No remaining stack frames to pop");
        }
        stackFrames.remove(stackFrames.size() - 1);
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
        getStorageFrame(name, isLocalScope).storeResult(name, result);
    }

    /**
     * Retrieves an evaluation result with the specified name, searching stack
     * frames starting from the most recently added.
     *
     * @param name The name of the evaluation result to retrieve.
     * @return The value of the named evaluation result.
     * @throws EntityReferenceException If no evaluation result is stored with the
     *         specified name.
     */
    public EvaluationResult resolve(final String name) throws EntityReferenceException {
        for (int i = stackFrames.size() - 1; i >= 0; i--) {
            if (stackFrames.get(i).containsName(name)) {
                return stackFrames.get(i).resolve(name);
            }
        }
        throw new EntityReferenceException(EntityReferenceException.UNDEFINED_VARIABLE, name);
    }

    /**
     * Identifies the storage frame in which a named result should be stored.
     *
     * @param name The name by which the evaluation result can be retrieved.
     * @param isLocalScope Whether the value should be stored independently of
     *        values stored with the same name at previous stack frames.
     * @return The storage frame in which the named result should be stored.
     */
    private StackFrame getStorageFrame(final String name, final boolean isLocalScope) {
        if (isLocalScope) {
            return stackFrames.get(stackFrames.size() - 1);
        }
        for (int i = stackFrames.size() - 1; i >= 0; i--) {
            if (stackFrames.get(i).containsName(name)) {
                return stackFrames.get(i);
            }
        }
        return stackFrames.get(stackFrames.size() - 1);
    }

}
