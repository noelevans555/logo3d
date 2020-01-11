package com.noelevans555.logo3d.compiler.turtle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;
import com.noelevans555.logo3d.model.LogoColor;
import com.noelevans555.logo3d.model.LogoLine;
import com.noelevans555.logo3d.model.LogoPoint;

@RunWith(MockitoJUnitRunner.class)
public class TurtleTest {

    private static final double TEST_ANGLE_DEGREES = 45;
    private static final double EXPECTED_ANGLE_RADIANS = 0.7853981633974483;

    private static final double[] TEST_UNIT_STEP = new double[] { 100.0, 200.0, 300.0 };

    private static final LogoPoint EXPECTED_LINE_START = new LogoPoint(0, 0, 0);
    private static final LogoPoint EXPECTED_LINE_END = new LogoPoint(1000, 2000, 3000);
    private static final LogoColor EXPECTED_LINE_COLOR = new LogoColor(128, 128, 128);
    private static final LogoLine EXPECTED_LINE = new LogoLine(EXPECTED_LINE_START, EXPECTED_LINE_END,
            EXPECTED_LINE_COLOR);

    private static final double[][] TEST_MATRIX = new double[][] {
        { 5.2, 4.7, 3.1, 4.4 },
        { 1.5, 5.6, 8.8, 7.2 },
        { 5.2, 1.2, 0.0, 9.5 },
        { 7.8, 4.4, 0.9, 3.4 }};

    @Mock
    private Orientation orientation;

    @InjectMocks
    private Turtle turtle;

    @Before
    public void setup() {
        when(orientation.getUnitStep()).thenReturn(TEST_UNIT_STEP);
        when(orientation.getMatrix()).thenReturn(TEST_MATRIX);
    }

    @Test
    public void turnLeft_rotatesOrientationByExpectedValue() {
        turtle.turnLeft(TEST_ANGLE_DEGREES);
        verify(orientation).rotateZ(EXPECTED_ANGLE_RADIANS);
    }

    @Test
    public void turnRight_rotatesOrientationByExpectedValue() {
        turtle.turnRight(TEST_ANGLE_DEGREES);
        verify(orientation).rotateZ(EXPECTED_ANGLE_RADIANS * -1);
    }

    @Test
    public void rollLeft_rotatesOrientationByExpectedValue() {
        turtle.rollLeft(TEST_ANGLE_DEGREES);
        verify(orientation).rotateY(EXPECTED_ANGLE_RADIANS * -1);
    }

    @Test
    public void rollRight_rotatesOrientationByExpectedValue() {
        turtle.rollRight(TEST_ANGLE_DEGREES);
        verify(orientation).rotateY(EXPECTED_ANGLE_RADIANS);
    }

    @Test
    public void turnUp_rotatesOrientationByExpectedValue() {
        turtle.turnUp(TEST_ANGLE_DEGREES);
        verify(orientation).rotateX(EXPECTED_ANGLE_RADIANS);
    }

    @Test
    public void turnDown_rotatesOrientationByExpectedValue() {
        turtle.turnDown(TEST_ANGLE_DEGREES);
        verify(orientation).rotateX(EXPECTED_ANGLE_RADIANS * -1);
    }

    @Test
    public void moveForward_whenDrawingWithColor_drawsLineWithExpectedColor() {
        turtle.setColor(EXPECTED_LINE_COLOR);
        turtle.moveForward(10);
        assertEquals(ImmutableList.of(EXPECTED_LINE), turtle.getLogoLines());
    }

    @Test
    public void moveForward_whenNotDrawing_drawsNoLine() {
        turtle.setDrawing(false);
        turtle.moveForward(10);
        assertTrue(turtle.getLogoLines().isEmpty());
    }

    @Test
    public void getPose_afterMoving_returnsExpectedPose() {
        turtle.moveForward(10);
        Pose outputPose = turtle.getPose();
        assertEquals(EXPECTED_LINE_END, outputPose.getLocation());
        MatrixUtility.assertEqual(TEST_MATRIX, outputPose.getOrientation());
    }

    @Test
    public void setPose_updatesOrientationAndDrawsLineFromExpectedLocation() {
        Pose pose = new Pose(EXPECTED_LINE_END, TEST_MATRIX);
        turtle.setPose(pose);
        ArgumentCaptor<?> matrixCaptor = ArgumentCaptor.forClass(Object.class);
        verify(orientation).setMatrix((double[][]) matrixCaptor.capture());
        MatrixUtility.assertEqual(TEST_MATRIX, (double[][]) matrixCaptor.getValue());
        // confirm changed in location was applied
        turtle.moveForward(10);
        assertEquals(EXPECTED_LINE_END, turtle.getLogoLines().get(0).getStart());
    }

}
