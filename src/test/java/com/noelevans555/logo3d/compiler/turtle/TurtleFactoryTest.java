package com.noelevans555.logo3d.compiler.turtle;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.noelevans555.logo3d.compiler.RuntimeLimits;

public class TurtleFactoryTest {

    private TurtleFactory turtleFactory = new TurtleFactory();

    @Test
    public void buildTurtle_returnsFunctionalTurtle() throws Exception {
        Turtle turtle = turtleFactory.buildTurtle(new RuntimeLimits(1, 100, 100));
        turtle.moveForward(1.0);
        assertEquals(1, turtle.getLogoLines().size());
    }

}
