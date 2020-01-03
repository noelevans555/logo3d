package com.noelevans555.logo3d.compiler.exception;

import java.util.Optional;

import lombok.Getter;

/**
 * Represents an exception encountered during Logo3d compilation which is due to
 * a fault with the input program.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@Getter
public abstract class ProgramException extends CompilerException {

    private static final long serialVersionUID = 1L;

    private final Optional<String> operation;

    /**
     * Constructor for a exception which is not directly linked to a specific
     * operation.
     *
     * @param message The detail message for the exception.
     */
    public ProgramException(final String message) {
        super(message);
        this.operation = Optional.empty();
    }

    /**
     * Constructor.
     *
     * @param message The detail message for the exception.
     * @param operation The operation being attempted at the time of the exception.
     */
    public ProgramException(final String message, final String operation) {
        super(message);
        this.operation = Optional.of(operation);
    }

}
