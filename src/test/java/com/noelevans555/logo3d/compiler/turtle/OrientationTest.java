package com.noelevans555.logo3d.compiler.turtle;

import org.junit.Test;

public class OrientationTest {

    private static final double ANGLE = Math.toRadians(90);

    private static final double[] EXPECTED_STEP = new double[] { 0, 0, 1 };

    private Orientation orientation = new Orientation();

    @Test
    public void getUnitStep_whenRotatedInXThenY_returnsExpectedStep() {
        orientation.rotateX(ANGLE);
        orientation.rotateY(ANGLE);
        MatrixUtility.assertEqual(EXPECTED_STEP, orientation.getUnitStep());
    }

    @Test
    public void getUnitStep_whenRotatedInYThenZ_returnsExpectedStep() {
        orientation.rotateY(ANGLE);
        orientation.rotateZ(ANGLE);
        MatrixUtility.assertEqual(EXPECTED_STEP, orientation.getUnitStep());
    }

    @Test
    public void getUnitStep_whenRotatedInZThenX_returnsExpectedStep() {
        orientation.rotateZ(ANGLE);
        orientation.rotateX(ANGLE);
        MatrixUtility.assertEqual(EXPECTED_STEP, orientation.getUnitStep());
    }

}
