package com.dwetterau.spacerocket;

import java.awt.Color;

/**
 * @author dwetterau
 */
public interface Body {

    public double getMass();
    public Color getColor();
    public Vector getVelocity();
    public Point getLocation();
    public void move(double timeStep);

}
