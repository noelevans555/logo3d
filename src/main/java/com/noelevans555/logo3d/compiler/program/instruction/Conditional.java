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
 * Instruction to conditionally run a defined program based on activation
 * criteria defined in terms of two input parameters. The instruction supports
 * an optional alternative program to be run if the activation criteria is not
 * satisfied.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Conditional implements Instruction {

    private final Parameter leftParameter;
    private final String comparator;
    private final Parameter rightParameter;
    private final Program ifProgram;
    private final Program elseProgram;

    /**
     * Constructor for conditionals with no alternative program to run if the
     * activation criteria is not satisfied.
     *
     * @param leftParameter The left hand side of the activation criteria.
     * @param comparator The relationship between the left and right hand side
     *        criteria which must be satisfied for the 'if' program to be run.
     * @param rightParameter The right hand side of the activation criteria.
     * @param ifProgram The program to be run if the activation criteria is
     *        satisfied.
     */
    public Conditional(final Parameter leftParameter, final String comparator, final Parameter rightParameter,
            final Program ifProgram) {
        this.leftParameter = leftParameter;
        this.comparator = comparator;
        this.rightParameter = rightParameter;
        this.ifProgram = ifProgram;
        this.elseProgram = null;
    }

    @Override
    public void run(final State state, final Turtle turtle) throws StopException, CompilerException {
        double leftValue = leftParameter.evaluate(state).getNumeric("if");
        double rightValue = rightParameter.evaluate(state).getNumeric("if");

        Program programToRun;

        if (">".equals(comparator) && leftValue > rightValue) {
            programToRun = ifProgram;
        } else if ("<".equals(comparator) && leftValue < rightValue) {
            programToRun = ifProgram;
        } else {
            programToRun = elseProgram;
        }

        if (programToRun != null) {
            state.pushStack();
            try {
                programToRun.run(state, turtle);
            } finally {
                state.popStack();
            }
        }
    }

}
