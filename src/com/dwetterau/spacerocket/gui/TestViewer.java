package com.dwetterau.spacerocket.gui;

import com.dwetterau.spacerocket.Galaxy;
import com.dwetterau.spacerocket.Planet;
import com.dwetterau.spacerocket.Point;
import com.dwetterau.spacerocket.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * @author dwetterau
 */
public class TestViewer extends JFrame implements KeyListener {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private static int WIDTH2 = 1000;
    private static int HEIGHT2 = 800;
    private static int XD;
    private static int YD;

    private static int TARGET_DELAY = 17; //approximately 60 fps.

    private GalaxyViewer galaxyViewer;

    public TestViewer() {
        setSize(WIDTH2, HEIGHT2);
        setVisible(true);
        XD = (WIDTH - getContentPane().getWidth())/2;
        YD = (HEIGHT - getContentPane().getHeight() - XD);
        fixSize();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);




        Galaxy testGalaxy = new Galaxy();
        testGalaxy.getPlanets().add(new Planet(5.97219*Math.pow(10,24), Color.BLUE, new Vector(0,0), new Point(0,0), 6378100));
        testGalaxy.getPlanets().add(new Planet(7.34767309*Math.pow(10,22), Color.WHITE, new Vector(1000,0), new Point(0,385000000), 1737400));
        galaxyViewer = new GalaxyViewer(new Point(0,0), WIDTH, HEIGHT, testGalaxy, .0000004);
    }

    public void	fixSize(){
        setVisible(true);
        while(getContentPane().getWidth()<WIDTH){
            WIDTH2++;
            setSize(WIDTH2,HEIGHT2);
            setVisible(true);
        }
        while(getContentPane().getHeight()<HEIGHT){
            HEIGHT2++;
            setSize(WIDTH2,HEIGHT2);
            setVisible(true);
        }
        while(getContentPane().getWidth()>WIDTH){
            //			WIDTH2--;
            setSize(WIDTH2,HEIGHT2);
            setVisible(true);
        }
        while(getContentPane().getHeight()>HEIGHT){
            HEIGHT2--;
            setSize(WIDTH2,HEIGHT2);
            setVisible(true);
        }
    }

    public void paint(Graphics g) {
        if (galaxyViewer != null) {
            Image image = galaxyViewer.getImage();
            g.drawImage(image, XD, YD, this);
        }
    }
    public void update(Graphics g){
        paint(g);
    }

    public static void main(String [] args) {
        TestViewer testViewer = new TestViewer();
        long start = -System.currentTimeMillis();

        int times = 0;
        double fps;

        while(true) {
            long time = -System.currentTimeMillis();
            testViewer.paint(testViewer.getGraphics());
            long paintingTime = time+System.currentTimeMillis();
            //System.out.println("Painting time: "+paintingTime);
            time = -System.currentTimeMillis();
            testViewer.galaxyViewer.update(1000); //Go for 1000 seconds. Don't want to use the granularity though
            long updateTime = time + System.currentTimeMillis();
            //System.out.println("Update time: "+updateTime);
            times++;
            fps = (1.0/(((System.currentTimeMillis()+start)/1000.0)/times));
            //System.out.println("fps: "+fps);

            long diff = (TARGET_DELAY - paintingTime+updateTime);
            if (diff > 0) {
                try {
                    Thread.sleep(diff);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            //galaxyViewer.getViewpoint().y
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
