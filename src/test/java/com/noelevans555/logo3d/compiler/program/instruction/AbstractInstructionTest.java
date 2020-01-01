package com.noelevans555.logo3d.compiler.program.instruction;

import org.mockito.Mock;

import com.noelevans555.logo3d.compiler.program.parameter.result.ColorResult;
import com.noelevans555.logo3d.compiler.program.parameter.result.NumericResult;
import com.noelevans555.logo3d.compiler.state.State;
import com.noelevans555.logo3d.compiler.turtle.Turtle;
import com.noelevans555.logo3d.model.LogoColor;

abstract class AbstractInstructionTest {

    static final double TEST_NUMERIC = 13.8d;
    static final NumericResult TEST_NUMERIC_RESULT = new NumericResult(TEST_NUMERIC);

    static final LogoColor TEST_COLOR = new LogoColor(50, 150, 250);
    static final ColorResult TEST_COLOR_RESULT = new ColorResult(TEST_COLOR);

    @Mock
    State state;

    @Mock
    Turtle turtle;

}
