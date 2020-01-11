package com.noelevans555.logo3d.compiler.state;

import java.util.HashMap;
import java.util.Map;

/**
 * A single frame of the Logo3d memory stack.
 *
 * @param <T> The type of values stored in the frame.
 * @author Noel Evans (noelevans555@gmail.com)
 */
class StackFrame<T extends Object> {

    private final Map<String, T> frameContent = new HashMap<>();

    /**
     * Stores a value in the frame, overwriting any previously-stored value with the
     * same name (case insensitive).
     *
     * @param name The name by which the value can be retrieved.
     * @param result The value to store.
     */
    void store(final String name, final T result) {
        frameContent.put(name.toLowerCase(), result);
    }

    /**
     * Checks whether the frame contains a value stored with the specified name
     * (case insensitive).
     *
     * @param name The name to check for.
     * @return Whether a value with the specified name is stored in the frame.
     */
    boolean containsName(final String name) {
        return frameContent.containsKey(name.toLowerCase());
    }

    /**
     * Retrieves the value stored with the specified name in the stack frame. The
     * lookup is case-insensitive.
     *
     * @param name The name of the value to retrieve.
     * @return The value stored with the specified name (if present).
     */
    T retrieve(final String name) {
        return frameContent.get(name.toLowerCase());
    }
}
