package com.dwetterau.spacerocket;

import java.util.ArrayList;

/**
 * @author dwetterau
 */
public class Galaxy {

    public static double G = .00000000006673;

    ArrayList<Planet> planets;
    ArrayList<Rocket> rockets;

    public Galaxy() {
        planets = new ArrayList<Planet>();
        rockets = new ArrayList<Rocket>();
    }

    public ArrayList<Planet> getPlanets() {
        return planets;
    }

    public ArrayList<Rocket>  getRockets() {
        return rockets;
    }

    public void moveEverything(double timeStep) {
        for (Rocket rocket : rockets) {
            rocket.move(timeStep);
        }
        for (Planet planet : planets) {
            planet.move(timeStep);
        }
    }

    public void updateVelocities(double timeStep) {
        //for each planet, update every other planet and rockets velocity
        //rockets shouldn't need to update planet's velocity although this could be added...
        Point p1;
        Point p2;
        for (int p = 0; p < planets.size(); p++) {
            p1 = planets.get(p).getLocation();
            for (Rocket rocket : rockets) {
                p2 = rocket.getLocation();
                Vector gravityForce = new Vector(p1.x-p2.x, p1.y-p2.y);
                double mag = gravityForce.getMag();
                mag = timeStep * (G * planets.get(p).getMass()) / (mag * mag);
                gravityForce.makeMag(mag);
                rocket.getVelocity().addVector(gravityForce);
            }
            for (int j = 0; j < planets.size(); j++) {
                if (j==p) continue;
                p2 = planets.get(j).getLocation();
                Vector gravityForce = new Vector(p1.x-p2.x, p1.y-p2.y);
                double mag = gravityForce.getMag();
                mag = timeStep * (G * planets.get(p).getMass()) / (mag * mag);
                gravityForce.makeMag(mag);
                planets.get(j).getVelocity().addVector(gravityForce);
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Planets: \n");
        for (Planet planet : planets) {
            stringBuilder.append(planet);
            stringBuilder.append("\n");
        }
        stringBuilder.append("Rockets: \n");
        for (Rocket rocket : rockets) {
            stringBuilder.append(rocket);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
