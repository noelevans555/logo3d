package com.noelevans555.logo3d.compiler.exception;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Represents an exception encountered during Logo3d compilation which is due to
 * the input program not specifying a mandatory token at a particular location.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

public class MissingTokenException extends SyntaxException {

    private static final String MESSAGE = "Missing token";

    private static final long serialVersionUID = 1L;

    private final Optional<String> expectedToken;
    private final Optional<Set<String>> expectedTokens;

    /**
     * Constructor.
     *
     * @param operation The operation being attempted at the time of the exception.
     * @param expectedToken The mandatory token which was expected to be read.
     * @param recentlyReadTokens The tokens which had been most recently read at the
     *        time of the exception, in the order in which they were read.
     */
    public MissingTokenException(final String operation, final String expectedToken,
            final List<String> recentlyReadTokens) {
        super(MESSAGE, operation, recentlyReadTokens);
        this.expectedToken = Optional.of(expectedToken);
        this.expectedTokens = Optional.empty();
    }

    /**
     * Constructor.
     *
     * @param operation The operation being attempted at the time of the exception.
     * @param expectedTokens A set of possible mandatory tokens, one of which was
     *        expected to be read.
     * @param recentlyReadTokens The tokens which had been most recently read at the
     *        time of the exception, in the order in which they were read.
     */
    public MissingTokenException(final String operation, final Set<String> expectedTokens,
            final List<String> recentlyReadTokens) {
        super(MESSAGE, operation, recentlyReadTokens);
        this.expectedToken = Optional.empty();
        this.expectedTokens = Optional.of(expectedTokens);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SyntaxException [message=" + getMessage() + ", operation=" + getOperation());
        expectedToken.ifPresent(e -> builder.append(", expectedToken=" + e));
        expectedTokens.ifPresent(e -> builder.append(", expectedTokens=" + e.toString()));
        builder.append(", recentlyReadTokens=" + getRecentlyReadTokens() + "]");
        return builder.toString();
    }

}
