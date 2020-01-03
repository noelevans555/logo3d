package com.noelevans555.logo3d.compiler.program;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.exception.InternalException;
import com.noelevans555.logo3d.compiler.exception.SyntaxException;
import com.noelevans555.logo3d.compiler.program.instruction.Instruction;
import com.noelevans555.logo3d.compiler.program.instruction.InstructionFactory;
import com.noelevans555.logo3d.compiler.program.instruction.InstructionFactory.AssemblyStep;
import com.noelevans555.logo3d.compiler.program.parameter.Parameter;
import com.noelevans555.logo3d.compiler.program.parameter.ParameterFactory;
import com.noelevans555.logo3d.compiler.program.tokens.TokenReader;
import com.noelevans555.logo3d.compiler.program.tokens.Vocabulary;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Constructs the Logo3d Abstract Syntax Tree (AST) from program tokens.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
public class ProgramFactory {

    private static final String OPERATION = "BuildProgram";

    private final InstructionFactory instructionFactory;
    private final ParameterFactory parameterFactory;

    /**
     * Consumes program tokens to construct the Logo3d Abstract Syntax Tree (AST).
     *
     * @param tokenReader Source of tokens to read.
     * @return The constructed Logo3d AST.
     * @throws CompilerException If the AST cannot be constructed.
     */
    public Program buildProgram(final TokenReader tokenReader) throws CompilerException {
        ProgramBuilder programBuilder = new ProgramBuilder(tokenReader);
        return programBuilder.buildProgram(0);
    }

    /**
     * Inner builder, instantiated for each request for convenient variable
     * management.
     */
    @RequiredArgsConstructor
    private class ProgramBuilder {

        private final TokenReader tokenReader;
        private final ProcedureRegistry procedureRegistry = new ProcedureRegistry();

        /**
         * Consumes program tokens to construct the Logo3d Abstract Syntax Tree (AST).
         *
         * @param depth The level of nesting at which the subprogram being built is
         *        defined within the overall Logo3d program.
         * @return The constructed Logo3d AST.
         * @throws CompilerException If the AST cannot be constructed.
         */
        private Program buildProgram(final int depth) throws CompilerException {
            List<Instruction> instructions = new ArrayList<>();
            while (tokenReader.hasMoreTokens()) {
                if (tokenReader.readOptionalToken(Vocabulary.BOX_CLOSE)) {
                    if (depth == 0) {
                        throw new SyntaxException(SyntaxException.UNEXPECTED_TOKEN, OPERATION,
                                tokenReader.getRecentlyReadTokens());
                    }
                    break;
                }
                if (tokenReader.readOptionalToken(Vocabulary.TO)) {
                    defineProcedure(depth);
                } else {
                    instructions.add(buildInstruction(depth));
                }
            }
            return new Program(instructions);
        }

        /**
         * Constructs a single instruction within a Logo3d program.
         *
         * @param depth The level of nesting at which the instruction's enclosing
         *        program is defined.
         * @return A constructed instruction.
         * @throws CompilerException If the instruction cannot be constructed.
         */
        private Instruction buildInstruction(final int depth) throws CompilerException {
            String token = tokenReader.readToken();
            AssemblyStep[] assemblySteps = instructionFactory.getAssemblySteps(token);
            List<?> instructionAssembly = getInstructionAssembly(assemblySteps, token, depth);
            return instructionFactory.buildInstruction(token, instructionAssembly);
        }

        /**
         * Follows the specified steps to assemble the program elements required to
         * build a single Logo3d instruction.
         *
         * @param assemblySteps Assembly steps to follow.
         * @param token The leading token of the instruction being assembled.
         * @param depth The level of nesting at which the instruction's enclosing
         *        program is defined.
         * @return Program elements required to build the instruction.
         * @throws CompilerException If the program elements cannot be assembled.
         */
        private List<?> getInstructionAssembly(final AssemblyStep[] assemblySteps, final String token, final int depth)
                throws CompilerException {

            List<Object> instructionAssembly = new ArrayList<>();

            for (AssemblyStep assemblyStep : assemblySteps) {
                switch (assemblyStep) {
                case PARAMETER:
                    instructionAssembly.add(parameterFactory.buildParameter(tokenReader));
                    break;
                case COMPARATOR:
                    instructionAssembly.add(tokenReader.readExpectedToken(Vocabulary.COMPARATORS, token));
                    break;
                case NAME:
                    instructionAssembly.add(tokenReader.readValidName(token));
                    break;
                case EQUALS:
                    tokenReader.readExpectedToken(Vocabulary.EQUALS, token);
                    break;
                case PROGRAM:
                    tokenReader.readExpectedToken(Vocabulary.BOX_OPEN, token);
                    instructionAssembly.add(buildProgram(depth + 1));
                    break;
                case OPTIONAL_ELSE:
                    if (!tokenReader.readOptionalToken(Vocabulary.ELSE)) {
                        return instructionAssembly;
                    }
                    break;
                case OPTIONAL_LOCAL:
                    instructionAssembly.add(tokenReader.readOptionalToken(Vocabulary.LOCAL));
                    break;
                case RUN_PROCEDURE:
                    Optional<Procedure> procedure = procedureRegistry.getProcedure(token);
                    if (procedure.isEmpty()) {
                        throw new SyntaxException(SyntaxException.UNRECOGNIZED_INSTRUCTION, OPERATION,
                                tokenReader.getRecentlyReadTokens());
                    }
                    List<Parameter> parameters = new ArrayList<>();
                    for (int i = 0; i < procedure.get().getParameterNames().size(); i++) {
                        parameters.add(parameterFactory.buildParameter(tokenReader));
                    }
                    instructionAssembly.add(parameters);
                    instructionAssembly.add(procedure.get());
                    break;
                default:
                    throw new InternalException("Unrecognized assembly step: " + assemblyStep);
                }
            }
            return instructionAssembly;
        }

        /**
         * Builds and registers a Logo3d procedure which is then available to be invoked
         * in onward Logo3d instructions.
         *
         * @param depth The level of nesting at which the procedure is defined.
         * @throws CompilerException If the procedure cannot be built or registered.
         */
        private void defineProcedure(final int depth) throws CompilerException {
            String procedureName = tokenReader.readValidName(Vocabulary.TO);
            List<String> parameterNames = new ArrayList<>();
            while (!tokenReader.readOptionalToken(Vocabulary.BOX_OPEN)) {
                parameterNames.add(tokenReader.readValidName(Vocabulary.TO));
            }
            procedureRegistry.registerProcedure(procedureName, parameterNames);
            procedureRegistry.linkProcedureBody(procedureName, buildProgram(depth + 1));
        }

    }

}
