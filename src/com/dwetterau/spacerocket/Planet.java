package com.dwetterau.spacerocket;

import java.awt.Color;

/**
 * @author dwetterau
 */
public class Planet implements Body {

    private double mass;
    private Color color;
    private Vector velocity;
    private Point location;

    public Planet(double mass, Color color, Vector velocity, Point location) {
        this.mass = mass;
        this.color = color;
        this.velocity = velocity;
        this.location = location;
    }

    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Vector getVelocity() {
        return velocity;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public void move(double timeStep) {
        location.x = location.x + velocity.a*timeStep;
        location.y = location.y + velocity.b*timeStep;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }


    public void setLocation(Point location) {
        this.location = location;
    }

    public String toString() {
        return "( m="+mass+" velocity="+ velocity + " loc="+location+" )";
    }
}
