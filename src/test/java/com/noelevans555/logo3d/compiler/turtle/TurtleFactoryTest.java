package com.noelevans555.logo3d.compiler.turtle;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TurtleFactoryTest {

    private TurtleFactory turtleFactory = new TurtleFactory();

    @Test
    public void buildTurtle_returnsFunctionalTurtle() {
        Turtle turtle = turtleFactory.buildTurtle();
        turtle.moveForward(1.0);
        assertEquals(1, turtle.getLogoLines().size());
    }

}
