package com.dwetterau.spacerocket;

import com.dwetterau.generic.Pair;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author dwetterau
 */
public class Rocket implements Body {
    private double mass;
    private double fuel;
    private Color color;
    private Vector velocity;
    private double angularVelocity;
    private Point location;
    private ArrayList<Thruster> thrusters;


    public Rocket(double mass, double fuel, Color color, Vector velocity, double angularVelocity, Point location) {
        this.mass = mass;
        this.fuel = fuel;
        this.color = color;
        this.velocity = velocity;
        this.angularVelocity = angularVelocity;
        this.location = location;
        this.thrusters = new ArrayList<Thruster>();
    }

    @Override
    public double getMass() {
        return mass+fuel;
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
        //adjust velocity due to thrusters
        for (Thruster thruster : thrusters) {
            if (thruster.getState()) {
                Pair<Vector, Double> response = thruster.getForce(fuel, timeStep);
                fuel -= response.getSecond();

                Vector direction = thruster.getDirection();
                Vector forceDirection = new Vector(response.getFirst());
                Vector positionVector = new Vector(thruster.getLocation().x, thruster.getLocation().y);

                response.getFirst().mult(timeStep/getMass());
                velocity.addVector(response.getFirst());
            }
        }


        location.x = location.x + velocity.a*timeStep;
        location.y = location.y + velocity.b*timeStep;

    }

    public double getAngularVelocity() {
        return angularVelocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public ArrayList<Thruster> getThrusters() {
        return thrusters;
    }

    public void addThruster(Thruster thruster) {
        thrusters.add(thruster);
    }

    public String toString() {
        return ">[ m="+getMass()+" velocity="+ velocity + " loc="+location+" >";
    }
}
