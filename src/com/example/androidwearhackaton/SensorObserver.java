package com.example.androidwearhackaton;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

//ROB - MODIFIED VERSION OF THE TUTORIAL LOCATED BELOW
//TUTORIAL LOCATED HERE:
//http://www.techrepublic.com/blog/software-engineer/a-quick-tutorial-on-coding-androids-accelerometer/


public class SensorObserver implements SensorEventListener{
	private boolean isSensing = false;
	private String sensorData = "";
	private String whichSensor = "";
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	
	private float mLastX;
	private float mLastY;
	private float mLastZ;
	
	private boolean mInitialized = false;
	private final float NOISE = (float)2.0;

	
	public void main(int sensorID){
		switch(sensorID){
			case 1:
				whichSensor = "Accelerometer";
				accelerometer();
				break;
		}
	}
	
	
	
	//PER TUTORIAL
	//THIS MAY NEED TO GET MOVED TO WearActivity.java TO AVOID BATTERY DRAIN
	/*
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}
	*/
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// can be safely ignored for this demo
	}
	//END POSSIBLE MOVE CODE
	
	
	
	private void accelerometer(){
		//UNCOMMENTING THIS LINE BREAKS COMPILATION
		//mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	
	
	//FROM TUTORIAL
	//@Override
	public void onSensorChanged(SensorEvent event) {
		//WE MAY WANT TO BAG THE ISSENSING AND USE THE LISTENER...
		//BUT FOR NOW LETS SET SENSING HERE
		isSensing = true;
		
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
		if (!mInitialized) {
			mLastX = x;
			mLastY = y;
			mLastZ = z;
			
			mInitialized = true;
		} else {
			float deltaX = Math.abs(mLastX - x);
			float deltaY = Math.abs(mLastY - y);
			float deltaZ = Math.abs(mLastZ - z);
			
			if (deltaX < NOISE)
				deltaX = (float) 0.0;
			if (deltaY < NOISE)
				deltaY = (float) 0.0;
			if (deltaZ < NOISE)
				deltaZ = (float) 0.0;
			mLastX = x;
			mLastY = y;
			mLastZ = z;
			
			
			//RETURN THIS JSON STRING
			/*
			tvX.setText(Float.toString(deltaX));
			tvY.setText(Float.toString(deltaY));
			tvZ.setText(Float.toString(deltaZ));
			*/
			
			/*
			//ROB
			//IF WE WANT TO GET FANCY WE CAN USE THIS TO DETECT ORIENTATION
			iv.setVisibility(View.VISIBLE);
			if (deltaX > deltaY) {
				//iv.setImageResource(R.drawable.horizontal);
			} else if (deltaY > deltaX) {
				//iv.setImageResource(R.drawable.vertical);
			} else {
				//iv.setVisibility(View.INVISIBLE);
			}
			*/
		}
	}

	
	//GETTER & SETTERS
	public boolean isSensing() {
		return isSensing;
	}
	public void setSensing(boolean isSensing) {
		this.isSensing = isSensing;
	}
	public String getSensorData() {
		return sensorData;
	}
	public void setSensorData(String sensorData) {
		this.sensorData = sensorData;
	}
	public String getWhichSensor() {
		return whichSensor;
	}
	public void setWhichSensor(String whichSensor) {
		this.whichSensor = whichSensor;
	}
}
