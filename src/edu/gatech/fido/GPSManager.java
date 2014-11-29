package edu.gatech.fido;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import edu.gatech.fido.Constants.GPS;
/**
 * Created by automation on 9/15/14.
 */
public class GPSManager
{

    private static Location dogLocation;
    static double desiredBearing;
    public static boolean gpsLoaded = false;
    public static Location getDroneLocation() {
        return droneLocation;
    }

    private static Location droneLocation;

    public static Location getDogLocation() {
        return dogLocation;
    }
    public static void Initialize(LocationManager locationManager)
    {
        System.out.println("GPS Initialization starting!");


        dogLocation = new Location("dogVest");
// Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {

// Called when a new location is found by the network location provider.
                makeUseOfNewLocation(location);
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {
                System.out.println("Status Change!");}
            public void onProviderEnabled(String provider) {
                System.out.println("Provider Enabled!");}
            public void onProviderDisabled(String provider) {
                System.out.println("Provider Disabled!");}
        };
// Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        System.out.println("GPS Initialized!");

    }
    public static void setDestination(String text)
    {
        String[] locationCordinates = text.split(",");
        dogLocation.setLatitude(Location.convert(locationCordinates[0]));
        dogLocation.setLongitude(Location.convert(locationCordinates[1]));
    }
    public static void makeUseOfNewLocation(Location dLocation)
    {
        System.out.println("New location!");
        droneLocation = dLocation;
        if (droneLocation.getAccuracy() < GPS.MIN_DISTANCE_PRECISION)
            if(true)
            {
                if(DroneManager.isLanded){
                    DroneManager.takeOff();
                    DroneManager.isLanded = false;
                }

                float distanceRemaining = (droneLocation.distanceTo(dogLocation));
                System.out.println(distanceRemaining);
                if (distanceRemaining < GPS.MIN_DISTANCE_CORRECTION )
                {
                    DroneManager.land();
                    System.out.println("arrived at Destination");
                } else {

                    gpsLoaded = true;

                }
            } else {
                System.out.println("Location not precise enough, waiting for better location");
            }
        System.out.println(droneLocation.toString());
    }
}
