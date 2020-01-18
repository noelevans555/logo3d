package com.noelevans555.logo3d.compiler.turtle;

import com.noelevans555.logo3d.compiler.RuntimeLimits;

/**
 * Builds a Logo3d turtle for tracking movements in three dimensional space.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */
public class TurtleFactory {

    /**
     * Builds a Logo3d turtle for tracking movements in three dimensional space.
     *
     * @param runtimeLimits Thresholds to be enforced during program execution.
     * @return A Logo3d turtle for tracking movements in three dimensional space.
     */
    public Turtle buildTurtle(final RuntimeLimits runtimeLimits) {
        Orientation orientation = new Orientation();
        return new Turtle(orientation, runtimeLimits.getMaximumLogoLines());
    }

}
