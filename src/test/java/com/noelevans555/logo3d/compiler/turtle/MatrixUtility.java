package com.noelevans555.logo3d.compiler.turtle;

import static org.junit.Assert.assertEquals;

class MatrixUtility {

    private static final double DELTA = 1.0E-9;

    static void assertEqual(double[][] expected, double[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertEqual(expected[row], actual[row]);
        }
    }

    static void assertEqual(double[] expected, double[] actual) {
        assertEquals(expected.length, actual.length);
        for (int row = 0; row < expected.length; row++) {
            assertEquals(expected[row], actual[row], DELTA);
        }
    }

}
