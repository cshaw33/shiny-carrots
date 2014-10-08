package edu.gatech.fido.App.Tester;

import edu.gatech.fido.GPS;
import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by automation on 9/15/14.
 */
public class GPSActivity extends Activity {

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        GPS.Initialize((LocationManager) this.getSystemService(Context.LOCATION_SERVICE));
        initialize();
    }

    private void initialize()
    {
        FIDOTestApp app = (FIDOTestApp)getApplication();
    }
    
    public LocationManager GetLocationManager()
    {
    	return (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    }
}


