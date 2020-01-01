package com.noelevans555.logo3d.compiler.program.tokens;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.noelevans555.logo3d.compiler.exception.MissingTokenException;
import com.noelevans555.logo3d.compiler.exception.SyntaxException;

public class TokenReaderTest {

    @Test
    public void hasMoreTokens_whenNoTokens_returnsFalse() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of());
        assertFalse(tokenReader.hasMoreTokens());
    }

    @Test
    public void hasMoreTokens_whenNoMoreTokens_returnsFalse() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("test"));
        tokenReader.readToken();
        assertFalse(tokenReader.hasMoreTokens());
    }

    @Test
    public void hasMoreTokens_whenHasTokens_returnsTrue() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("test"));
        assertTrue(tokenReader.hasMoreTokens());
    }

    @Test(expected = SyntaxException.class)
    public void next_whenNoTokens_throwsSyntaxException() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of());
        tokenReader.readToken();
    }

    @Test
    public void next_whenHasTokens_returnsToken() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("TEST"));
        assertEquals("TEST", tokenReader.readToken());
    }

    @Test
    public void peek_whenNoTokens_returnsEmpty() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of());
        assertTrue(tokenReader.peek().isEmpty());
    }

    @Test
    public void peek_whenHasTokens_returnsToken() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("TEST"));
        for (int i = 0; i < 10; i++) {
            assertEquals("TEST", tokenReader.peek().get());
        }
    }

    @Test(expected = MissingTokenException.class)
    public void readExpectedToken_whenMismatchedToken_throwsMissingTokenException() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("TEST"));
        tokenReader.readExpectedToken("<", "unitTest");
    }

    @Test
    public void readExpectedToken_whenMatchingToken_consumesToken() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("TEST"));
        tokenReader.readExpectedToken("test", "unitTest");
        assertFalse(tokenReader.hasMoreTokens());
    }

    @Test(expected = MissingTokenException.class)
    public void readExpectedToken_whenMismatchedTokens_throwsMissingTokenException() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("<"));
        tokenReader.readExpectedToken(Vocabulary.OPERATORS, "unitTest");
    }

    @Test
    public void readExpectedToken_whenMatchingTokens_consumesToken() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("<"));
        tokenReader.readExpectedToken(Vocabulary.COMPARATORS, "unitTest");
        assertFalse(tokenReader.hasMoreTokens());
    }

    @Test
    public void readOptionalToken_whenNoToken_returnsFalse() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of());
        assertFalse(tokenReader.readOptionalToken("fd"));
    }

    @Test
    public void readOptionalToken_whenMismatchedToken_returnsFalse() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("TEST"));
        assertFalse(tokenReader.readOptionalToken("fd"));
        assertTrue(tokenReader.hasMoreTokens());
    }

    @Test
    public void readOptionalToken_whenMatchingToken_returnsTrueAndConsumesToken() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("TEST"));
        assertTrue(tokenReader.readOptionalToken("test"));
        assertFalse(tokenReader.hasMoreTokens());
    }

    @Test
    public void readOptionalToken_whenNoTokens_returnsEmpty() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of());
        assertTrue(tokenReader.readOptionalToken(Vocabulary.BUILT_IN_FUNCTIONS).isEmpty());
    }

    @Test
    public void readOptionalToken_whenMismatchedTokens_returnsEmpty() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("TEST"));
        assertTrue(tokenReader.readOptionalToken(Vocabulary.BUILT_IN_FUNCTIONS).isEmpty());
        assertTrue(tokenReader.hasMoreTokens());
    }

    @Test
    public void readOptionalToken_whenMatchingTokens_returnsMatchedTokenAndConsumesToken() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("SqRt"));
        assertEquals("SqRt", tokenReader.readOptionalToken(Vocabulary.BUILT_IN_FUNCTIONS).get());
        assertFalse(tokenReader.hasMoreTokens());
    }

    @Test(expected = SyntaxException.class)
    public void readValidName_withReservedWord_throwsSyntaxException() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("STOP"));
        tokenReader.readValidName("unitTest");
    }

    @Test(expected = SyntaxException.class)
    public void readValidName_withNumeriConstant_throwsSyntaxException() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("3.12"));
        tokenReader.readValidName("unitTest");
    }

    @Test
    public void readValidName_withVaidName_returnsNameAndConsumesToken() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("i3.12"));
        assertEquals("i3.12", tokenReader.readValidName("unitTest"));
        assertFalse(tokenReader.hasMoreTokens());
    }

    @Test
    public void readDelimiters_withOnlyDelimiters_consumesAllTokens() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("|", "|", "|"));
        tokenReader.readDelimiters();
        assertFalse(tokenReader.hasMoreTokens());
    }

    @Test
    public void readDelimiters_withLeadingDelimiters_consumesDelimiters() throws Exception {
        TokenReader tokenReader = new TokenReader(ImmutableList.of("|", "|", "teST"));
        tokenReader.readDelimiters();
        assertEquals("teST", tokenReader.readToken());
    }

    @Test
    public void getRecentlyReadTokens_whenNoTokensRead_returnsNoTokens() throws Exception {
        TokenReader tokenList = new TokenReader(
                ImmutableList.of("these", "are", "some", "test", "tokens", "one", "two", "three"));
        assertTrue(tokenList.getRecentlyReadTokens().isEmpty());
    }

    @Test
    public void getRecentlyReadTokens_whenMultipleTokensRead_returnsReadTokens() throws Exception {
        TokenReader tokenList = new TokenReader(
                ImmutableList.of("THESE", "Are", "SoMe", "TEst", "tOKEns", "One", "TWO", "thrEE"));
        for (int i = 0; i < 7; i++) {
            tokenList.readToken();
        }
        assertEquals(ImmutableList.of("Are", "SoMe", "TEst", "tOKEns", "One", "TWO"),
                tokenList.getRecentlyReadTokens());
    }

}
