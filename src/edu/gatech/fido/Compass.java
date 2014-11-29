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


                            if(!DroneManager.isLanded && GPSManager.gpsLoaded){
                                Double curDegree = Compass.getCurDegree();
                                //       double currentBearing = droneLocation.getBearing();
                                double desiredBearing = GPSManager.getDroneLocation().bearingTo(GPSManager.getDogLocation());
                                double desiredDegree = (curDegree - desiredBearing);
                                //    System.out.println(desiredDegree);
                                if(Math.abs(desiredDegree) > 180){
                                    desiredDegree += desiredDegree > 0 ? -360 : 360;
                                }

                                if(desiredDegree < 0){
                                    //   System.out.println("Turn right! " + String.valueOf(Math.min(Math.abs((int)desiredDegree), 50)));
                                    try {
                                        DroneManager.turnClockwise(Math.min(Math.abs((int) desiredDegree)/2, 50));
                                        //           Thread.sleep(50);
                                        //         DroneManager.forward(1);
                                    }
                                    catch (Exception e){

                                    }
                                }
                                else if(desiredDegree > 0)
                                {
                                    //  System.out.println("Turn left! " + String.valueOf(Math.min(Math.abs(desiredDegree), 50)));
                                    try{
                                        DroneManager.turnCounterClockwise(Math.min(Math.abs((int) desiredDegree)/2, 50));
                                      //  Thread.sleep(50);
                                      //  DroneManager.forward(1);
                                    }
                                    catch (Exception e){

                                    }
                                }
                                else
                                {
                                    System.out.println("Degree is correct! (hopefully)");
                                }
                            }
                            else
                            {
                                try{
                                    DroneManager.land();
                                }
                                catch (Exception e){
                                    System.out.println("Landing caused an exception!");
                                }
                            }
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
