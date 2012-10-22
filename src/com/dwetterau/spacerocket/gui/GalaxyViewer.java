package com.dwetterau.spacerocket.gui;

import com.dwetterau.spacerocket.Galaxy;
import com.dwetterau.spacerocket.Planet;
import com.dwetterau.spacerocket.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author dwetterau
 */
public class GalaxyViewer {

    private Point viewpoint;
    private double zoom; //imageHeight / displaying Height
    private Galaxy galaxy;
    private int imageWidth;
    private int imageHeight;

    public GalaxyViewer(Point viewpoint, int imageWidth, int imageHeight, Galaxy galaxy, double zoom) {
        this.viewpoint = viewpoint;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.galaxy = galaxy;
        this.zoom = zoom;
    }

    public void update(double timeStep) {
        galaxy.updateVelocities(timeStep);
        galaxy.moveEverything(timeStep);
    }


    /**
     * This is going to be really naive for now.
     * Will have to use some method of sectioning later to do this better
     */
    public BufferedImage getImage() {
        //initialized to black background which is fine for now.
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        if (galaxy == null) {
            return image;
        }
        //calculate viewing bounds
        double xOffset = (imageWidth>>1)/zoom;
        double yOffset = (imageHeight>>1)/zoom;


        //terrible pruning
        double maxRad = Math.sqrt(((imageWidth>>1)/zoom)*((imageWidth>>1)/zoom) + ((imageHeight>>1)/zoom)*((imageHeight>>1)/zoom));

        for (Planet planet : galaxy.getPlanets()) {
            double effectiveRadius = planet.getRadius()*zoom;
            //if (Point.distance(viewpoint, planet.getLocation()) < effectiveRadius + maxRad) {
                //draw the planet
                g.setColor(planet.getColor());
                System.out.println(planet.getLocation().x*zoom-effectiveRadius+xOffset-viewpoint.x);
                g.fillOval((int)(planet.getLocation().x*zoom-effectiveRadius+xOffset-viewpoint.x),
                        (int)(planet.getLocation().y*zoom-effectiveRadius+yOffset-viewpoint.y),
                        (int)(2*effectiveRadius),
                        (int)(2*effectiveRadius));
            //}
        }

        g.dispose();
        return image;
    }
}