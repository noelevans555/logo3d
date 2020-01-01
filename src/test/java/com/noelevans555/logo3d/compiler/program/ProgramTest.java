package com.noelevans555.logo3d.compiler.program;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;
import com.noelevans555.logo3d.compiler.exception.StopException;
import com.noelevans555.logo3d.compiler.program.instruction.Instruction;
import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.turtle.Turtle;

@RunWith(MockitoJUnitRunner.class)
public class ProgramTest {

    private static final Instruction INSTRUCTION_ONE = mock(Instruction.class);
    private static final Instruction INSTRUCTION_TWO = mock(Instruction.class);
    private static final Instruction INSTRUCTION_THREE = mock(Instruction.class);
    private static final List<Instruction> INSTRUCTIONS = ImmutableList.of(INSTRUCTION_ONE, INSTRUCTION_TWO,
            INSTRUCTION_THREE);

    @Mock
    private State state;

    @Mock
    private Turtle turtle;

    private Program program = new Program(INSTRUCTIONS);

    @Test
    public void run_thenCallsRunOnEachInstruction() throws Exception {
        program.run(state, turtle);
        verify(INSTRUCTION_ONE).run(state, turtle);
        verify(INSTRUCTION_TWO).run(state, turtle);
        verify(INSTRUCTION_THREE).run(state, turtle);
    }

    @Test
    public void run_whenInstructionStopped_thenExeptionIsThrownAndOnwardInstructionsNotRun() throws Exception {
        doThrow(new StopException()).when(INSTRUCTION_TWO).run(state, turtle);
        try {
            program.run(state, turtle);
            fail("Stop exception not thrown");
        } catch (StopException e) {
            verify(INSTRUCTION_ONE).run(state, turtle);
            verifyNoInteractions(INSTRUCTION_THREE);
        }
    }

}
