package com.noelevans555.logo3d.compiler.program.instruction;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;
import com.noelevans555.logo3d.compiler.RuntimeLimits;
import com.noelevans555.logo3d.compiler.exception.InternalException;
import com.noelevans555.logo3d.compiler.program.Procedure;
import com.noelevans555.logo3d.compiler.program.Program;
import com.noelevans555.logo3d.compiler.program.instruction.InstructionFactory.AssemblyStep;
import com.noelevans555.logo3d.compiler.program.parameter.Parameter;

@RunWith(MockitoJUnitRunner.class)
public class InstructionFactoryTest {

    private static final int TEST_TIMEOUT_IN_SECONDS = 10;
    private static final RuntimeLimits TEST_RUNTIME_LIMITS = new RuntimeLimits(TEST_TIMEOUT_IN_SECONDS, 100, 100);

    @Mock
    private Parameter parameter;

    private InstructionFactory instructionFactory = new InstructionFactory();

    @Test
    public void getAssemblySteps_whenRecognizedInstruction_returnsInstructionSteps() {
        AssemblyStep[] assemblySteps = instructionFactory.getAssemblySteps("forward");
        assertArrayEquals(new AssemblyStep[] { AssemblyStep.PARAMETER }, assemblySteps);
    }

    @Test
    public void getAssemblySteps_whenUnRecognizedInstruction_returnsDefaultSteps() {
        AssemblyStep[] assemblySteps = instructionFactory.getAssemblySteps("cube");
        assertArrayEquals(new AssemblyStep[] { AssemblyStep.RUN_PROCEDURE }, assemblySteps);
    }

    @Test
    public void buildInstruction_whenMoveForwardAssembly_returnsMoveForwardInstruction() throws Exception {
        Instruction instruction = instructionFactory.buildInstruction("forward", ImmutableList.of(parameter));
        assertTrue(instruction instanceof MoveForward);
    }

    @Test
    public void buildInstruction_whenRunProcedureAssembly_returnsRunProcedureInstruction() throws Exception {
        List<Parameter> parameters = ImmutableList.of(parameter);
        Procedure procedure = new Procedure("test", ImmutableList.of());
        Instruction instruction = instructionFactory.buildInstruction("cube", ImmutableList.of(parameters, procedure));
        assertTrue(instruction instanceof RunProcedure);
    }

    @Test
    public void buildInstruction_whenRepeatAssembly_returnsRepeatInstruction() throws Exception {
        Program program = new Program(ImmutableList.of());
        Instruction instruction = instructionFactory.buildInstruction("repeat", ImmutableList.of(parameter, program));
        assertTrue(instruction instanceof Repeat);
    }

    @Test(expected = InternalException.class)
    public void buildInstruction_whenMismatchedAssembly_throwsInternalException() throws Exception {
        Instruction instruction = instructionFactory.buildInstruction("repeat", ImmutableList.of(parameter));
        assertTrue(instruction instanceof Repeat);
    }

    @Test
    public void buildTimeoutCheckInstruction_withRuntimeLimits_thenReturnsExpectedTimeoutCheck() throws Exception {
        Instruction instruction = instructionFactory.buildTimeoutCheckInstruction(TEST_RUNTIME_LIMITS);
        assertEquals(new TimeoutCheck(TEST_TIMEOUT_IN_SECONDS), instruction);
    }

}
