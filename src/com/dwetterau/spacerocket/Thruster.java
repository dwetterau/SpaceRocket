package com.dwetterau.spacerocket;

import com.dwetterau.generic.Pair;

/**
 * @author dwetterau
 */
public class Thruster {

    private double fuelConsumption;
    private double thrust;
    private Vector direction;
    private boolean on;

    private Pair<Vector, Double> zeroResponse = new Pair<Vector, Double>(new Vector(0,0), 0.0);

    public Thruster(double thrust, double fuelConsumption, Vector direction) {
        direction.normalize();
        this.thrust = thrust;
        this.fuelConsumption = fuelConsumption;
        this.direction = direction;
        this.on = true;
    }

    public void turnOn() {
        this.on = true;
    }

    public void turnOff() {
        this.on = false;
    }

    public boolean getState() {
        return this.on;
    }

    public Pair<Vector, Double> getForce(double fuelAvailable, double timeStep) {
        if (fuelAvailable == 0) {
            return zeroResponse;
        }

        double fuelToUse = Math.min(fuelAvailable, timeStep*fuelConsumption);
        double mag = fuelToUse/fuelConsumption * thrust;

        Vector forceVec = new Vector(direction);
        forceVec.makeMag(mag);
        return new Pair<Vector, Double>(forceVec, fuelToUse);
    }


}
