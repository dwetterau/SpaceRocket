package com.david.spacerocket;

import java.awt.Color;

/**
 * @author david
 */
public class Rocket implements Body {
    private double mass;
    private Color color;
    private Vector velocity;
    private Point location;

    public Rocket(double mass, Color color, Vector velocity, Point location) {
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

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String toString() {
        return ">[ m="+mass+" velocity="+ velocity + " loc="+location+" >";
    }
}
