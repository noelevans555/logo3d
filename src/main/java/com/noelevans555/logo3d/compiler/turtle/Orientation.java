package com.noelevans555.logo3d.compiler.turtle;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the direction that the Logo3d turtle is currently facing in three
 * dimensional space.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
class Orientation {

    private static final double[] UNIT = new double[] {0d, 1d, 0d, 0d};

    private static final double[][] IDENTITY_MATRIX = new double[][] {
        new double[] {1d, 0d, 0d, 0d},
        new double[] {0d, 1d, 0d, 0d},
        new double[] {0d, 0d, 1d, 0d},
        new double[] {0d, 0d, 0d, 1d}};

    private double[][] matrix = IDENTITY_MATRIX;

    /**
     * Rotates the turtle's direction by the specified angle in the X axis.
     *
     * @param angle The angle of rotation, in radians.
     */
    void rotateX(final double angle) {
        matrix = MatrixMultiplier.multiply(matrix, new double[][] {
            new double[] {1, 0, 0, 0},
            new double[] {0, Math.cos(angle), Math.sin(angle) * -1, 0},
            new double[] {0, Math.sin(angle), Math.cos(angle), 0},
            new double[] {0, 0, 0, 1} });
    }

    /**
     * Rotates the turtle's direction by the specified angle in the Y axis.
     *
     * @param angle The angle of rotation, in radians.
     */
    void rotateY(final double angle) {
        matrix = MatrixMultiplier.multiply(matrix, new double[][] {
            new double[] {Math.cos(angle), 0, Math.sin(angle), 0},
            new double[] {0, 1, 0, 0},
            new double[] {Math.sin(angle) * -1, 0, Math.cos(angle), 0},
            new double[] {0, 0, 0, 1}});
    }

    /**
     * Rotates the turtle's direction by the specified angle in the Z axis.
     *
     * @param angle The angle of rotation, in radians.
     */
    void rotateZ(final double angle) {
        matrix = MatrixMultiplier.multiply(matrix, new double[][] {
            new double[] {Math.cos(angle), Math.sin(angle) * -1, 0, 0},
            new double[] {Math.sin(angle), Math.cos(angle), 0, 0},
            new double[] {0, 0, 1, 0},
            new double[] {0, 0, 0, 1}});
    }

    /**
     * Computes the unit step that the turtle should take if advanced along its current heading.
     *
     * @return Unit step that the turtle should take if advanced along its current heading
     */
    double[] getUnitStep() {
        double[] unitStep = MatrixMultiplier.multiply(matrix, UNIT);
        return new double[] {unitStep[0], unitStep[1], unitStep[2]};
    }

}
