package com.noelevans555.logo3d.compiler.turtle;

/**
 * Builds a Logo3d turtle for tracking movements in three dimensional space.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */
public class TurtleFactory {

    /**
     * Builds a Logo3d turtle for tracking movements in three dimensional space.
     *
     * @return A Logo3d turtle for tracking movements in three dimensional space.
     */
    public Turtle buildTurtle() {
        Orientation orientation = new Orientation();
        return new Turtle(orientation);
    }

}
