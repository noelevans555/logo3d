package com.noelevans555.logo3d.compiler.state;

import java.util.HashMap;
import java.util.Map;

import com.noelevans555.logo3d.compiler.program.parameter.result.EvaluationResult;

/**
 * A single frame of the Logo3d memory stack.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

class StackFrame {

    private final Map<String, EvaluationResult> frameContent = new HashMap<>();

    /**
     * Stores a result in the frame, overwriting any previously-stored value with
     * the same name (case insensitive).
     *
     * @param name The name by which the evaluation result can be retrieved.
     * @param result The evaluation result to store.
     */
    void storeResult(final String name, final EvaluationResult result) {
        frameContent.put(name.toLowerCase(), result);
    }

    /**
     * Checks whether the frame contains a value stored with the specified name
     * (case insensitive).
     *
     * @param name The name to check for.
     * @return Whether a name with the specified name is stored in the frame.
     */
    boolean containsName(final String name) {
        return frameContent.containsKey(name.toLowerCase());
    }

    /**
     * Retrieves the value stored with the specified name in the stack frame. The
     * lookup is case-insensitive.
     *
     * @param name The name of the evaluation result to retrieve.
     * @return The value of the named evaluation result (if defined).
     */
    EvaluationResult resolve(final String name) {
        return frameContent.get(name.toLowerCase());
    }
}
