package edu.gatech.fido;

import android.hardware.*;
import android.content.*;

/**
 * Created by automation on 9/15/14.
 */
public class Compass {
	
	public static float alt;
	public static float azimuth;//rotation around z axis (Degrees from North pole)
	public static float pitch;//rotation around y axis (degrees rotated NS)
	public static float roll;//rotation around x axis (degrees rotated EW)
	
	private static SensorManager sensorManager;
	private static SensorEvent sensorEvent;
	private static Sensor magneticField;
	private static Sensor gravity;
	private static Sensor accelerometer;
	private static SensorEventListener magnetListener;
	private static SensorEventListener gravityListener;
	private static SensorEventListener accelerometerListener;
	
	public void init(){
		
		sensorManager = (SensorManager) getSystemService("sensor");
		
		magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		magnetListener = new SensorEventListener(){
			public void onAccuracyChanged(Sensor sensor, int accuracy){}
			public void onSensorChanged(SensorEvent event){}
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
	}
	

	public static void findRotation(){
		
		float[] R = new float[9];
		float[] I = new float[9];
		
		float[] gravityArray; //this value should come from a sensor event from accelerometer
		float[] magneticArray; //this value should come from a sensor event from magnetic field sensor
		
		sensorManager.getRotationMatrix(R, I, gravityArray, magneticArray);
		
		float[] values = new float[3];
		sensorManager.getOrientation(R, values);
		azimuth = values[0];
		pitch = values[1];
		roll = values[2];
	}
	
}
