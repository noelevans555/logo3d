package com.noelevans555.logo3d.compiler.turtle;

import java.util.ArrayList;
import java.util.List;

import com.noelevans555.logo3d.model.LogoColor;
import com.noelevans555.logo3d.model.LogoLine;
import com.noelevans555.logo3d.model.LogoPoint;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * Represents an entity which is manipulated within three dimensional space
 * while recording the positions of traversed lines.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class Turtle {

    private static final LogoPoint ORIGIN = new LogoPoint(0, 0, 0);
    private static final LogoColor WHITE = new LogoColor(255, 255, 255);

    private final Orientation orientation;

    private LogoPoint location = ORIGIN;
    private LogoColor color = WHITE;
    private boolean isDrawing = true;

    private final List<LogoLine> drawnLines = new ArrayList<>();

    /**
     * Retrieves all lines recorded by the turtle.
     *
     * @return Lines recorded by the turtle.
     */
    public List<LogoLine> getLogoLines() {
        return drawnLines;
    }

    /**
     * Returns a snapshot of the turtle's current pose.
     *
     * @return The turtle's current pose.
     */
    public Pose getPose() {
        return new Pose(location, orientation.getMatrix());
    }

    /**
     * Sets the turtle's pose to the value specified. The movement is not recorded
     * as a traversed line.
     *
     * @param pose Pose that the turtle is to adopt.
     */
    public void setPose(final Pose pose) {
        location = pose.getLocation();
        orientation.setMatrix(pose.getOrientation());
    }

    /**
     * Whether the turtle is to record lines during onward movements.
     *
     * @param drawLines Whether the turtle is to record lines during onward
     *        movements.
     */
    public void setDrawing(final boolean drawLines) {
        this.isDrawing = drawLines;
    }

    /**
     * The color in which the turtle is to record onward lines.
     *
     * @param color The color in which the turtle is to record onward lines.
     */
    public void setColor(final LogoColor color) {
        this.color = color;
    }

    /**
     * Instructs the turtle to turn left by the angle specified.
     *
     * @param angle The angle of the rotation, in degrees.
     */
    public void turnLeft(final double angle) {
        orientation.rotateZ(Math.toRadians(angle));
    }

    /**
     * Instructs the turtle to turn right by the angle specified.
     *
     * @param angle The angle of the rotation, in degrees.
     */
    public void turnRight(final double angle) {
        orientation.rotateZ(Math.toRadians(angle * -1));
    }

    /**
     * Instructs the turtle to roll left by the angle specified.
     *
     * @param angle The angle of the rotation, in degrees.
     */
    public void rollLeft(final double angle) {
        orientation.rotateY(Math.toRadians(angle * -1));
    }

    /**
     * Instructs the turtle to roll right by the angle specified.
     *
     * @param angle The angle of the rotation, in degrees.
     */
    public void rollRight(final double angle) {
        orientation.rotateY(Math.toRadians(angle));
    }

    /**
     * Instructs the turtle to turn up by the angle specified.
     *
     * @param angle The angle of the rotation, in degrees.
     */
    public void turnUp(final double angle) {
        orientation.rotateX(Math.toRadians(angle));
    }

    /**
     * Instructs the turtle to turn down by the angle specified.
     *
     * @param angle The angle of the rotation, in degrees.
     */
    public void turnDown(final double angle) {
        orientation.rotateX(Math.toRadians(angle * -1));
    }

    /**
     * Instructs the turtle to move forward by the specified amount.
     *
     * @param distance The distance to travel forward.
     */
    public void moveForward(final double distance) {
        double[] unitStep = orientation.getUnitStep();
        LogoPoint startLocation = location;
        location = new LogoPoint(
                startLocation.getX() + unitStep[0] * distance,
                startLocation.getY() + unitStep[1] * distance,
                startLocation.getZ() + unitStep[2] * distance);
        if (isDrawing) {
            drawnLines.add(new LogoLine(startLocation, location, color));
        }
    }

}
