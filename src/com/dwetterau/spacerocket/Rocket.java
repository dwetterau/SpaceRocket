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
    private double length;
    private Color color;
    private Vector velocity;
    private double angularVelocity;
    private Point location;
    private double rotation;
    private ArrayList<Thruster> thrusters;
    private static final double PI2 = Math.PI * 2;


    public Rocket(double mass, double fuel, double length, Color color, Vector velocity, double angularVelocity, Point location) {
        this.mass = mass;
        this.fuel = fuel;
        this.length = length;
        this.color = color;
        this.velocity = velocity;
        this.angularVelocity = angularVelocity;
        this.location = location;
        this.thrusters = new ArrayList<Thruster>();
        this.rotation = 0;
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

                Vector positionVector = new Vector(thruster.getLocation().x, thruster.getLocation().y);
                positionVector.normalize();

                Vector lateralForce = response.getFirst().proj(positionVector);
                Vector tangentialForce = response.getFirst().rejection(positionVector);

                lateralForce.mult(timeStep / getMass());
                velocity.addVector(lateralForce);

                double torque = tangentialForce.getMag()*Point.distance(new Point(0,0), thruster.getLocation());
                angularVelocity += timeStep*torque/getInertia();
            }
        }

        //use angular velocity to rotate every thrusters location && vector
        for (Thruster thruster : thrusters) {
            thruster.rotate(angularVelocity);
        }

        location.x = location.x + velocity.a*timeStep;
        location.y = location.y + velocity.b*timeStep;
        rotation = (rotation + angularVelocity) % PI2;

        //move thruster locations in accordance with new rotation... or change the vector? or both?

    }

    public double getInertia() {
        return (1/12.0) * getMass() * length * length;
    }

    public double getLength() {
        return length;
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
        return ">[ m="+getMass()+" velocity="+ velocity + " angular_vel="+ angularVelocity +" loc="+location+" >";
    }
}
