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
}
