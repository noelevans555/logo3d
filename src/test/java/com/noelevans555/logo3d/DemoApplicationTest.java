package com.noelevans555.logo3d;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.noelevans555.logo3d.compiler.exception.RuntimeLimitException;
import com.noelevans555.logo3d.model.LogoColor;
import com.noelevans555.logo3d.model.LogoLine;
import com.noelevans555.logo3d.model.LogoPoint;

public class DemoApplicationTest {

    private static final double DELTA = 1.0E-4;
    private static final LogoPoint ORIGIN = new LogoPoint(0, 0, 0);

    private DemoApplication demoApplication = new DemoApplication();

    @Test
    public void compileAndRunProgram_withGridSphere_returnsExpectedLogoLines() throws Exception {
        // compile & run
        List<LogoLine> logoLines = demoApplication.compileAndRunProgram(DemoPrograms.GRID_SPHERE);

        // analyze results
        double totalLineLength = 0;
        List<Double> distancesFromOrigin = new ArrayList<>();
        Set<LogoColor> colorsUsed = new HashSet<>();
        for (LogoLine logoLine : logoLines) {
            totalLineLength += getDistanceBetween(logoLine.getStart(), logoLine.getEnd());
            distancesFromOrigin.add(getDistanceBetween(ORIGIN, logoLine.getStart()));
            distancesFromOrigin.add(getDistanceBetween(ORIGIN, logoLine.getEnd()));
            colorsUsed.add(logoLine.getColor());
        }
        double averageDistanceFromOrigin = distancesFromOrigin.stream().mapToDouble(d -> d).average().getAsDouble();

        // assert
        assertEquals(10000, logoLines.size());
        assertEquals(100000, totalLineLength, DELTA);
        // program is non-deterministic so support variance in following checks.
        assertTrue(averageDistanceFromOrigin > 110 && averageDistanceFromOrigin < 120);
        assertTrue(colorsUsed.size() > 4);
    }

    @Test
    public void compileAndRunProgram_withRecursiveTree_returnsExpectedLogoLines() throws Exception {
        // compile & run
        List<LogoLine> logoLines = demoApplication.compileAndRunProgram(DemoPrograms.RECURSIVE_TREE);

        // analyze results
        double totalLineLength = 0;
        List<Double> distancesFromOrigin = new ArrayList<>();
        Set<LogoColor> colorsUsed = new HashSet<>();
        for (LogoLine logoLine : logoLines) {
            totalLineLength += getDistanceBetween(logoLine.getStart(), logoLine.getEnd());
            distancesFromOrigin.add(getDistanceBetween(ORIGIN, logoLine.getStart()));
            distancesFromOrigin.add(getDistanceBetween(ORIGIN, logoLine.getEnd()));
            colorsUsed.add(logoLine.getColor());
        }
        double averageDistanceFromOrigin = distancesFromOrigin.stream().mapToDouble(d -> d).average().getAsDouble();

        // assert
        assertEquals(8191, logoLines.size());
        assertEquals(37446.6636, totalLineLength, DELTA);
        assertEquals(113.22792, averageDistanceFromOrigin, DELTA);
        assertEquals(13, colorsUsed.size());
    }

    @Test(expected = RuntimeLimitException.class)
    public void compileAndRunProgram_withInfiniteRecursion_throwsLimitException() throws Exception {
        demoApplication.compileAndRunProgram("to proc [ proc ] proc");
    }

    private double getDistanceBetween(final LogoPoint start, final LogoPoint end) {
        return Math.sqrt(Math.pow(start.getX() - end.getX(), 2) + Math.pow(start.getY() - end.getY(), 2)
                + Math.pow(start.getZ() - end.getZ(), 2));
    }

}
