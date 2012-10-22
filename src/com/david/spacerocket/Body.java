package com.david.spacerocket;

import java.awt.Color;

/**
 * @author david
 */
public interface Body {

    public double getMass();
    public Color getColor();
    public Vector getVelocity();
    public Point getLocation();

}
