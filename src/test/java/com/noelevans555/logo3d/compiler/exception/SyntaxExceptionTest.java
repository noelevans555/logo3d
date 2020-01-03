package com.noelevans555.logo3d.compiler.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class SyntaxExceptionTest {

    private static final String DETAIL = "The program structure is bad";
    private static final String OPERATION = "ReadParameter";
    private static final ImmutableList<String> READ_TOKENS = ImmutableList.of("tokenA", "tokenB");

    private static final String EXPECTED_TOSTRING = String.format(
            "SyntaxException [message=%s, operation=%s, recentlyReadTokens=%s]", DETAIL, OPERATION, READ_TOKENS);

    @Test
    public void toString_returnsExpectedString() {
        Exception e = new SyntaxException(DETAIL, OPERATION, READ_TOKENS);
        assertEquals(EXPECTED_TOSTRING, e.toString());
    }

}
