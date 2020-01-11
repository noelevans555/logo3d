package com.noelevans555.logo3d.compiler.turtle;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.noelevans555.logo3d.model.LogoPoint;

public class PoseTest {

    private static final LogoPoint TEST_POINT = new LogoPoint(128.0, 256.0, 512.0);
    private static final double[][] TEST_MATRIX = new double[][] {
        { 6.8, 4.5, 2.3, 4.1 },
        { 3.7, 6.3, 7.7, 2.0 },
        { 2.2, 9.8, 1.2, 9.3 },
        { 5.6, 1.4, 8.3, 1.5 }};

    private Pose pose = new Pose(TEST_POINT, TEST_MATRIX);

    @Test
    public void getLocation_returnsExpectedLocation() {
        assertEquals(TEST_POINT, pose.getLocation());
    }

    @Test
    public void getOrientation_returnsExpectedOrientation() {
        MatrixUtility.assertEqual(TEST_MATRIX, pose.getOrientation());
    }

}
