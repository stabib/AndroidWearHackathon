package com.example.androidwearhackaton;

public class SensorObserver {
	private boolean isSensing = false;
	private String sensorData = "";
	private String whichSensor = "";
	
	
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
