package com.noelevans555.logo3d.compiler.program.parameter;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.exception.SyntaxException;
import com.noelevans555.logo3d.compiler.program.tokens.TokenReader;

public class ParameterFactoryTest extends AbstractParameterTest {

    private ParameterFactory parameterFactory = new ParameterFactory();

    @Test(expected = SyntaxException.class)
    public void buildParameter_withNoTokens_throwsSyntaxException() throws Exception {
        buildParameterWithTokens();
    }

    @Test(expected = SyntaxException.class)
    public void buildParameter_withReservedWord_throwsSyntaxException() throws Exception {
        System.out.println(buildParameterWithTokens("repeat"));
    }

    @Test
    public void buildParameter_forPositiveConstant_returnsPositiveConstant() throws Exception {
        Parameter parameter = buildParameterWithTokens("3.6");
        assertEquals(new NumericConstant(3.6), parameter);
    }

    @Test
    public void buildParameter_forNegativeConstant_returnsNegativeConstant() throws Exception {
        Parameter parameter = buildParameterWithTokens("-", "0.8");
        assertEquals(new NumericConstant(true, 0.8), parameter);
    }

    @Test
    public void buildParameter_forDoubleNegativeConstant_returnsPositiveConstant() throws Exception {
        Parameter parameter = buildParameterWithTokens("-", "-", "6.3");
        assertEquals(new NumericConstant(6.3), parameter);
    }

    @Test(expected = SyntaxException.class)
    public void buildParameter_forPickWithNoParameters_throwsSyntaxException() throws Exception {
        buildParameterWithTokens("pick", "[", "]");
    }

    @Test
    public void buildParameter_forPositivePick_returnsPositivePick() throws Exception {
        Parameter parameter = buildParameterWithTokens("pick", "[", "8.0", "0.5", "]");
        assertEquals(new Pick(false, ImmutableList.of(new NumericConstant(8.0), new NumericConstant(0.5))), parameter);
    }

    @Test
    public void buildParameter_forNegativePick_returnsNegativePick() throws Exception {
        Parameter parameter = buildParameterWithTokens("-", "PICK", "[", "42.2", "13.1", "]");
        assertEquals(new Pick(true, ImmutableList.of(new NumericConstant(42.2), new NumericConstant(13.1))), parameter);
    }

    @Test
    public void buildParameter_forPickWithDelimiter_returnsPickWithIndividualParameters() throws Exception {
        Parameter parameter = buildParameterWithTokens("Pick", "[", "12.7", "|", "-", "6.3", "]");
        assertEquals(new Pick(false, ImmutableList.of(new NumericConstant(12.7), new NumericConstant(true, 6.3))),
                parameter);
    }

    @Test(expected = SyntaxException.class)
    public void buildParameter_forColorDefinitionWithMissingClose_throwsSyntaxException() throws Exception {
        buildParameterWithTokens("[", "5.3", "6.1", "2.4", "6.3");
    }

    @Test
    public void buildParameter_forPositiveColorDefinition_returnsPositiveColorDefinition() throws Exception {
        Parameter parameter = buildParameterWithTokens("[", "541.2", "63.1", "94.7", "]");
        assertEquals(new ColorDefinition(false, new NumericConstant(541.2), new NumericConstant(63.1),
                new NumericConstant(94.7)), parameter);
    }

    @Test
    public void buildParameter_forNegativeColorDefinition_returnsNegativeColorDefinition() throws Exception {
        Parameter parameter = buildParameterWithTokens("-", "[", "18.9", "31.4", "73.0", "]");
        assertEquals(new ColorDefinition(true, new NumericConstant(18.9), new NumericConstant(31.4),
                new NumericConstant(73.0)), parameter);
    }

    @Test
    public void buildParameter_forColorDefinitionWithDelimiters_returnsColorDefinition() throws Exception {
        Parameter parameter = buildParameterWithTokens("[", "4.7", "|", "15.0", "|", "-", "31.3", "]");
        assertEquals(new ColorDefinition(false, new NumericConstant(4.7), new NumericConstant(15.0),
                new NumericConstant(true, 31.3)), parameter);
    }

    @Test
    public void buildParameter_forPositiveRandomColor_returnsPositiveRandomColor() throws Exception {
        Parameter parameter = buildParameterWithTokens("randomcolor");
        assertEquals(new RandomColor(false), parameter);
    }

    @Test
    public void buildParameter_forNegativeRandomColor_returnsNegativeRandomColor() throws Exception {
        Parameter parameter = buildParameterWithTokens("-", "rc");
        assertEquals(new RandomColor(true), parameter);
    }

    @Test
    public void buildParameter_forPositiveBuiltInFunction_returnsPositiveBuiltInFunction() throws Exception {
        Parameter parameter = buildParameterWithTokens("cos", "45.3");
        assertEquals(new BuiltInFunction(false, "cos", new NumericConstant(45.3)), parameter);
    }

    @Test
    public void buildParameter_forNegativeBuiltInFunction_returnsPositiveBuiltInFunction() throws Exception {
        Parameter parameter = buildParameterWithTokens("-", "atan", "0.7");
        assertEquals(new BuiltInFunction(true, "atan", new NumericConstant(0.7)), parameter);
    }

    @Test
    public void buildParameter_forPositiveVariableReference_returnsPositiveVariableReference() throws Exception {
        Parameter parameter = buildParameterWithTokens("myVariable");
        assertEquals(new VariableReference(false, "myVariable"), parameter);
    }

    @Test
    public void buildParameter_forNegativeVariableReference_returnsNegativeVariableReference() throws Exception {
        Parameter parameter = buildParameterWithTokens("-", "myVariable");
        assertEquals(new VariableReference(true, "myVariable"), parameter);
    }

    @Test
    public void buildParameter_forExpression_returnsExpression() throws CompilerException {
        Parameter parameter = buildParameterWithTokens("4.3", "+", "var_a", "-", "-", "-", "0.01", "*", "-", "var_b",
                "/", "6.0");
        Expression expectedExpression = new Expression(
                ImmutableList.of(new NumericConstant(4.3), new VariableReference(false, "var_a"),
                        new NumericConstant(0.01), new VariableReference(true, "var_b"), new NumericConstant(6.0)),
                ImmutableList.of("+", "-", "*", "/"));
        assertEquals(expectedExpression, parameter);
    }

    private Parameter buildParameterWithTokens(String... stringTokens) throws CompilerException {
        TokenReader tokenList = new TokenReader(Arrays.asList(stringTokens));
        return parameterFactory.buildParameter(tokenList);
    }

}
