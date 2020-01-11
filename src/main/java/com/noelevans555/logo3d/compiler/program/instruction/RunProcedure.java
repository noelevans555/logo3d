package com.noelevans555.logo3d.compiler.program.instruction;

import java.util.List;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.exception.StopException;
import com.noelevans555.logo3d.compiler.program.Procedure;
import com.noelevans555.logo3d.compiler.program.parameter.Parameter;
import com.noelevans555.logo3d.compiler.program.parameter.result.EvaluationResult;
import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.turtle.Turtle;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Instruction to run the specified procedure's program body after defining new
 * locally-scoped variables. The variables are named by the procedure and
 * populated with the current evaluation results of the instruction's
 * parameters.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RunProcedure implements Instruction {

    private final List<Parameter> parameters;
    private final Procedure procedure;

    @Override
    public void run(final State state, final Turtle turtle) throws CompilerException {
        state.pushStack();
        try {
            for (int i = 0; i < parameters.size(); i++) {
                EvaluationResult parameterValue = parameters.get(i).evaluate(state);
                state.store(procedure.getParameterNames().get(i), parameterValue, true);
            }
            try {
                procedure.getProcedureBody().run(state, turtle);
            } catch (StopException e) {
                // deliberately consumed = continue program execution after procedure is
                // stopped.
            }
        } finally {
            state.popStack();
        }
    }

}
