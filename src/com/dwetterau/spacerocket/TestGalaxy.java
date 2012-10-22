package com.dwetterau.spacerocket;

import java.awt.*;

/**
 * @author dwetterau
 */
public class TestGalaxy {
    public static void main(String [] args) {
        Galaxy testGalaxy = new Galaxy();
        testGalaxy.getPlanets().add(new Planet(5.97219*Math.pow(10,24), Color.BLACK, new Vector(0,0), new Point(0,0)));
        testGalaxy.getRockets().add(new Rocket(731000, 2100000, Color.BLACK, new Vector(0,0), new Point(0,6378100)));
        testGalaxy.getRockets().get(0).addThruster(new Thruster(34020000,14000,new Vector(0,1)));

    }
}
