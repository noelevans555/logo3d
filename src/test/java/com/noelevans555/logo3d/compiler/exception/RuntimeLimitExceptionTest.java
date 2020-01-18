package com.noelevans555.logo3d.compiler.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RuntimeLimitExceptionTest {

    private static final String DETAIL = "The limit has been breached";

    private static final String EXPECTED_TOSTRING = String.format("LimitException [message=%s]", DETAIL);

    @Test
    public void toString_returnsExpectedString() {
        Exception e = new RuntimeLimitException(DETAIL);
        assertEquals(EXPECTED_TOSTRING, e.toString());
    }

}
