package com.noelevans555.logo3d.compiler.turtle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Mock
    Orientation orientation;

    @InjectMocks
    private Turtle turtle;

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
        // given
        when(orientation.getUnitStep()).thenReturn(TEST_UNIT_STEP);
        // when
        turtle.setColor(EXPECTED_LINE_COLOR);
        turtle.moveForward(10);
        // then
        assertEquals(ImmutableList.of(EXPECTED_LINE), turtle.getLogoLines());
    }

    @Test
    public void moveForward_whenNotDrawing_drawsNoLine() {
        // given
        when(orientation.getUnitStep()).thenReturn(new double[] { 100.0, 200.0, 300.0 });
        // when
        turtle.setDrawing(false);
        turtle.moveForward(10);
        // then
        assertTrue(turtle.getLogoLines().isEmpty());
    }

}
