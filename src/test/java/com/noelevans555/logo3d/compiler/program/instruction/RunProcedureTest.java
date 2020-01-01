package com.noelevans555.logo3d.compiler.program.instruction;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;
import com.noelevans555.logo3d.compiler.exception.StopException;
import com.noelevans555.logo3d.compiler.program.Procedure;
import com.noelevans555.logo3d.compiler.program.Program;
import com.noelevans555.logo3d.compiler.program.parameter.Parameter;

@RunWith(MockitoJUnitRunner.class)
public class RunProcedureTest extends AbstractInstructionTest {

    private static final String PARAMETER_NAME_ONE = "myParameterOne";
    private static final String PARAMETER_NAME_TWO = "myParameterTwo";

    private static final String PROCEDURE_NAME = "myProcedure";

    @Mock
    private Program program;

    @Test
    public void run_withZeroParameterProcedure_thenProcedureIsRun() throws Exception {
        // given
        Procedure procedure = new Procedure(PROCEDURE_NAME, ImmutableList.of(), program);
        RunProcedure runProcedure = new RunProcedure(ImmutableList.of(), procedure);
        // when
        runProcedure.run(state, turtle);
        // then
        verify(program).run(state, turtle);
        verifyState(false);
    }

    @Test
    public void run_whenProgramThrowsStoppedException_thenExceptionIsConsumed() throws Exception {
        // given
        doThrow(new StopException()).when(program).run(state, turtle);
        Procedure procedure = new Procedure(PROCEDURE_NAME, ImmutableList.of(), program);
        RunProcedure runProcedure = new RunProcedure(ImmutableList.of(), procedure);
        // when
        runProcedure.run(state, turtle);
        // then
        verify(program).run(state, turtle);
        verifyState(false);
    }

    @Test
    public void run_withMultipleParameterProcedure_thenProcedureIsRun() throws Exception {
        // given
        Parameter parameterOne = mock(Parameter.class);
        when(parameterOne.evaluate(state)).thenReturn(TEST_NUMERIC_RESULT);
        Parameter parameterTwo = mock(Parameter.class);
        when(parameterTwo.evaluate(state)).thenReturn(TEST_COLOR_RESULT);
        Procedure procedure = new Procedure(PROCEDURE_NAME, ImmutableList.of(PARAMETER_NAME_ONE, PARAMETER_NAME_TWO),
                program);
        RunProcedure runProcedure = new RunProcedure(ImmutableList.of(parameterOne, parameterTwo), procedure);
        // when
        runProcedure.run(state, turtle);
        // then
        verify(program).run(state, turtle);
        verifyState(true);
    }

    public void verifyState(final boolean assertValuesAreStored) {
        InOrder inOrder = inOrder(state);
        inOrder.verify(state).pushStack();
        if (assertValuesAreStored) {
            inOrder.verify(state).storeResult(PARAMETER_NAME_ONE, TEST_NUMERIC_RESULT, true);
            inOrder.verify(state).storeResult(PARAMETER_NAME_TWO, TEST_COLOR_RESULT, true);
        }
        inOrder.verify(state).popStack();
    }

}
