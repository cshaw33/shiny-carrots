package edu.gatech.fido;

import java.util.Arrays;
import java.util.List;

import edu.gatech.fido.Constants.GPS;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
/**
 * Created by automation on 9/15/14.
 */
public class GPSManager
{
	private static Location dogLocation;
	public static void Initialize(LocationManager locationManager)
	{
		dogLocation = new Location("dogVest");
		// Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {
		    public void onLocationChanged(Location location) {
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
		  // Register the listener with the Location Manager to receive location updates
		  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
	}

	public static void setDestination(String text)
	{
		String[] locationCordinates = text.split(",");
		dogLocation.setLatitude(Location.convert(locationCordinates[0]));
		dogLocation.setLongitude(Location.convert(locationCordinates[1]));
	}
	public static void makeUseOfNewLocation(Location droneLocation)
	{
		if (droneLocation.getAccuracy() < GPS.MIN_DISTANCE_PRECISION)
		{
			float distanceRemaining = (droneLocation.distanceTo(dogLocation));
			if (distanceRemaining < GPS.MIN_DISTANCE_CORRECTION || distanceRemaining < 2 * droneLocation.getAccuracy())
			{
				// TODO stop drone
				//DroneManager.Land();
				System.out.println("arrived at Destination");
			} else {
				if (droneLocation.hasBearing())
				{
					double currentBearing = droneLocation.getBearing();
					double desiredBearing = droneLocation.bearingTo(dogLocation);
					double bearingDifference = currentBearing - desiredBearing;
					if (Math.abs(bearingDifference) > GPS.MIN_BEARING_CORRECTION)
					{
						// TODO tell drone to correct bearing, and start drone if not started already
					}
				}
			}
		} else {

			System.out.println("Location not precise enough, waiting for better location");
		}
		System.out.println("found location");
		System.out.println(droneLocation.toString());
	}
}
