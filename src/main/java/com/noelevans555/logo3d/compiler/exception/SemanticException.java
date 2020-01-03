package com.noelevans555.logo3d.compiler.exception;

import lombok.Getter;

/**
 * Represents an exception encountered during Logo3d compilation which is due to
 * a semantic fault with the input program.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@Getter
public class SemanticException extends ProgramException {

    public static final String INVALID_COLOR_AS_INPUT = "Invalid color as input";
    public static final String INVALID_NUMERIC_AS_INPUT = "Invalid numeric as input";
    public static final String CANNOT_COMBINE_COLOR_AND_NUMERIC = "Cannot combine color and numeric";

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for a exception which is not directly linked to a specific
     * operation.
     *
     * @param message The detail message for the exception.
     */
    public SemanticException(final String message) {
        super(message);
    }

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
        StringBuilder builder = new StringBuilder();
        builder.append("SemanticException [message=" + getMessage());
        getOperation().ifPresent(e -> builder.append(", operation=" + getOperation().get()));
        builder.append("]");
        return builder.toString();
    }

}
