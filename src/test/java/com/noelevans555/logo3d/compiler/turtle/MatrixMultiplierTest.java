package com.noelevans555.logo3d.compiler.turtle;

import org.junit.Test;

public class MatrixMultiplierTest {

    private static final double[][] TEST_MATRIX_A = new double[][] { new double[] { 0.5, 0.1, 0.3, 0.2 },
            new double[] { 0.1, -0.2, 0.8, -0.6 }, new double[] { 0.3, 0.4, -0.7, 0.4 },
            new double[] { 0.4, 0.1, 0.9, 0.5 } };

    private static final double[][] TEST_MATRIX_B = new double[][] { new double[] { -0.1, 0.5, 0.6, 0.8 },
            new double[] { 0.5, 0.9, 0.3, 0.3 }, new double[] { 0.3, -0.2, 0.1, -0.7 },
            new double[] { 0.7, 0.4, 0.7, 0.4 } };

    private static final double[][] EXPECTED_PRODUCT = new double[][] { new double[] { 0.23, 0.36, 0.5, 0.3 },
            new double[] { -0.29, -0.53, -0.34, -0.78 }, new double[] { 0.24, 0.81, 0.51, 1.01 },
            new double[] { 0.63, 0.31, 0.71, -0.08 } };

    private static final double[] TEST_VECTOR = new double[] { 0.2, -0.1, 1.3, 0.7 };

    private static final double[] EXPECTED_VECTOR = new double[] { 0.62, 0.66, -0.61, 1.59 };

    @Test
    public void multiply_withMatrices_returnsExpectedMatrix() {
        MatrixUtility.assertEqual(EXPECTED_PRODUCT, MatrixMultiplier.multiply(TEST_MATRIX_A, TEST_MATRIX_B));
    }

    @Test
    public void multiply_withMatrixAndVector_returnsExpectedMatrix() {
        MatrixUtility.assertEqual(EXPECTED_VECTOR, MatrixMultiplier.multiply(TEST_MATRIX_A, TEST_VECTOR));
    }

}
