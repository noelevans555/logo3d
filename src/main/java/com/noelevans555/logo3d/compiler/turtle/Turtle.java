package com.noelevans555.logo3d.compiler.turtle;

import com.noelevans555.logo3d.model.LogoColor;

import lombok.Data;

/**
 * Represents an entity which is manipulated within three dimensional space
 * while recording the positions of traversed lines.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@Data
public class Turtle {

    /**
     * Whether the turtle is to record lines during onward movements.
     *
     * @param isDrawing Whether the turtle is to record lines during onward
     *        movements.
     */
    public void setDrawing(final boolean isDrawing) {
        // TODO
    }

    /**
     * The color in which the turtle is to record onward lines.
     *
     * @param logoColor The color in which the turtle is to record onward lines.
     */
    public void setColor(final LogoColor logoColor) {
        // TODO
    }

    /**
     * Instructs the turtle to turn left by the angle specified.
     *
     * @param angle The angle of the rotation.
     */
    public void turnLeft(final double angle) {
        // TODO
    }

    /**
     * Instructs the turtle to turn right by the angle specified.
     *
     * @param angle The angle of the rotation.
     */
    public void turnRight(final double angle) {
        // TODO
    }

    /**
     * Instructs the turtle to roll left by the angle specified.
     *
     * @param angle The angle of the rotation.
     */
    public void rollLeft(final double angle) {
        // TODO
    }

    /**
     * Instructs the turtle to roll right by the angle specified.
     *
     * @param angle The angle of the rotation.
     */
    public void rollRight(final double angle) {
        // TODO
    }

    /**
     * Instructs the turtle to turn up by the angle specified.
     *
     * @param angle The angle of the rotation.
     */
    public void turnUp(final double angle) {
        // TODO
    }

    /**
     * Instructs the turtle to turn down by the angle specified.
     *
     * @param angle The angle of the rotation.
     */
    public void turnDown(final double angle) {
        // TODO
    }

    /**
     * Instructs the turtle to move forward by the specified amount.
     *
     * @param distance The distance to travel forward.
     */
    public void moveForward(final double distance) {
        // TODO
    }

}
