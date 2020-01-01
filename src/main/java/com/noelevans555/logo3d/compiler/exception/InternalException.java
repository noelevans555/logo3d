package com.noelevans555.logo3d.compiler.exception;

/**
 * Represents an exception encountered during Logo3d compilation which is due to
 * a fault in the compiler logic.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

public class InternalException extends CompilerException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * @param message The detail message for the exception.
     */
    public InternalException(final String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param message The detail message for the exception.
     * @param cause The underlying cause of the exception.
     */
    public InternalException(final String message, final Exception cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return "InternalException [message=" + getMessage() + ", cause=" + getCause() + "]";
    }

}
