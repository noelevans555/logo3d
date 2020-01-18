package com.noelevans555.logo3d.compiler.exception;

import java.util.List;

import lombok.Getter;

/**
 * Represents an exception encountered during Logo3d compilation which is due to
 * a syntactic fault with the input program.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@Getter
public class SyntaxException extends ProgramException {

    public static final String UNEXPECTED_TOKEN = "Unexpected token";
    public static final String INVALID_PARAMETER = "Invalid parameter";
    public static final String INVALID_NAME = "Invalid name";
    public static final String MISSING_VALUES = "Missing values";
    public static final String UNRECOGNIZED_INSTRUCTION = "Unrecognized instruction";
    public static final String UNEXPECTED_END_OF_PROGRAM = "Unexpected end of program";

    private static final long serialVersionUID = 1L;

    private final List<String> recentlyReadTokens;

    /**
     * Constructor.
     *
     * @param message The detail message for the exception.
     * @param operation The operation being attempted at the time of the exception.
     * @param recentlyReadTokens The tokens which had been most recently read at the
     *        time of the exception, in the order in which they were read.
     */
    public SyntaxException(final String message, final String operation, final List<String> recentlyReadTokens) {
        super(message, operation);
        this.recentlyReadTokens = recentlyReadTokens;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SyntaxException [message=" + getMessage());
        getOperation().ifPresent(e -> builder.append(", operation=" + getOperation().get()));
        builder.append(", recentlyReadTokens=" + recentlyReadTokens);
        builder.append("]");
        return builder.toString();
    }

}
