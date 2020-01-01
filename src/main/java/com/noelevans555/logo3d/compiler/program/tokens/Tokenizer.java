package com.noelevans555.logo3d.compiler.program.tokens;

import java.util.ArrayList;
import java.util.List;

/**
 * Splits a Logo3d program defined as a single string into its constituent
 * tokens.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

public class Tokenizer {

    /**
     * Splits a Logo3d program defined as a single string into its constituent
     * tokens.
     *
     * @param program A Logo3d program defined as a single string.
     * @return A TokenReader for individual access to program tokens.
     */
    public TokenReader tokenize(final String program) {

        List<String> tokenList = new ArrayList<>();
        if (program == null) {
            return new TokenReader(tokenList);
        }

        StringBuilder currentToken = new StringBuilder();

        char[] chars = program.toCharArray();
        for (int charIndex = 0; charIndex < chars.length; charIndex++) {

            char currentChar = chars[charIndex];
            switch (currentChar) {
            case ';':
                // skip chars to end of line
                addCurrentTokenIfNotEmpty(currentToken.toString(), tokenList);
                while (charIndex < chars.length && chars[charIndex] != '\n') {
                    charIndex++;
                }
                currentToken = new StringBuilder();
                break;
            case '+':
            case '-':
            case '*':
            case '/':
            case '<':
            case '>':
            case '=':
            case '(':
            case ')':
            case '[':
            case ']':
            case '|':
            case ' ':
            case '\t':
            case '\n':
                addCurrentTokenIfNotEmpty(currentToken.toString(), tokenList);
                // record newly read token
                addCurrentTokenIfNotEmpty(String.valueOf(currentChar), tokenList);
                currentToken = new StringBuilder();
                break;
            default:
                currentToken.append(currentChar);
                break;
            }
        }
        addCurrentTokenIfNotEmpty(currentToken.toString(), tokenList);
        return new TokenReader(tokenList);
    }

    /**
     * If the currently considered token is not blank, records it in the specified
     * list of tokens.
     *
     * @param currentToken The currently considered token.
     * @param tokens List of tokens to which to add add non-blank tokens.
     */
    private void addCurrentTokenIfNotEmpty(final String currentToken, final List<String> tokens) {
        if (currentToken.trim().isEmpty()) {
            return;
        }
        tokens.add(currentToken);
    }

}
