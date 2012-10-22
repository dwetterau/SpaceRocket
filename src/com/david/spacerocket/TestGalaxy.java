package com.david.spacerocket;

import java.awt.*;

/**
 * @author david
 */
public class TestGalaxy {
    public static void main(String [] args) {
        Galaxy testGalaxy = new Galaxy();
        testGalaxy.getPlanets().add(new Planet(5.97219*Math.pow(10,24), Color.BLACK, new Vector(0,0), new Point(0,0)));
        testGalaxy.getPlanets().add(new Planet(Math.pow(10,10), Color.BLACK, new Vector(0,0), new Point(100,6378100)));
        testGalaxy.getRockets().add(new Rocket(70, Color.BLACK, new Vector(0,0), new Point(0,6378100)));

    }
}
