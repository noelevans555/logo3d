package com.noelevans555.logo3d.compiler.program.parameter.result;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.exception.SemanticException;
import com.noelevans555.logo3d.model.LogoColor;

/**
 * Represents the evaluation result of a Logo3d parameter. Can be combined with
 * other results to determine the result of larger expressions.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

public abstract class EvaluationResult {

    /**
     * Conditionally return the negation of the result, otherwise return the
     * original result.
     *
     * @param isNegated Whether the result is to be negated.
     * @return The conditionally inverted evaluation result
     */
    public EvaluationResult conditionallyNegate(final boolean isNegated) {
        return isNegated ? negate() : this;
    }

    /**
     * Returns the negation of this result.
     *
     * @return The negation of this result.
     */
    abstract EvaluationResult negate();

    /**
     * Returns a new result determined by applying the specified operator and
     * operand to this result.
     *
     * @param operator Operator to use to determine the new result.
     * @param operand Operand to use to determine the new result.
     * @return The new result after applying the operator and operand.
     * @throws CompilerException If the operation cannot be performed.
     */
    public abstract EvaluationResult combine(String operator, EvaluationResult operand) throws CompilerException;

    /**
     * Gets the value of this result as a numeric value.
     *
     * @param operation The operation to attribute any exception to (for user
     *        feedback).
     * @return The value of this result as a numeric value.
     * @throws SemanticException If the result cannot be interpreted as a numeric
     *         (i.e. it represents a color).
     */
    public double getNumeric(final String operation) throws SemanticException {
        throw new SemanticException(SemanticException.INVALID_COLOR_AS_INPUT, operation);
    }

    /**
     * Gets the value of this result as a LogoColor.
     *
     * @param operation The operation to attribute any exception to (for user
     *        feedback).
     * @return The value of this result as a LogoColor.
     * @throws SemanticException If the result cannot be represented as a LogoColor
     *         (i.e. it represents a numeric).
     */
    public LogoColor getColor(final String operation) throws SemanticException {
        throw new SemanticException(SemanticException.INVALID_NUMERIC_AS_INPUT, operation);
    }

}
