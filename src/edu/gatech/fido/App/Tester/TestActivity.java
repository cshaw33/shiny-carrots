package edu.gatech.fido.App.Tester;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import edu.gatech.fido.GPS;
import edu.gatech.fido.SMSManager;

/**
 * Created by automation on 9/15/14.
 */
public class TestActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GPS.testLocation((LocationManager) this.getSystemService(Context.LOCATION_SERVICE));
        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        System.out.println(("\nConnected to " + wifi.getConnectionInfo().getSSID()));
        SMSManager sms = new SMSManager();

        try {
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialize() throws Exception {

        FIDOTestApp app = (FIDOTestApp)getApplication();

        System.out.println("Starting SMS Test!");
        testSMS();

    }

    private void testSMS()
    {
        String phoneNumber = "5554";
        String message = "Drone takeoff";




        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }


}


