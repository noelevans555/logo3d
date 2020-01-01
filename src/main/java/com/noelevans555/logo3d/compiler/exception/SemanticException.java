package com.noelevans555.logo3d.compiler.exception;

/**
 * Represents an exception encountered during Logo3d compilation which is due to
 * a semantic fault with the input program.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

public class SemanticException extends ProgramException {

    public static final String INVALID_COLOR_AS_INPUT = "Invalid color as input";
    public static final String INVALID_NUMERIC_AS_INPUT = "Invalid numeric as input";
    public static final String CANNOT_COMBINE_COLOR_AND_NUMERIC = "Cannot combine color and numeric";
    public static final String PROCEDURE_ALREADY_EXISTS = "Procedure already exists";

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * @param message The detail message for the exception.
     * @param operation The operation being attempted at the time of the exception.
     */
    public SemanticException(final String message, final String operation) {
        super(message, operation);
    }

    @Override
    public String toString() {
        return "SemanticException [message=" + getMessage() + ", operation=" + getOperation() + "]";
    }

}
