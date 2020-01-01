package com.noelevans555.logo3d.compiler.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class SyntaxExceptionTest {

    private static final String DETAIL = "The program structure is bad";
    private static final String OPERATION = "readParameter";
    private static final ImmutableList<String> READ_TOKENS = ImmutableList.of("tokenA", "tokenB");

    @Test
    public void toString_returnsExpectedString() {
        Exception e = new SyntaxException(DETAIL, OPERATION, READ_TOKENS);
        assertEquals("SyntaxException [message=" + DETAIL + ", operation=" + OPERATION + ", recentlyReadTokens="
                + READ_TOKENS.toString() + "]", e.toString());
    }

}
