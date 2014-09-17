package edu.gatech.fido.App.Tester;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;

/**
 * Created by automation on 9/15/14.
 */
public class MainMenuActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        System.out.println(("\nConnected to " + wifi.getConnectionInfo().getSSID()));

        initialize();
    }

    private void initialize()
    {

        FIDOTestApp app = (FIDOTestApp)getApplication();

    }



}


