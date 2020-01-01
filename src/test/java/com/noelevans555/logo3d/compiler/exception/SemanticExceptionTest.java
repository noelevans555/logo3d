package com.noelevans555.logo3d.compiler.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SemanticExceptionTest {

    private static final String DETAIL = "The program meaning is bad";
    private static final String OPERATION = "moveForward";

    @Test
    public void toString_returnsExpectedString() {
        Exception e = new SemanticException(DETAIL, OPERATION);
        assertEquals("SemanticException [message=" + DETAIL + ", operation=" + OPERATION + "]", e.toString());
    }

}
