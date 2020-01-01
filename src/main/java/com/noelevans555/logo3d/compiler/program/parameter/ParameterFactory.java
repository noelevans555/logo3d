package com.noelevans555.logo3d.compiler.program.parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.noelevans555.logo3d.compiler.exception.SyntaxException;
import com.noelevans555.logo3d.compiler.program.tokens.TokenReader;
import com.noelevans555.logo3d.compiler.program.tokens.Vocabulary;

/**
 * Constructs Logo3d parameters.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

public class ParameterFactory {

    private static final String OPERATION = "BuildParameter";

    /**
     * Greedily consumes tokens until the largest-possible Logo3d parameter has been
     * constructed, i.e. possibly assembled from multiple individual 'operand'
     * parameters combined in an expression.
     *
     * @param tokenReader Source of tokens to read.
     * @return The constructed Logo3d parameter.
     * @throws SyntaxException If a valid Logo3d parameter cannot be constructed.
     */
    public Parameter buildParameter(final TokenReader tokenReader) throws SyntaxException {

        List<Parameter> operands = new ArrayList<>();
        List<String> operators = new ArrayList<>();

        while (true) {

            operands.add(buildOperand(tokenReader));

            if (!tokenReader.hasMoreTokens()) {
                break;
            }

            Optional<String> operator = tokenReader.readOptionalToken(Vocabulary.OPERATORS);
            if (operator.isPresent()) {
                operators.add(operator.get());
            } else {
                break;
            }
        }

        return operands.size() == 1 ? operands.get(0) : new Expression(operands, operators);
    }

    /**
     * Reads as many tokens as necessary to construct an 'operand' parameter, i.e. a
     * single element of a Logo3d expression.
     *
     * @param tokenReader Source of tokens to read.
     * @return An 'operand' parameter.
     * @throws SyntaxException If a valid Logo3d parameter cannot be constructed.
     */
    private Parameter buildOperand(final TokenReader tokenReader) throws SyntaxException {

        boolean isNegated = false;
        while (tokenReader.readOptionalToken(Vocabulary.MINUS)) {
            isNegated = !isNegated;
        }

        if (tokenReader.readOptionalToken(Vocabulary.PICK)) {
            return buildPick(isNegated, tokenReader);
        }

        if (tokenReader.readOptionalToken(Vocabulary.BOX_OPEN)) {
            return buildColorDefinition(isNegated, tokenReader);
        }

        if (tokenReader.readOptionalToken(Vocabulary.RANDOM_COLOR).isPresent()) {
            return new RandomColor(isNegated);
        }

        Optional<String> builtInFunction = tokenReader.readOptionalToken(Vocabulary.BUILT_IN_FUNCTIONS);
        if (builtInFunction.isPresent()) {
            return new BuiltInFunction(isNegated, builtInFunction.get(), buildParameter(tokenReader));
        }

        NumericConstant constant = buildNumericConstant(isNegated, tokenReader.peek());
        if (constant != null) {
            tokenReader.readToken();
            return constant;
        }

        Optional<String> reservedWord = tokenReader.readOptionalToken(Vocabulary.RESERVED_WORDS);
        if (reservedWord.isPresent()) {
            throw new SyntaxException(SyntaxException.INVALID_PARAMETER, OPERATION,
                    tokenReader.getRecentlyReadTokens());
        }

        return new VariableReference(isNegated, tokenReader.readToken());
    }

    /**
     * Reads as many tokens as necessary to construct a Pick parameter.
     *
     * @param isNegated Whether the result of the parameter is to be negated in the
     *        wider expression.
     * @param tokenReader Source of tokens.
     * @return A pick parameter.
     * @throws SyntaxException If a valid Pick parameter cannot be constructed.
     */
    private Pick buildPick(final boolean isNegated, final TokenReader tokenReader) throws SyntaxException {
        tokenReader.readExpectedToken(Vocabulary.BOX_OPEN, Vocabulary.PICK);
        List<Parameter> pickParameters = new ArrayList<>();
        while (true) {
            if (tokenReader.readOptionalToken(Vocabulary.BOX_CLOSE)) {
                break;
            }
            tokenReader.readDelimiters();
            pickParameters.add(buildParameter(tokenReader));
        }
        if (pickParameters.isEmpty()) {
            throw new SyntaxException(SyntaxException.MISSING_VALUES, Vocabulary.PICK,
                    tokenReader.getRecentlyReadTokens());
        }
        return new Pick(isNegated, pickParameters);
    }

    /**
     * Reads as many tokens as necessary to construct a ColorDefinition parameter.
     *
     * @param isNegated Whether the result of the parameter is to be negated in the
     *        wider expression.
     * @param tokenReader Source of tokens.
     * @return A ColorDefinition parameter.
     * @throws SyntaxException If a valid ColorDefinition parameter cannot be
     *         constructed.
     */
    private ColorDefinition buildColorDefinition(final boolean isNegated, final TokenReader tokenReader)
            throws SyntaxException {

        Parameter redParameter = buildParameter(tokenReader);
        tokenReader.readDelimiters();
        Parameter greenParameter = buildParameter(tokenReader);
        tokenReader.readDelimiters();
        Parameter blueParameter = buildParameter(tokenReader);
        tokenReader.readExpectedToken(Vocabulary.BOX_CLOSE, "DefineColor");
        return new ColorDefinition(isNegated, redParameter, greenParameter, blueParameter);
    }

    /**
     * Attempts to construct a valid NumericConstant parameter from the token value.
     *
     * @param isNegated Whether the result of the parameter is to be negated in the
     *        wider expression.
     * @param token The token to construct from.
     * @return The constructed NumericConstant, or null if unable to construct.
     */
    private NumericConstant buildNumericConstant(final boolean isNegated, final Optional<String> token) {
        try {
            return new NumericConstant(isNegated, Double.parseDouble(token.get()));
        } catch (Exception e) {
            return null;
        }
    }

}
