package edu.gatech.fido.App.Tester;

import android.hardware.*;
import android.content.*;
import android.app.Activity;
//import org.openintents.sensorsimulator.hardware.Sensor;
//import org.openintents.sensorsimulator.hardware.SensorEvent;
//import org.openintents.sensorsimulator.hardware.SensorEventListener;
//import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;


/**
 * Created by automation on 9/15/14.
 */
public class Compass extends Activity{
	
	public static float alt;
	public static float azimuth;//rotation around z axis (Degrees from North pole)
	public static float pitch;//rotation around y axis (degrees rotated NS)
	public static float roll;//rotation around x axis (degrees rotated EW)
	
	private static SensorManager sensorManager;
	//private static SensorManagerSimulator sensorManagerSim;
	private static SensorEvent sensorEvent;
	private static Sensor magneticField;
	private static Sensor gravity;
	private static Sensor accelerometer;
	private static SensorEventListener magnetListener;
	private static SensorEventListener gravityListener;
	private static SensorEventListener accelerometerListener;
	
	
	public Compass(SensorManager sensorManager){
		
		System.out.println("Compass has been created");
		
		this.sensorManager = sensorManager;
		
		magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		magnetListener = new SensorEventListener(){
			public void onAccuracyChanged(Sensor sensor, int accuracy){}
			public void onSensorChanged(SensorEvent event){
				if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
					
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
		System.out.println("Things have been done, compass has been initialized.");
		
		this.findRotation();
		
	}
	

	public static void findRotation(){
		
		float[] R = new float[9];
		float[] I = new float[9];
		
		float[] gravityArray = {0, 0, 0}; //this value should come from a sensor event from accelerometer
		float[] magneticArray = {0, 0, 0}; //this value should come from a sensor event from magnetic field sensor
		
		
		
		sensorManager.getRotationMatrix(R, I, gravityArray, magneticArray);
		
		float[] values = new float[3];
		sensorManager.getOrientation(R, values);
		azimuth = values[0];
		pitch = values[1];
		roll = values[2];
		System.out.println("Az = "+azimuth+" pitch = "+pitch+" roll = "+roll);
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		sensorManager.registerListener(accelerometerListener, accelerometer, sensorManager.SENSOR_DELAY_FASTEST);
		sensorManager.registerListener(gravityListener, gravity, sensorManager.SENSOR_DELAY_FASTEST);
		sensorManager.registerListener(magnetListener, magneticField, sensorManager.SENSOR_DELAY_FASTEST);
		
	}
	
	@Override
	protected void onStop() {
		sensorManager.unregisterListener(accelerometerListener);
		sensorManager.unregisterListener(gravityListener);
		sensorManager.unregisterListener(magnetListener);
		super.onStop();
	}
	
	
	protected void onPause(){
		super.onPause();
		sensorManager.unregisterListener(accelerometerListener);
		sensorManager.unregisterListener(gravityListener);
		sensorManager.unregisterListener(magnetListener);
	}
	
	
}
