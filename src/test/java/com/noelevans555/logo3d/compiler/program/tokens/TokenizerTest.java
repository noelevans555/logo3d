package com.noelevans555.logo3d.compiler.program.tokens;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class TokenizerTest {

    private Tokenizer tokenizer = new Tokenizer();

    @Test
    public void tokenize_whenNullProgram_returnsZeroTokens() throws Exception {
        TokenReader tokens = tokenizer.tokenize(null);
        assertFalse(tokens.hasMoreTokens());
    }

    @Test
    public void tokenize_whenBlankProgram_returnsZeroTokens() throws Exception {
        TokenReader tokens = tokenizer.tokenize("   ");
        assertFalse(tokens.hasMoreTokens());
    }

    @Test
    public void tokenize_whenProgramHasTerminalComment_returnsNoComments() throws Exception {
        TokenReader tokens = tokenizer.tokenize("fd 10;lt 90 fd 50 rt 50 fd 30");
        assertEquals(ImmutableList.of("fd", "10"), readAllTokens(tokens));
    }

    @Test
    public void tokenize_whenProgramHasInterstitialComment_returnsNoComment() throws Exception {
        TokenReader tokens = tokenizer.tokenize("fd 10;lt 90 fd 50 rt 50 fd 30\nfd 20");
        assertEquals(ImmutableList.of("fd", "10", "fd", "20"), readAllTokens(tokens));
    }

    private static List<String> readAllTokens(final TokenReader tokens) throws Exception {
        List<String> allTokens = new ArrayList<>();
        while (tokens.hasMoreTokens()) {
            allTokens.add(tokens.readToken());
        }
        return allTokens;
    }

}
