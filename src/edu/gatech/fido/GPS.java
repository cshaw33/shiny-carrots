package edu.gatech.fido;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
/**
 * Created by automation on 9/15/14.
 */
public class GPS
{
    public static void testLocation(LocationManager locationManager)
    {
        System.out.println("asdf");
        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                System.out.println("Hello");
                // Called when a new location is found by the network location provider.
                makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                System.out.println("asdf1");}

            public void onProviderEnabled(String provider) {
                System.out.println("asdf2");}

            public void onProviderDisabled(String provider) {
                System.out.println("asdf3");}
        };
        Criteria criteria = new Criteria();
        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        String provider = locationManager.getBestProvider(criteria, false);

        if(provider!=null && !provider.equals("")){

            // Get the location from the given provider
            Location location = locationManager.getLastKnownLocation(provider);

            locationManager.requestLocationUpdates(provider, 20000, 1,locationListener);

            if(location!=null)
                locationListener.onLocationChanged(location);
            else
                System.out.println("No location");
        }else{
            System.out.println("No provider");
        }
    }

    public static void makeUseOfNewLocation(Location location)
    {
        System.out.println("found location");
        System.out.println(location.toString());
    }
}
