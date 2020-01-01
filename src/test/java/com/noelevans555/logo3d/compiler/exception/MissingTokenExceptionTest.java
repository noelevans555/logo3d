package com.noelevans555.logo3d.compiler.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class MissingTokenExceptionTest {

    private static final String OPERATION = "readProgram";
    private static final String EXPECTED_TOKEN = "myToken";
    private static final ImmutableSet<String> EXPECTED_TOKENS = ImmutableSet.of("tokenA", "tokenB");
    private static final ImmutableList<String> READ_TOKENS = ImmutableList.of("tokenA", "tokenB");

    @Test
    public void toString_withExpectedToken_returnsExpectedString() {
        Exception e = new MissingTokenException(OPERATION, EXPECTED_TOKEN, READ_TOKENS);
        assertEquals("SyntaxException [message=Missing token, operation=" + OPERATION + ", expectedToken="
                + EXPECTED_TOKEN + ", recentlyReadTokens=" + READ_TOKENS.toString() + "]", e.toString());
    }

    @Test
    public void toString_withExpectedTokens_returnsExpectedString() {
        Exception e = new MissingTokenException(OPERATION, EXPECTED_TOKENS, READ_TOKENS);
        assertEquals(
                "SyntaxException [message=Missing token, operation=" + OPERATION + ", expectedTokens="
                        + EXPECTED_TOKENS.toString() + ", recentlyReadTokens=" + READ_TOKENS.toString() + "]",
                e.toString());
    }

}
