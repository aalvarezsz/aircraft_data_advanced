package fr.estia.pandora.model;

public class Position {
	private double latitude;
	private double longitude;
	private double altitude;
	
	// Constructors
	public Position () {}
	
	public Position(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = 0;
	}
	
	public Position(double latitude, double longitude, double altitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
	}
	
	// Methods
	public double getLatitude() { return latitude; }
	public void setLatitude(double latitude) { this.latitude = latitude; }
	
	public double getLongitude() { return longitude; }
	public void setLongitude(double longitude) { this.longitude = longitude; }
	
	public double getAltitude() { return altitude; }
	public void setAltitude(double altitude) { this.altitude = altitude; }
}
