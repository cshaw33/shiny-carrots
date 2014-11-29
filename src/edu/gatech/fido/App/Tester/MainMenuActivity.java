package edu.gatech.fido.App.Tester;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import edu.gatech.fido.Compass;
import edu.gatech.fido.DroneManager;
import edu.gatech.fido.GPSManager;
import edu.gatech.fido.SMSManager;


public class MainMenuActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        System.out.println(("\nConnected to " + wifi.getConnectionInfo().getSSID()));
        //initialize();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        Compass compass = new Compass(sm);
        //  compass.enableDebugMode();
        SMSManager sms = new SMSManager();
        DroneManager.initializeDrone();
        DroneManager.preflightChecklist();

        GPSManager.Initialize((LocationManager) this.getSystemService(Context.LOCATION_SERVICE));
        GPSManager.setDestination("90.0000,0.00000");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
