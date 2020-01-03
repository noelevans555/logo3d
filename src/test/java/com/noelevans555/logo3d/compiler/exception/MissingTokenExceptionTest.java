package com.noelevans555.logo3d.compiler.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class MissingTokenExceptionTest {

    private static final String OPERATION = "readProgram";
    private static final String EXPECTED_TOKEN = "myToken";
    private static final ImmutableSet<String> EXPECTED_TOKENS = ImmutableSet.of("tokenA", "tokenB");
    private static final ImmutableList<String> READ_TOKENS = ImmutableList.of("tokenOne", "tokenTwo");

    @Test
    public void toString_withExpectedToken_returnsExpectedString() {
        String expectedString = String.format(
                "MissingTokenException [message=Missing token, operation=%s, expectedToken=%s, recentlyReadTokens=%s]",
                OPERATION, EXPECTED_TOKEN, READ_TOKENS);

        Exception e = new MissingTokenException(OPERATION, EXPECTED_TOKEN, READ_TOKENS);
        assertEquals(expectedString, e.toString());
    }

    @Test
    public void toString_withExpectedTokens_returnsExpectedString() {
        String expectedString = String.format(
                "MissingTokenException [message=Missing token, operation=%s, expectedTokens=%s, recentlyReadTokens=%s]",
                OPERATION, EXPECTED_TOKENS, READ_TOKENS);

        Exception e = new MissingTokenException(OPERATION, EXPECTED_TOKENS, READ_TOKENS);
        assertEquals(expectedString, e.toString());
    }

}
