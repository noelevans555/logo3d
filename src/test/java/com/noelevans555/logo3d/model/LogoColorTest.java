package com.noelevans555.logo3d.model;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

public class LogoColorTest {

    private static final double DELTA = 1.0E-9;

    @Test
    public void construct_withNegativeComponents_areSnappedToZero() {
        LogoColor logoColor = new LogoColor(-23.421, -10278.4242, -0.000001);
        assertEquals(0, logoColor.getRed(), DELTA);
        assertEquals(0, logoColor.getGreen(), DELTA);
        assertEquals(0, logoColor.getBlue(), DELTA);
    }

    @Test
    public void construct_withOversizedComponents_areSnappedToTwoFiveFive() {
        LogoColor logoColor = new LogoColor(321.731, 255.000001, 6278171.54);
        assertEquals(255, logoColor.getRed(), DELTA);
        assertEquals(255, logoColor.getGreen(), DELTA);
        assertEquals(255, logoColor.getBlue(), DELTA);
    }

    @Test
    public void construct_withInRangeComponents_areNotSnapped() {
        LogoColor logoColor = new LogoColor(254.99999, 73.4241, 0.0001);
        assertEquals(254.99999, logoColor.getRed(), DELTA);
        assertEquals(73.4241, logoColor.getGreen(), DELTA);
        assertEquals(0.0001, logoColor.getBlue(), DELTA);
    }

    @Test
    public void toAwtColor_returnsExpectedColor() {
        LogoColor logoColor = new LogoColor(-0.31, 65.22, 256.19);
        Color color = logoColor.toAwtColor();
        assertEquals(0, color.getRed());
        assertEquals(65, color.getGreen());
        assertEquals(255, color.getBlue());
    }

}
