package com.noelevans555.logo3d.compiler.program.instruction;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.noelevans555.logo3d.compiler.exception.InternalException;
import com.noelevans555.logo3d.compiler.program.Program;
import com.noelevans555.logo3d.compiler.program.parameter.Parameter;

import lombok.Getter;

/**
 * Provides the steps necessary to assemble each type of Logo3d instruction (as
 * identified by the opening token of each instruction type), and translates the
 * results of following those steps into an instruction.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */
public class InstructionFactory {

    /**
     * Program elements which can be combined in various ways to make different
     * instructions.
     */
    public enum AssemblyStep {
        PARAMETER, COMPARATOR, NAME, EQUALS, PROGRAM, OPTIONAL_ELSE, OPTIONAL_LOCAL, RUN_PROCEDURE
    }

    /**
     * Defines the implementation class and assembly steps for each type of Logo3d
     * instruction.
     */
    @Getter
    private static class InstructionDefinition {

        private final Class<? extends Instruction> implementationClass;
        private final AssemblyStep[] assemblySteps;

        /**
         * Constructor for an instruction which has no assembly steps, i.e. only the
         * name (leading token) of the instruction is required, e.g. 'penup'.
         *
         * @param implementationClass Instruction implementation class.
         */
        InstructionDefinition(final Class<? extends Instruction> implementationClass) {
            this.implementationClass = implementationClass;
            this.assemblySteps = new AssemblyStep[0];
        }

        /**
         * Constructor for an instruction that has assembly steps.
         *
         * @param implementationClass Instruction implementation class.
         * @param assemblySteps Steps required to assemble the instruction.
         */
        InstructionDefinition(final Class<? extends Instruction> implementationClass,
                final AssemblyStep... assemblySteps) {
            this.implementationClass = implementationClass;
            this.assemblySteps = assemblySteps;
        }
    }

    private static final Map<String, InstructionDefinition> INSTRUCTION_DEFINITIONS =
            new ImmutableMap.Builder<String, InstructionDefinition>()
                .put("dn", new InstructionDefinition(TurnDown.class, AssemblyStep.PARAMETER))
                .put("fd", new InstructionDefinition(MoveForward.class, AssemblyStep.PARAMETER))
                .put("forward", new InstructionDefinition(MoveForward.class, AssemblyStep.PARAMETER))
                .put("if", new InstructionDefinition(Conditional.class, AssemblyStep.PARAMETER, AssemblyStep.COMPARATOR,
                        AssemblyStep.PARAMETER, AssemblyStep.PROGRAM, AssemblyStep.OPTIONAL_ELSE, AssemblyStep.PROGRAM))
                .put("lt", new InstructionDefinition(TurnLeft.class, AssemblyStep.PARAMETER))
                .put("make", new InstructionDefinition(Make.class, AssemblyStep.OPTIONAL_LOCAL, AssemblyStep.NAME,
                        AssemblyStep.EQUALS, AssemblyStep.PARAMETER))
                .put("pd", new InstructionDefinition(PenDown.class))
                .put("pendown", new InstructionDefinition(PenDown.class))
                .put("penup", new InstructionDefinition(PenUp.class))
                .put("pu", new InstructionDefinition(PenUp.class))
                .put("repeat", new InstructionDefinition(Repeat.class, AssemblyStep.PARAMETER, AssemblyStep.PROGRAM))
                .put("rl", new InstructionDefinition(RollLeft.class, AssemblyStep.PARAMETER))
                .put("rollleft", new InstructionDefinition(RollLeft.class, AssemblyStep.PARAMETER))
                .put("rollright", new InstructionDefinition(RollRight.class, AssemblyStep.PARAMETER))
                .put("rr", new InstructionDefinition(RollRight.class, AssemblyStep.PARAMETER))
                .put("rt", new InstructionDefinition(TurnRight.class, AssemblyStep.PARAMETER))
                .put("sc", new InstructionDefinition(SetColor.class, AssemblyStep.PARAMETER))
                .put("setcolor", new InstructionDefinition(SetColor.class, AssemblyStep.PARAMETER))
                .put("stop", new InstructionDefinition(Stop.class))
                .put("turndown", new InstructionDefinition(TurnDown.class, AssemblyStep.PARAMETER))
                .put("turnleft", new InstructionDefinition(TurnLeft.class, AssemblyStep.PARAMETER))
                .put("turnright", new InstructionDefinition(TurnRight.class, AssemblyStep.PARAMETER))
                .put("turnup", new InstructionDefinition(TurnUp.class, AssemblyStep.PARAMETER))
                .put("up", new InstructionDefinition(TurnUp.class, AssemblyStep.PARAMETER))
                .build();

    private static final InstructionDefinition DEFAULT_INSTRUCTION_DEFINITION = new InstructionDefinition(
            RunProcedure.class, AssemblyStep.RUN_PROCEDURE);

    /**
     * Returns the steps necessary to assemble the instruction as identified by the
     * leading token.
     *
     * @param token The leading token of the instruction definition.
     * @return Steps which must be followed to assemble the identified instruction.
     */
    public AssemblyStep[] getAssemblySteps(final String token) {
        return INSTRUCTION_DEFINITIONS.getOrDefault(token.toLowerCase(), DEFAULT_INSTRUCTION_DEFINITION)
                .getAssemblySteps();
    }

    /**
     * Translates an instruction assembly into an instruction implementation.
     *
     * @param token The leading token of the instruction definition.
     * @param instructionAssembly The results of following the instruction's
     *        assembly steps.
     * @return The instruction implementation.
     * @throws InternalException If the instruction cannot be built.
     */
    public Instruction buildInstruction(final String token, final List<?> instructionAssembly)
            throws InternalException {

        Class<? extends Instruction> implementationClass = INSTRUCTION_DEFINITIONS
                .getOrDefault(token.toLowerCase(), DEFAULT_INSTRUCTION_DEFINITION).getImplementationClass();

        Class<?>[] argumentClasses = instructionAssembly.stream()
                .map(e -> e instanceof Parameter ? Parameter.class
                        : e instanceof Program ? Program.class : e instanceof List ? List.class : e.getClass())
                .toArray(Class[]::new);

        try {
            Constructor<? extends Instruction> constructor = implementationClass.getConstructor(argumentClasses);
            return constructor.newInstance(instructionAssembly.toArray(new Object[0]));

        } catch (Exception e) {
            throw new InternalException(
                    "Failed to build instruction: class=" + implementationClass + ", assembly=" + instructionAssembly,
                    e);
        }

    }

}
