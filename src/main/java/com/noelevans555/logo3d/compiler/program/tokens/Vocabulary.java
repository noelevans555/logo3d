package com.noelevans555.logo3d.compiler.program.tokens;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

/**
 * Registry of tokens frequently used in Logo3d programs.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

public final class Vocabulary {

    public static final String TO = "to";

    public static final String PICK = "pick";

    public static final String ELSE = "else";

    public static final String LOCAL = "local";

    public static final Set<String> RANDOM_COLOR = ImmutableSet.of("randomcolor", "rc");

    public static final String MINUS = "-";

    public static final String EQUALS = "=";

    public static final String BOX_OPEN = "[";

    public static final String BOX_CLOSE = "]";

    public static final String DELIMITER = "|";

    public static final Set<String> OPERATORS = ImmutableSet.of("+", "-", "*", "/");

    public static final Set<String> COMPARATORS = ImmutableSet.of(">", "<");

    public static final Set<String> BUILT_IN_FUNCTIONS = ImmutableSet.of("random", "sqrt", "sin", "cos", "tan", "asin",
            "acos", "atan");

    public static final Set<String> RESERVED_WORDS = ImmutableSet.of("acos", "asin", "atan", "cos", "dn", "fd",
            "forward", "if", "local", "lt", "make", "pd", "pendown", "penup", "pick", "pu", "random", "randomcolor",
            "rc", "repeat", "rl", "rollleft", "rollright", "rr", "rt", "sc", "setcolor", "sin", "sqrt", "stop", "tan",
            "to", "turndown", "turnleft", "turnright", "turnup", "up");

    /**
     * Private Constructor to prevent instantiation.
     */
    private Vocabulary() {
    }
}
