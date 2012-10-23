package com.dwetterau.spacerocket.gui;

import com.dwetterau.spacerocket.*;
import com.dwetterau.spacerocket.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author dwetterau
 */
public class GalaxyViewer {

    public final static double TARGET_TIMESTEP = 1;

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

    public Point getViewpoint() {
        return viewpoint;
    }

    public void moveUp() {
        viewpoint.y += imageHeight/zoom/100;
    }

    public void moveDown() {
        viewpoint.y -= imageHeight/zoom/100;
    }

    public void moveLeft() {
        viewpoint.x -= imageWidth/zoom/100;
    }

    public void moveRight() {
        viewpoint.x += imageWidth/zoom/100;
    }

    public void zoomIn() {
        zoom *= 1.1;
    }

    public void zoomOut() {
        zoom *= .9;
    }

    public void update(double timeStep) {

        while ((timeStep-=TARGET_TIMESTEP) >= 0) {
            galaxy.updateVelocities(TARGET_TIMESTEP);
            galaxy.moveEverything(TARGET_TIMESTEP);
        }
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
        double dx = imageWidth/(2*zoom);
        double dy = imageHeight/(2*zoom);


        //terrible pruning
        double maxRad = Math.sqrt(dx*dx + dy*dy);
        for (Planet planet : galaxy.getPlanets()) {
            double effectiveRadius = planet.getRadius()*zoom;
            if (Point.distance(viewpoint, planet.getLocation()) < planet.getRadius() + maxRad) {
                //draw the planet
                g.setColor(planet.getColor());

                int xCenter = (int)((planet.getLocation().x-viewpoint.x)*zoom + imageWidth/2);
                int yCenter = imageHeight - (int)((planet.getLocation().y-viewpoint.y)*zoom + imageHeight/2);

                g.drawString("Planet",xCenter,yCenter);
                g.fillOval( (int)(xCenter - effectiveRadius),
                            (int)(yCenter - effectiveRadius),
                            (int)(2*effectiveRadius),
                            (int)(2*effectiveRadius));
            }
        }

        for (Rocket rocket : galaxy.getRockets()) {
            //drawing rockets is going to suck...
            int xCenter = (int)((rocket.getLocation().x-viewpoint.x)*zoom + imageWidth/2);
            int yCenter = (int)((rocket.getLocation().y-viewpoint.y)*zoom + imageHeight/2);

            Point center = new Point(xCenter, yCenter);

            double len = rocket.getLength();
            double theta = rocket.getRotation();
            Point A = new Point(-len/10, -len/2);
            A = A.rotate(theta).multiply(zoom).add(center);
            Point B = new Point(len/10, -len/2);
            B = B.rotate(theta).multiply(zoom).add(center);
            Point C = new Point(-len/10, 7*len/16);
            C = C.rotate(theta).multiply(zoom).add(center);
            Point D = new Point(len/10, 7*len/16);
            D = D.rotate(theta).multiply(zoom).add(center);
            Point E = new Point(0, len/2);
            E = E.rotate(theta).multiply(zoom).add(center);

            System.out.println(center);

            g.setColor(rocket.getColor());
            g.drawString("Rocket",xCenter,imageHeight - yCenter);
            //6 line rocket drawing
            g.drawLine(A.getX(), imageHeight - A.getY(), B.getX(), imageHeight - B.getY());
            g.drawLine(B.getX(), imageHeight - B.getY(), D.getX(), imageHeight - D.getY());
            g.drawLine(C.getX(), imageHeight - C.getY(), D.getX(), imageHeight - D.getY());
            g.drawLine(E.getX(), imageHeight - E.getY(), D.getX(), imageHeight - D.getY());
            g.drawLine(C.getX(), imageHeight - C.getY(), E.getX(), imageHeight - E.getY());
            g.drawLine(C.getX(), imageHeight - C.getY(), A.getX(), imageHeight - A.getY());

            int thrusterRadius = (int)(len/10 * zoom);

            for (Thruster t : rocket.getThrusters()) {
                Point p = new Point(t.getLocation());
                p = p.multiply(zoom).add(center);

                if (t.getState()) {
                    g.fillOval(p.getX()-thrusterRadius,
                        imageHeight - p.getY()-thrusterRadius,
                        2*thrusterRadius,
                        2*thrusterRadius);
                } else {
                    g.drawOval(p.getX()-thrusterRadius,
                        imageHeight - p.getY()-thrusterRadius,
                        2*thrusterRadius,
                        2*thrusterRadius);
                }
            }
        }

        g.dispose();
        return image;
    }
}