package com.noelevans555.logo3d.compiler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;
import com.noelevans555.logo3d.compiler.exception.StopException;
import com.noelevans555.logo3d.compiler.program.Program;
import com.noelevans555.logo3d.compiler.program.ProgramFactory;
import com.noelevans555.logo3d.compiler.program.tokens.TokenReader;
import com.noelevans555.logo3d.compiler.program.tokens.Tokenizer;
import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.state.StateFactory;
import com.noelevans555.logo3d.compiler.turtle.Turtle;
import com.noelevans555.logo3d.compiler.turtle.TurtleFactory;
import com.noelevans555.logo3d.model.LogoColor;
import com.noelevans555.logo3d.model.LogoLine;
import com.noelevans555.logo3d.model.LogoPoint;

@RunWith(MockitoJUnitRunner.class)
public class LogoCompilerTest {

    private static final String TEST_PROGRAM = "test program";
    private static final List<LogoLine> TURTLE_OUTPUT = ImmutableList
            .of(new LogoLine(new LogoPoint(0, 0, 0), new LogoPoint(1, 1, 1), new LogoColor(255, 255, 255)));

    @Mock
    private TokenReader tokenReader;
    @Mock
    private Tokenizer tokenizer;
    @Mock
    private Program program;
    @Mock
    private ProgramFactory programFactory;
    @Mock
    private State state;
    @Mock
    private StateFactory stateFactory;
    @Mock
    private Turtle turtle;
    @Mock
    private TurtleFactory turtleFactory;
    @Mock
    private RuntimeLimits runtimeLimits;

    @InjectMocks
    private LogoCompiler logoCompiler;

    @Before
    public void setup() throws Exception {
        when(tokenizer.tokenize(TEST_PROGRAM)).thenReturn(tokenReader);
        when(programFactory.buildProgram(tokenReader, runtimeLimits)).thenReturn(program);
        when(stateFactory.buildState(runtimeLimits)).thenReturn(state);
        when(turtleFactory.buildTurtle(runtimeLimits)).thenReturn(turtle);
        when(turtle.getLogoLines()).thenReturn(TURTLE_OUTPUT);
    }

    @Test
    public void compileProgram_runsProgramAndReturnsTurtleLines() throws Exception {
        assertEquals(TURTLE_OUTPUT, logoCompiler.compileAndRunProgram(TEST_PROGRAM, runtimeLimits));
        verify(program).run(state, turtle);
    }

    @Test
    public void compileProgram_whenStoppedFromRunning_returnsTurtleLines() throws Exception {
        doThrow(new StopException()).when(program).run(state, turtle);
        assertEquals(TURTLE_OUTPUT, logoCompiler.compileAndRunProgram(TEST_PROGRAM, runtimeLimits));
        verify(program).run(state, turtle);
    }

}
