package com.noelevans555.logo3d.compiler.turtle;

import com.noelevans555.logo3d.model.LogoPoint;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * Represents a snapshot of a turtle's location and orientation.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@Getter(AccessLevel.PACKAGE)
public class Pose {

    private static final int DIMENSION = 4;

    private final LogoPoint location;
    private final double[][] orientation;

    /**
     * Constructor.
     *
     * @param location The snapshot location.
     * @param orientation The snapshot orientation.
     */
    public Pose(final LogoPoint location, final double[][] orientation) {
        this.location = location;
        this.orientation = clone(orientation);
    }

    /**
     * Creates a clone of the specified matrix, preventing consuming logic from
     * making changes to the original matrix.
     *
     * @param original The matrix to clone.
     * @return The cloned matrix.
     */
    private static double[][] clone(final double[][] original) {
        double[][] clone = new double[DIMENSION][DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            System.arraycopy(original[i], 0, clone[i], 0, DIMENSION);
        }
        return clone;
    }

}
