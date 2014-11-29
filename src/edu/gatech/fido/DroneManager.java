package edu.gatech.fido;

import de.yadrone.base.ARDrone;

/**
 * Created by automation on 9/15/14.
 */
public class DroneManager {
    public static final int MAXINDOORHEIGHT = 1500;
    public static boolean isLanded = true;


    private static ARDrone drone;
    public static ARDrone getDrone(){
        return drone;
    }
    public static ARDrone initializeDrone(){
        drone = new ARDrone("192.168.1.1", null);
        return drone;
    }

    // This is used when we are on a flat surface to let the drone know that we are on a flat surface. Confusing na
    public static void preflightChecklist(){
       // drone.getCommandManager().emergency();
     //   drone.reset();
        drone.getCommandManager().flatTrim();
        drone.setMaxAltitude(MAXINDOORHEIGHT);
        drone.start();

    }

    public static void goTo(String s) {
    }

    public static void takeOff() {
        drone.getCommandManager().takeOff();
        drone.takeOff(); isLanded = false;
        System.out.println("Took off!");
    }

    public static void land() {
        drone.getCommandManager().landing(); isLanded = true;
    }

    public static void forward(int s) {
        drone.getCommandManager().forward(s);
    }

    public static void back(int s) {drone.getCommandManager().backward(s);
    }

    public static void left(int s) {drone.getCommandManager().goLeft(s);
    }

    public static void right(int s) {drone.getCommandManager().goRight(s);
    }

    public static void up(int s) {drone.getCommandManager().up(s);
    }

    public static void down(int s) {drone.getCommandManager().down(s);
    }

    public static void turnCounterClockwise(int s) {
        drone.getCommandManager().spinLeft(s);
        drone.spinLeft();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void turnClockwise(int s) {
        drone.getCommandManager().spinLeft(s);
        drone.spinRight();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void emergency() {
        drone.getCommandManager().emergency();
    }

    public static void reset() {
        drone.reset();
        preflightChecklist();
    }




}
