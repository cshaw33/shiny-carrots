package edu.gatech.fido;

import de.yadrone.base.ARDrone;

/**
 * Created by automation on 9/15/14.
 */
public class DroneManager {
    public static final int MAXINDOORHEIGHT = 1500;



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
        drone.reset();
        drone.getCommandManager().flatTrim();
        drone.setMaxAltitude(MAXINDOORHEIGHT);
        drone.start();
    }

    public static void goTo(String s) {
    }

    public static void takeOff() {
    }

    public static void land() {
    }

    public static void forward(String s) {
    }

    public static void back(String s) {
    }

    public static void left(String s) {
    }

    public static void right(String s) {
    }

    public static void up(String s) {
    }

    public static void down(String s) {
    }

    public static void turnCounterClockwise(String s) {
    }

    public static void turnClockwise(String s) {
    }

    public static void emergency() {
    }

    public static void reset() {
    }

    public static void trackRotation() {
    }


}
