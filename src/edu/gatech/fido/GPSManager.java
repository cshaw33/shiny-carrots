package edu.gatech.fido;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import edu.gatech.fido.Constants.GPS;


public class GPSManager
{


    private static Location dogLocation;
    static double desiredBearing;
    public static Location getDroneLocation() {
        return droneLocation;
    }

    private static Location droneLocation;

    public static Location getDogLocation() {
        return dogLocation;
    }
    public static void Initialize(LocationManager locationManager)
    {

        dogLocation = new Location("dogVest");
// Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
// Called when a new location is found by the network location provider.
                System.out.println("Hello!");
                makeUseOfNewLocation(location);
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {
                System.out.println("asdf1");}
            public void onProviderEnabled(String provider) {
                System.out.println("asdf2");}
            public void onProviderDisabled(String provider) {
                System.out.println("asdf3");}
        };
// Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

    }
    public static void setDestination(String text)
    {
        String[] locationCordinates = text.split(",");
        dogLocation.setLatitude(Location.convert(locationCordinates[0]));
        dogLocation.setLongitude(Location.convert(locationCordinates[1]));
    }
    public static void makeUseOfNewLocation(Location dLocation)
    {
        droneLocation = dLocation;
        if (droneLocation.getAccuracy() < GPS.MIN_DISTANCE_PRECISION)
        {
            float distanceRemaining = (droneLocation.distanceTo(dogLocation));
            if (distanceRemaining < GPS.MIN_DISTANCE_CORRECTION || distanceRemaining < 2 * droneLocation.getAccuracy())
            {
                DroneManager.land();
                System.out.println("arrived at Destination");
            } else {
                if (droneLocation.hasBearing())
                {
                    Double curDegree = Compass.getCurDegree();
             //       double currentBearing = droneLocation.getBearing();
                    desiredBearing = droneLocation.bearingTo(dogLocation);
                    double desiredDegree = (curDegree - desiredBearing);

                    if(Math.abs(desiredDegree) > 180){
                        desiredDegree += desiredDegree > 0 ? -360 : 360;
                    }

                    if(desiredDegree < 0){
                        System.out.println("Turn right!");
                        DroneManager.turnClockwise(String.valueOf(Math.min(Math.abs(desiredDegree),100)));
                    }
                    else if(desiredDegree > 0)
                    {
                        System.out.println("Turn left!");
                        DroneManager.turnCounterClockwise(String.valueOf(Math.min(Math.abs(desiredDegree),100)));
                    }
               //     double bearingDifference = currentBearing - desiredBearing;
               //     if (Math.abs(bearingDifference) > GPS.MIN_BEARING_CORRECTION)
                 //   {
                  //  }
                }
            }
        } else {
            System.out.println("Location not precise enough, waiting for better location");
        }
        System.out.println("found location");
        System.out.println(droneLocation.toString());
    }
}
