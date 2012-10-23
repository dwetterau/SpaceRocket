package com.dwetterau.spacerocket;

/**
 * @author dwetterau
 */
public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point (Point p) {
        this(p.x, p.y);
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x) + (p2.y-p1.y)*(p2.y-p1.y));
    }

    public String toString() {
        return "[x="+x+ " y="+y+"]";
    }

    public Point rotate(double radians) {
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        return new Point(x*cos - y*sin, x*sin + y*cos);
    }

    public Point add(Point p) {
        x+=p.x;
        y+=p.y;
        return this;
    }

    public Point multiply(double d) {
        x *= d;
        y *= d;
        return this;
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }
}
