package com.noelevans555.logo3d.compiler.state;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.noelevans555.logo3d.compiler.program.parameter.result.ColorResult;
import com.noelevans555.logo3d.compiler.program.parameter.result.NumericResult;
import com.noelevans555.logo3d.compiler.program.parameter.result.Result;
import com.noelevans555.logo3d.model.LogoColor;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Builds a state object for tracking variable parameters during Logo3d program
 * execution. The object is pre-populated with common variable values, e.g.
 * standard colors and the constant pi.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

public class StateFactory {

    /**
     * Named results for pre-populating in newly created state objects.
     */
    @AllArgsConstructor
    @Getter
    private static class NamedResult {
        private final String name;
        private final Result result;
    }

    private static final double ZERO_SEGMENT = 0;
    private static final double HALF_SEGMENT = 128;
    private static final double FULL_SEGMENT = 255;

    private static final List<NamedResult> PREDEFINED_RESULTS = ImmutableList.<NamedResult>builder()
        .add(new NamedResult("black", new ColorResult(new LogoColor(ZERO_SEGMENT, ZERO_SEGMENT, ZERO_SEGMENT))))
        .add(new NamedResult("gray", new ColorResult(new LogoColor(HALF_SEGMENT, HALF_SEGMENT, HALF_SEGMENT))))
        .add(new NamedResult("white", new ColorResult(new LogoColor(FULL_SEGMENT, FULL_SEGMENT, FULL_SEGMENT))))
        .add(new NamedResult("red", new ColorResult(new LogoColor(FULL_SEGMENT, ZERO_SEGMENT, ZERO_SEGMENT))))
        .add(new NamedResult("green", new ColorResult(new LogoColor(ZERO_SEGMENT, FULL_SEGMENT, ZERO_SEGMENT))))
        .add(new NamedResult("blue", new ColorResult(new LogoColor(ZERO_SEGMENT, ZERO_SEGMENT, FULL_SEGMENT))))
        .add(new NamedResult("yellow", new ColorResult(new LogoColor(FULL_SEGMENT, FULL_SEGMENT, ZERO_SEGMENT))))
        .add(new NamedResult("cyan", new ColorResult(new LogoColor(ZERO_SEGMENT, FULL_SEGMENT, FULL_SEGMENT))))
        .add(new NamedResult("magenta", new ColorResult(new LogoColor(FULL_SEGMENT, ZERO_SEGMENT, FULL_SEGMENT))))
        .add(new NamedResult("orange", new ColorResult(new LogoColor(FULL_SEGMENT, HALF_SEGMENT, ZERO_SEGMENT))))
        .add(new NamedResult("purple", new ColorResult(new LogoColor(HALF_SEGMENT, ZERO_SEGMENT, FULL_SEGMENT))))
        .add(new NamedResult("pi", new NumericResult(Math.PI))).build();

    /**
     * Builds a state object for use during Logo3d compilation.
     *
     * @return A state object, pre-populated with common variable values, e.g.
     *         standard colors and the constant pi.
     */
    public State buildState() {
        State state = new State();
        PREDEFINED_RESULTS.stream().forEach(e -> state.store(e.getName(), e.getResult(), false));
        return state;
    }

}
