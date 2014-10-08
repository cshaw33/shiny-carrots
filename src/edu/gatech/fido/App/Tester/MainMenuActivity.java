package edu.gatech.fido.App.Tester;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import edu.gatech.fido.Compass;
import android.hardware.*;

import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;
import org.openintents.sensorsimulator.hardware.SensorEventListener;
import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;

/**
 * Created by automation on 9/15/14.
 */
public class MainMenuActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        System.out.println(("\nConnected to " + wifi.getConnectionInfo().getSSID()));

        initialize();
        
        
        //Intent intent = new Intent(this, Compass);
       // startActivity(intent);
    }

    private void initialize()
    {

        FIDOTestApp app = (FIDOTestApp)getApplication();
        
        //Compass compass = new Compass((SensorManager)getSystemService(SENSOR_SERVICE));
        
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        SensorManagerSimulator smsim = SensorManagerSimulator.getSystemService(this, SENSOR_SERVICE);
        
        smsim.connectSimulator();
        
        Compass compass = new Compass(sm);
        //Compass compassSim = new Compass(smsim);
        
    }



}


