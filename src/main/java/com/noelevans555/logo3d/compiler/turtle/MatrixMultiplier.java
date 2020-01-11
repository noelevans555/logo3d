package com.noelevans555.logo3d.compiler.turtle;

/**
 * Utility class for calculating the dot products of four-dimensional matrices
 * and vectors.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */
final class MatrixMultiplier {

    private static final int DIMENSION = 4;

    /**
     * Private constructor to prevent class instantiation.
     */
    private MatrixMultiplier() {
    }

    /**
     * Calculates the dot product of two four-dimensional matrices.
     *
     * @param matrixA Input matrix one.
     * @param matrixB Input matrix two.
     * @return The dot product of matrix one and two.
     */
    static double[][] multiply(final double[][] matrixA, final double[][] matrixB) {
        double[][] result = new double[DIMENSION][DIMENSION];
        for (int row = 0; row < DIMENSION; row++) {
            for (int column = 0; column < DIMENSION; column++) {
                double product = 0;
                for (int dimension = 0; dimension < DIMENSION; dimension++) {
                    product += matrixA[row][dimension] * matrixB[dimension][column];
                }
                result[row][column] = product;
            }
        }
        return result;
    }

    /**
     * Calculates the dot product of a four-dimensional matrix and a corresponding
     * vector.
     *
     * @param matrixA Input matrix.
     * @param vectorB Input vector.
     * @return The dot product of the matrix and vector.
     */
    static double[] multiply(final double[][] matrixA, final double[] vectorB) {
        double[] result = new double[DIMENSION];
        for (int row = 0; row < DIMENSION; row++) {
            double product = 0;
            for (int dimension = 0; dimension < DIMENSION; dimension++) {
                product += matrixA[row][dimension] * vectorB[dimension];
            }
            result[row] = product;
        }
        return result;
    }

}
