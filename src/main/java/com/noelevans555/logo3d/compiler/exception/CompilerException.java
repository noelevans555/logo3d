package com.noelevans555.logo3d.compiler.exception;

/**
 * Base class for all exceptions encountered during Logo3d compilation.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

public abstract class CompilerException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * @param message The detail message for the exception.
     */
    public CompilerException(final String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param message The detail message for the exception.
     * @param cause The underlying cause of the exception.
     */
    public CompilerException(final String message, final Exception cause) {
        super(message, cause);
    }

}
