package com.noelevans555.logo3d.model;

import lombok.Data;

/**
 * Represents a point in three dimensional space.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@Data
public class LogoPoint {

    private final double x;
    private final double y;
    private final double z;

    @Override
    public String toString() {
        return String.format("LogoPoint(x=%.2f, y=%.2f, z=%.2f)", x, y, z);
    }

}
