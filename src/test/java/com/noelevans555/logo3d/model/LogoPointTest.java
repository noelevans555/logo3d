package com.noelevans555.logo3d.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LogoPointTest {

    private LogoPoint logoPoint = new LogoPoint(1012.5552455, 731.83245225, -61.217322423);

    @Test
    public void toString_printsValuesToTwoDecimalPlaces() {
        assertEquals("LogoPoint(x=1012.56, y=731.83, z=-61.22)", logoPoint.toString());
    }

}
