package com.noelevans555.logo3d.compiler.exception;

/**
 * Indicates that the program should stop execution within the current scope.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

public class StopException extends CompilerException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     */
    public StopException() {
        super("Stop");
    }

}
