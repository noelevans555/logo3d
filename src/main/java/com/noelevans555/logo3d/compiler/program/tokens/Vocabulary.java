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

    public static final Set<String> RESERVED_WORDS = ImmutableSet.of("forward", "fd", "turnleft", "lt", "turnright",
            "rt", "turnup", "up", "turndown", "dn", "rollleft", "rl", "rollright", "rr", "penup", "pu", "pendown", "pd",
            "stop", "setcolor", "sc", "randomcolor", "rc", "repeat", "if", "make", "local", "to", "pick", "random",
            "sin", "cos", "tan", "asin", "acos", "atan", "sqrt");

    /**
     * Private Constructor to prevent instantiation.
     */
    private Vocabulary() {
    }
}
