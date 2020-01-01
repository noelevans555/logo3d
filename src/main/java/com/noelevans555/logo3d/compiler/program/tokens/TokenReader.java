package com.noelevans555.logo3d.compiler.program.tokens;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.noelevans555.logo3d.compiler.exception.MissingTokenException;
import com.noelevans555.logo3d.compiler.exception.SyntaxException;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Provides ordered access to the constituent tokens of a Logo3d program.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@RequiredArgsConstructor
@ToString
public class TokenReader {

    private static final String OPERATION = "ReadProgram";

    private static final int RECENT_TOKEN_COUNT = 6;

    private final List<String> tokens;
    private int readIndex = 0;

    /**
     * Whether the reader contains tokens which have not yet been read.
     *
     * @return Whether the reader contains tokens which have not yet been read.
     */
    public boolean hasMoreTokens() {
        return readIndex < tokens.size();
    }

    /**
     * Read the next token which has not yet been read.
     *
     * @return The next token which has not previously been read.
     * @throws SyntaxException If there are no further tokens which have not yet
     *         been read.
     */
    public String readToken() throws SyntaxException {
        if (!hasMoreTokens()) {
            throw new SyntaxException(SyntaxException.UNEXPECTED_END_OF_PROGRAM, OPERATION, getRecentlyReadTokens());
        }
        return readTokenInternal();
    }

    /**
     * Internal method to read the next token. The calling method is responsible for
     * ensuring that unread tokens are available.
     *
     * @return The next token which has not previously been read.
     */
    private String readTokenInternal() {
        return tokens.get(readIndex++);
    }

    /**
     * Take a peek at the next token which has not yet been read. The returned token
     * is not considered to have been read.
     *
     * @return The next token which has not previously been read, or empty if no
     *         unread tokens remain.
     */
    public Optional<String> peek() {
        if (!hasMoreTokens()) {
            return Optional.empty();
        }
        return Optional.of(tokens.get(readIndex));
    }

    /**
     * Reads the next token, throwing an exception if it does not have the expected
     * value.
     *
     * @param expectedToken The token which is expected to be read.
     * @param operation The operation to attribute any exception to (for user
     *        feedback).
     * @throws SyntaxException If the expected token is not read, or the reader
     *         contains no more unread tokens.
     */
    public void readExpectedToken(final String expectedToken, final String operation) throws SyntaxException {
        if (!expectedToken.equalsIgnoreCase(readToken())) {
            throw new MissingTokenException(operation, expectedToken, getRecentlyReadTokens());
        }
    }

    /**
     * Reads the next token, throwing an exception if it is not from an expected set
     * of values.
     *
     * @param expectedTokens A set of tokens, one of which the read token is
     *        expected to match.
     * @param operation The operation to attribute any exception to (for user
     *        feedback).
     * @throws SyntaxException If the read token is not from the expected set of
     *         values, or the reader contains no more tokens.
     * @return The read token (if from the expected set of values).
     */
    public String readExpectedToken(final Set<String> expectedTokens, final String operation) throws SyntaxException {
        String next = readToken();
        if (expectedTokens.contains(next.toLowerCase())) {
            return next;
        }
        throw new MissingTokenException(operation, expectedTokens, getRecentlyReadTokens());
    }

    /**
     * Reads the next token only if it matches an expected value.
     *
     * @param optionalToken The optional token to read.
     * @return Whether the optional token was read.
     */
    public boolean readOptionalToken(final String optionalToken) {
        Optional<String> peeked = peek();
        if (peeked.isPresent() && optionalToken.equalsIgnoreCase(peeked.get())) {
            readTokenInternal();
            return true;
        }
        return false;
    }

    /**
     * Reads the next token only if it matches a value from an expected set.
     *
     * @param optionalTokens The set of optional tokens from which to read.
     * @return The read token value, or empty if the next token does not match a
     *         value from the expected set.
     */
    public Optional<String> readOptionalToken(final Set<String> optionalTokens) {
        Optional<String> peeked = peek();
        if (peeked.isPresent() && optionalTokens.contains(peeked.get().toLowerCase())) {
            return Optional.of(readTokenInternal());
        }
        return Optional.empty();
    }

    /**
     * Reads the next token if it represents a valid Logo3d name, i.e. starts with
     * an alphabetical character and is not a reserved word.
     *
     * @param operation The operation to attribute any exception to (for user
     *        feedback).
     * @return The read Logo3d name (if valid)
     * @throws SyntaxException If the reader contains no more unread tokens, or the
     *         read token does not represent a valid Logo3d name.
     */
    public String readValidName(final String operation) throws SyntaxException {
        Optional<String> reservedWord = readOptionalToken(Vocabulary.RESERVED_WORDS);
        if (reservedWord.isPresent()) {
            throw new SyntaxException(SyntaxException.INVALID_NAME, operation, getRecentlyReadTokens());
        }
        String name = readToken();
        if (!Character.isLetter(name.charAt(0))) {
            throw new SyntaxException(SyntaxException.INVALID_NAME, operation, getRecentlyReadTokens());
        }
        return name;
    }

    /**
     * Continue reading the next tokens while they continue to represent Logo3d
     * delimiters.
     */
    public void readDelimiters() {
        while (true) {
            Optional<String> peeked = peek();
            if (peeked.isEmpty() || !Vocabulary.DELIMITER.equals(peeked.get())) {
                break;
            }
            readTokenInternal();
        }
    }

    /**
     * Returns the most recently read tokens in the order they were read.
     *
     * @return The most recently read tokens.
     */
    public List<String> getRecentlyReadTokens() {
        List<String> recentlyReadTokens = new ArrayList<>();
        for (int tokenIndex = Math.max(readIndex - RECENT_TOKEN_COUNT, 0); tokenIndex < readIndex; tokenIndex++) {
            recentlyReadTokens.add(tokens.get(tokenIndex));
        }
        return recentlyReadTokens;
    }

}
