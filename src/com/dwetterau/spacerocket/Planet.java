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
    private double radius;

    public Planet(double mass, Color color, Vector velocity, Point location, double radius) {
        this.mass = mass;
        this.color = color;
        this.velocity = velocity;
        this.location = location;
        this.radius = radius;
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

    public double getRadius() {
        return radius;
    }


    public void setLocation(Point location) {
        this.location = location;
    }

    public String toString() {
        return "( m="+mass+" velocity="+ velocity + " loc="+location+" )";
    }
}
