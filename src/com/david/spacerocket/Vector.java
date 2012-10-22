package com.david.spacerocket;

/**
 * @author david
 */
public class Vector {

    public double a;
    public double b;

    public Vector(){
    }

    public Vector(double d1, double d2){
        a = d1;
        b = d2;
    }
    public Vector(double theta, double mag, boolean angle){
        a = mag*Math.cos(theta);
        b = mag*Math.sin(theta);
    }
    public void addVector(Vector v){
        a = a+v.a;
        b = b+v.b;
    }
    public double dot(Vector v){
        return a*v.a + b*v.b;
    }
    /*
      * Projects this vector onto the input vector
      */
    public Vector proj(Vector v){
        double fact = this.dot(v)/v.dot(v);
        return new Vector(v.a*fact, v.b*fact);
    }
    public double getMag(){
        return Math.sqrt(a*a+b*b);
    }
    public void normalize(){
        double mag = this.getMag();
        a /= mag;
        b /= mag;
    }
    public void reverse(){
        a = -a;
        b = -b;
    }
    public void makeMag(double l){
        double mag = this.getMag();
        a *= (l/mag);
        b *= (l/mag);
    }
    public void mult(double n){
        a*=n;
        b*=n;
    }
    public String toString(){
        return "a: "+a+" b: "+b+" mag: "+this.getMag();
    }
}
