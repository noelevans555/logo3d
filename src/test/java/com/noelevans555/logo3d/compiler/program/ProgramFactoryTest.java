package com.noelevans555.logo3d.compiler.program;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;
import com.noelevans555.logo3d.compiler.exception.SyntaxException;
import com.noelevans555.logo3d.compiler.program.instruction.Conditional;
import com.noelevans555.logo3d.compiler.program.instruction.Instruction;
import com.noelevans555.logo3d.compiler.program.instruction.InstructionFactory;
import com.noelevans555.logo3d.compiler.program.instruction.Make;
import com.noelevans555.logo3d.compiler.program.instruction.MoveForward;
import com.noelevans555.logo3d.compiler.program.instruction.PenDown;
import com.noelevans555.logo3d.compiler.program.instruction.Repeat;
import com.noelevans555.logo3d.compiler.program.instruction.RunProcedure;
import com.noelevans555.logo3d.compiler.program.instruction.TurnLeft;
import com.noelevans555.logo3d.compiler.program.parameter.NumericConstant;
import com.noelevans555.logo3d.compiler.program.parameter.Parameter;
import com.noelevans555.logo3d.compiler.program.parameter.ParameterFactory;
import com.noelevans555.logo3d.compiler.program.parameter.VariableReference;
import com.noelevans555.logo3d.compiler.program.tokens.TokenReader;
import com.noelevans555.logo3d.compiler.program.tokens.Tokenizer;

@RunWith(MockitoJUnitRunner.class)
public class ProgramFactoryTest {

    private InstructionFactory instructionFactory = new InstructionFactory();
    private ParameterFactory parameterFactory = new ParameterFactory();
    private Tokenizer tokenizer = new Tokenizer();

    private ProgramFactory programFactory = new ProgramFactory(instructionFactory, parameterFactory);

    @Test
    public void buildProgram_withPenDownInstruction_returnsExpectedProgram() throws Exception {
        // given
        Program expectedProgram = makeProgram(new PenDown());
        TokenReader tokenReader = tokenizer.tokenize("pendown");
        // when
        Program actualProgram = programFactory.buildProgram(tokenReader);
        // then
        assertEquals(expectedProgram, actualProgram);
        assertFalse(tokenReader.hasMoreTokens());
    }

    @Test
    public void buildProgram_withForwardInstruction_returnsExpectedProgram() throws Exception {
        // given
        Program expectedProgram = makeForwardProgram(5.5);
        TokenReader tokenReader = tokenizer.tokenize("forward 5.5");
        // when
        Program actualProgram = programFactory.buildProgram(tokenReader);
        // then
        assertEquals(expectedProgram, actualProgram);
        assertFalse(tokenReader.hasMoreTokens());
    }

    @Test
    public void buildProgram_withRepeatInstruction_returnsExpectedProgram() throws Exception {
        // given
        Program expectedProgram = makeProgram(new Repeat(new NumericConstant(12.1), makeForwardProgram(31.0)));
        TokenReader tokenReader = tokenizer.tokenize("repeat 12.1 [ forward 31.0 ]");
        // when
        Program actualProgram = programFactory.buildProgram(tokenReader);
        // then
        assertEquals(expectedProgram, actualProgram);
        assertFalse(tokenReader.hasMoreTokens());
    }

    @Test
    public void buildProgram_withMakeInstruction_returnsExpectedProgram() throws Exception {
        // given
        Program expectedProgram = makeProgram(new Make(true, "myVar", new NumericConstant(55.5)));
        TokenReader tokenReader = tokenizer.tokenize("make local myVar = 55.5");
        // when
        Program actualProgram = programFactory.buildProgram(tokenReader);
        // then
        assertEquals(expectedProgram, actualProgram);
        assertFalse(tokenReader.hasMoreTokens());
    }

    @Test
    public void buildProgram_withConditionalInstructionWithElse_returnsExpectedProgram() throws Exception {
        // given
        Program expectedProgram = makeProgram(new Conditional(new NumericConstant(17.1), ">", new NumericConstant(17.0),
                makeForwardProgram(1.0), makeForwardProgram(2.0)));
        TokenReader tokenReader = tokenizer.tokenize("if 17.1 > 17.0 [ forward 1 ] else [ forward 2 ]");
        // when
        Program actualProgram = programFactory.buildProgram(tokenReader);
        // then
        assertEquals(expectedProgram, actualProgram);
        assertFalse(tokenReader.hasMoreTokens());
    }

    @Test
    public void buildProgram_withConditionalInstructionWithoutElse_returnsExpectedProgram() throws Exception {
        // given
        Program expectedProgram = makeProgram(
                new Conditional(new NumericConstant(24.5), "<", new NumericConstant(24.8), makeForwardProgram(1.5)));
        TokenReader tokenReader = tokenizer.tokenize("if 24.5 < 24.8 [ forward 1.5 ]");
        // when
        Program actualProgram = programFactory.buildProgram(tokenReader);
        // then
        assertEquals(expectedProgram, actualProgram);
        assertFalse(tokenReader.hasMoreTokens());
    }

    @Test
    public void buildProgram_withProcedureRunInstruction_returnsExpectedProgram() throws Exception {
        // given
        Program repeatBody = new Program(ImmutableList.of(
                new MoveForward(new VariableReference(false, "size")),
                new TurnLeft(new NumericConstant(90.0))));
        Program repeatProgram = makeProgram(new Repeat(new NumericConstant(4.0), repeatBody));
        Procedure expectedProcedure = new Procedure("square", ImmutableList.of("size"), repeatProgram);
        Program expectedProgram = makeProgram(
                new RunProcedure(ImmutableList.of(new NumericConstant(10.0)), expectedProcedure));
        TokenReader tokenReader = tokenizer
                .tokenize("to square size [ repeat 4 [ forward size turnleft 90 ] ] square 10");
        // when
        Program actualProgram = programFactory.buildProgram(tokenReader);
        // then
        assertEquals(expectedProgram, actualProgram);
        assertFalse(tokenReader.hasMoreTokens());
    }

    @Test(expected = SyntaxException.class)
    public void buildProgram_withUndefinedProcedureReference_returnsExpectedProgram() throws Exception {
        // given
        TokenReader tokenReader = tokenizer.tokenize("circle 15");
        // when
        programFactory.buildProgram(tokenReader);
    }

    @Test(expected = SyntaxException.class)
    public void buildProgram_withUnopenedBox_throwsSyntaxException() throws Exception {
        // given
        TokenReader tokenReader = tokenizer.tokenize("repeat 10 [ forward 10 ] ]");
        // when
        programFactory.buildProgram(tokenReader);
    }

    private Program makeForwardProgram(final double distance) {
        Parameter parameter = new NumericConstant(distance);
        return makeProgram(new MoveForward(parameter));
    }

    private Program makeProgram(final Instruction instruction) {
        return new Program(ImmutableList.of(instruction));
    }

}
