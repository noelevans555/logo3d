package com.noelevans555.logo3d.model;

import lombok.Data;

/**
 * Represents a line in three dimensional space.
 *
 * @author Noel Evans (noelevans555@gmail.com)
 */

@Data
public class LogoLine {

    private final LogoPoint start;
    private final LogoPoint end;
    private final LogoColor color;

}
