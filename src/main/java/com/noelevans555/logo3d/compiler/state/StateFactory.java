package com.noelevans555.logo3d.compiler.state;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.noelevans555.logo3d.compiler.program.parameter.result.ColorResult;
import com.noelevans555.logo3d.compiler.program.parameter.result.EvaluationResult;
import com.noelevans555.logo3d.compiler.program.parameter.result.NumericResult;
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
     * Named evaluation results for pre-populating in newly created state objects.
     */
    @AllArgsConstructor
    @Getter
    private static class NamedEvaluationResult {
        private final String name;
        private final EvaluationResult result;
    }

    private static final double ZERO_SEGMENT = 0;
    private static final double HALF_SEGMENT = 128;
    private static final double FULL_SEGMENT = 255;

    private static final List<NamedEvaluationResult> PREDEFINED_RESULTS = ImmutableList.<NamedEvaluationResult>builder()
            .add(new NamedEvaluationResult("black",
                    new ColorResult(new LogoColor(ZERO_SEGMENT, ZERO_SEGMENT, ZERO_SEGMENT))))
            .add(new NamedEvaluationResult("gray",
                    new ColorResult(new LogoColor(HALF_SEGMENT, HALF_SEGMENT, HALF_SEGMENT))))
            .add(new NamedEvaluationResult("white",
                    new ColorResult(new LogoColor(FULL_SEGMENT, FULL_SEGMENT, FULL_SEGMENT))))
            .add(new NamedEvaluationResult("red",
                    new ColorResult(new LogoColor(FULL_SEGMENT, ZERO_SEGMENT, ZERO_SEGMENT))))
            .add(new NamedEvaluationResult("green",
                    new ColorResult(new LogoColor(ZERO_SEGMENT, FULL_SEGMENT, ZERO_SEGMENT))))
            .add(new NamedEvaluationResult("blue",
                    new ColorResult(new LogoColor(ZERO_SEGMENT, ZERO_SEGMENT, FULL_SEGMENT))))
            .add(new NamedEvaluationResult("yellow",
                    new ColorResult(new LogoColor(FULL_SEGMENT, FULL_SEGMENT, ZERO_SEGMENT))))
            .add(new NamedEvaluationResult("cyan",
                    new ColorResult(new LogoColor(ZERO_SEGMENT, FULL_SEGMENT, FULL_SEGMENT))))
            .add(new NamedEvaluationResult("magenta",
                    new ColorResult(new LogoColor(FULL_SEGMENT, ZERO_SEGMENT, FULL_SEGMENT))))
            .add(new NamedEvaluationResult("orange",
                    new ColorResult(new LogoColor(FULL_SEGMENT, HALF_SEGMENT, ZERO_SEGMENT))))
            .add(new NamedEvaluationResult("purple",
                    new ColorResult(new LogoColor(HALF_SEGMENT, ZERO_SEGMENT, FULL_SEGMENT))))
            .add(new NamedEvaluationResult("pi", new NumericResult(Math.PI))).build();

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
