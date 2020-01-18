package com.noelevans555.logo3d.compiler.exception;

/**
 * Indicates that a runtime limit was exceeded during execution of the input
 * program.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

public class RuntimeLimitException extends ProgramException {

    public static final String TIMEOUT_EXCEEDED = "Timeout exceeded";
    public static final String MAXIMUM_STACK_DEPTH_EXCEEDED = "Maximum stack depth exceeded";
    public static final String MAXIMUM_LOGO_LINES_EXCEEDED = "Maximum logo lines exceeded";

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * @param message The detail message for the exception.
     */
    public RuntimeLimitException(final String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "LimitException [message=" + getMessage() + "]";
    }

}
