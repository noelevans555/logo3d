package com.noelevans555.logo3d.compiler.program.instruction;

import com.noelevans555.logo3d.compiler.exception.CompilerException;
import com.noelevans555.logo3d.compiler.exception.RuntimeLimitException;
import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.turtle.Turtle;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Confirms that the compilation timeout has not been exceeded.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@RequiredArgsConstructor
@EqualsAndHashCode(exclude = { "startTimeMillis" })
@ToString
public class TimeoutCheck implements Instruction {

    private static final int MILLIS_IN_SECOND = 1000;

    private final long startTimeMillis = System.currentTimeMillis();
    private final long timeoutInSeconds;

    @Override
    public void run(final State state, final Turtle turtle) throws CompilerException {
        if (System.currentTimeMillis() > startTimeMillis + timeoutInSeconds * MILLIS_IN_SECOND) {
            throw new RuntimeLimitException(RuntimeLimitException.TIMEOUT_EXCEEDED);
        }
    }

}
