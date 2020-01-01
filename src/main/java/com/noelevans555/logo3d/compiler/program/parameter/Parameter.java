package com.noelevans555.logo3d.compiler.program.parameter;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.program.parameter.result.EvaluationResult;
import com.noelevans555.logo3d.compiler.state.State;

/**
 * A parameter which can be evaluated in a Logo3d program and used as input to
 * an instruction.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

public interface Parameter {

    /**
     * Compute the value of the parameter given the specified state.
     *
     * @param state The state in which to compute the parameter value.
     * @return The value of the parameter in the specified state.
     * @throws CompilerException If the parameter cannot be evaluated.
     */
    EvaluationResult evaluate(State state) throws CompilerException;

}
