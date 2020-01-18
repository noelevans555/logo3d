package com.noelevans555.logo3d.compiler.state;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.noelevans555.logo3d.compiler.RuntimeLimits;
import com.noelevans555.logo3d.compiler.program.parameter.result.ColorResult;
import com.noelevans555.logo3d.compiler.program.parameter.result.NumericResult;
import com.noelevans555.logo3d.model.LogoColor;

public class StateFactoryTest {

    private StateFactory stateFactory = new StateFactory();

    @Test
    public void buildState_returnsStateWithPredefinedResults() throws Exception {
        State state = stateFactory.buildState(new RuntimeLimits(1, 100, 100));
        assertEquals(new NumericResult(Math.PI), state.retrieveResult("PI"));
        assertEquals(new ColorResult(new LogoColor(0, 255, 255)), state.retrieveResult("Cyan"));
    }

}
