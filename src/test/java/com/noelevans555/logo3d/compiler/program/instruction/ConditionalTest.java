package com.noelevans555.logo3d.compiler.program.instruction;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.noelevans555.logo3d.compiler.program.Program;
import com.noelevans555.logo3d.compiler.program.parameter.Parameter;
import com.noelevans555.logo3d.compiler.program.parameter.result.NumericResult;

@RunWith(MockitoJUnitRunner.class)
public class ConditionalTest extends AbstractInstructionTest {

    private static final double LARGE_NUMERIC = 1023;
    private static final NumericResult LARGE_RESULT = new NumericResult(LARGE_NUMERIC);

    private static final double SMALL_NUMERIC = -1031;
    private static final NumericResult SMALL_RESULT = new NumericResult(SMALL_NUMERIC);

    @Mock
    private Parameter leftParameter;

    @Mock
    private Parameter rightParameter;

    @Mock
    private Program ifProgram;

    @Mock
    private Program elseProgram;

    @Test
    public void run_withSatisfiedGreaterThan_thenRunsIfProgram() throws Exception {
        Conditional conditional = prepareConditional(LARGE_RESULT, ">", SMALL_RESULT, null);
        conditional.run(state, turtle);
        verify(ifProgram).run(state, turtle);
        verifyNoInteractions(elseProgram);
        verifyStateInteractions();
    }

    @Test
    public void run_withUnSatisfiedGreaterThan_thenNoProgramRun() throws Exception {
        Conditional conditional = prepareConditional(SMALL_RESULT, ">", LARGE_RESULT, null);
        conditional.run(state, turtle);
        verifyNoInteractions(ifProgram);
        verifyNoInteractions(elseProgram);
        verifyNoInteractions(state);
    }

    @Test
    public void run_withSatisfiedLessThanWithElse_thenRunsIfProgram() throws Exception {
        Conditional conditional = prepareConditional(SMALL_RESULT, "<", LARGE_RESULT, elseProgram);
        conditional.run(state, turtle);
        verify(ifProgram).run(state, turtle);
        verifyNoInteractions(elseProgram);
        verifyStateInteractions();
    }

    @Test
    public void run_withUnSatisfiedLessThanWithElse_thenRunsElseProgram() throws Exception {
        Conditional conditional = prepareConditional(LARGE_RESULT, "<", SMALL_RESULT, elseProgram);
        conditional.run(state, turtle);
        verify(elseProgram).run(state, turtle);
        verifyNoInteractions(ifProgram);
        verifyStateInteractions();
    }

    private Conditional prepareConditional(final NumericResult leftHandResult, String operator,
            final NumericResult rightHandResult, final Program elseProgram) throws Exception {
        when(leftParameter.evaluate(state)).thenReturn(leftHandResult);
        when(rightParameter.evaluate(state)).thenReturn(rightHandResult);
        if (elseProgram == null) {
            return new Conditional(leftParameter, operator, rightParameter, ifProgram);
        }
        return new Conditional(leftParameter, operator, rightParameter, ifProgram, elseProgram);
    }

    public void verifyStateInteractions() {
        InOrder inOrder = inOrder(state);
        inOrder.verify(state).pushStack();
        inOrder.verify(state).popStack();
    }

}
