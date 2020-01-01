package com.noelevans555.logo3d.compiler.program.instruction;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.exception.StopException;
import com.noelevans555.logo3d.compiler.program.Program;
import com.noelevans555.logo3d.compiler.program.parameter.Parameter;
import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.turtle.Turtle;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Runs the instruction's program body the number of times specified by the
 * instruction's parameter.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Repeat implements Instruction {

    private final Parameter countParameter;
    private final Program repeatBody;

    @Override
    public void run(final State state, final Turtle turtle) throws CompilerException {
        double repeatCount = countParameter.evaluate(state).getNumeric(getClass().getSimpleName());
        state.pushStack();
        try {
            try {
                for (int i = 0; i < Math.floor(repeatCount); i++) {
                    repeatBody.run(state, turtle);
                }
            } catch (StopException e) {
                // deliberately consumed = continue program execution after repeat is stopped.
            }
        } finally {
            state.popStack();
        }
    }

}
