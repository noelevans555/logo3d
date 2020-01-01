package com.noelevans555.logo3d.compiler.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InternalExceptionTest {

    private static final String DETAIL = "The program is bad";

    @Mock
    private Exception cause;

    @Test
    public void toString_withMessage_returnsExpectedString() {
        Exception e = new InternalException(DETAIL);
        assertEquals("InternalException [message=" + DETAIL + ", cause=null]", e.toString());
    }

    @Test
    public void toString_withMessageAndCause_returnsExpectedString() {
        Exception e = new InternalException(DETAIL, cause);
        assertEquals("InternalException [message=" + DETAIL + ", cause=cause]", e.toString());
    }

}
