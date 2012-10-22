package com.dwetterau.spacerocket;

/**
 * @author dwetterau
 */
public class TestVector {
    public static void main(String [] args) {
        Vector v1 = new Vector(10,0);
        Vector v2 = new Vector(0,10);

        System.out.println(v2.proj(v1));
        System.out.println(v2.rejection(v1));



    }
}
