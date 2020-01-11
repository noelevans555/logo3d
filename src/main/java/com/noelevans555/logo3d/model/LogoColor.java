package com.noelevans555.logo3d.model;

import java.awt.Color;

import lombok.Data;

/**
 * Represents a color with each RGB component expressed as a double in the range
 * 0 to 255.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@Data
public class LogoColor {

    public static final int MAX_COMPONENT_VALUE = 255;

    private final double red;
    private final double green;
    private final double blue;

    /**
     * Constructor.
     *
     * @param red Strength of the color's red component, snapped to the range 0 to
     *        255 (inclusive).
     * @param green Strength of the color's green component, snapped to the range 0
     *        to 255 (inclusive).
     * @param blue Strength of the blue green component, snapped to the range 0 to
     *        255 (inclusive).
     */
    public LogoColor(final double red, final double green, final double blue) {
        this.red = Math.min(MAX_COMPONENT_VALUE, Math.max(0, red));
        this.green = Math.min(MAX_COMPONENT_VALUE, Math.max(0, green));
        this.blue = Math.min(MAX_COMPONENT_VALUE, Math.max(0, blue));
    }

    /**
     * Returns the java.awt.Color corresponding to this color.
     *
     * @return The corresponding java.awt.Color.
     */
    public Color toAwtColor() {
        return new Color(
                (float) (red / MAX_COMPONENT_VALUE),
                (float) (green / MAX_COMPONENT_VALUE),
                (float) (blue / MAX_COMPONENT_VALUE));
    }

}
