package edu.gatech.fido;

import android.hardware.*;
import android.location.Location;




public class Compass{

    private static Sensor magneticField;
    private static Sensor gravity;
    private static Sensor accelerometer;
    private static SensorEventListener magnetListener;
    private static SensorEventListener gravityListener;
    private static SensorEventListener accelerometerListener;

    private static double curDegree = 0;


    public Compass(SensorManager sensorManager){

        System.out.println("Compass has been created");


        magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        magnetListener = new SensorEventListener(){
            public void onAccuracyChanged(Sensor sensor, int accuracy){}
            public void onSensorChanged(SensorEvent event){
                if (event.sensor.getType() == Sensor.TYPE_ORIENTATION){

                       curDegree = event.values[0];
                       Location currentLoc = GPSManager.getDroneLocation();
                        if(currentLoc != null){
                        GeomagneticField geoField = new GeomagneticField(
                                (float) currentLoc.getLatitude(),
                                (float) currentLoc.getLongitude(),
                                (float) currentLoc.getAltitude(),
                                System.currentTimeMillis());
                            curDegree += geoField.getDeclination();

                    }
                }
            }
        };

        gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        gravityListener = new SensorEventListener(){
            public void onAccuracyChanged(Sensor sensor, int accuracy){}
            public void onSensorChanged(SensorEvent event){}
        };

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelerometerListener = new SensorEventListener(){
            public void onAccuracyChanged(Sensor sensor, int accuracy){}
            public void onSensorChanged(SensorEvent event){}
        };

        sensorManager.registerListener(magnetListener, magneticField, SensorManager.SENSOR_DELAY_NORMAL);
        System.out.println("Compass has been initialized");


    }

    public static double getCurDegree() {
        return curDegree;
    }

}
