package fr.estia.pandora.model;

import java.util.HashMap;
import java.util.Map;

public class Metadata {
	private Map<String, String> sourceMetadata;
	
	// Flight data
	private String flightId = "";
	private String flightCode = "";
	private String flightRegion = "";
	private String flightDate = "";
	private String flightOrigin = "";
	private String flightDestination = "";

	// Plane data
	private int engineAmount = 1;
	private float aircraftMass = 0;
	private float fuelMass = 0;
	private double liftCoef = 0;
	private double dragCoef = 0;

	// Constructor
	public Metadata() {
		sourceMetadata = new HashMap<String, String>() ;
	}
	
	public void setMetaData( String key, String sourceValue ) {
		sourceMetadata.put(key,	sourceValue);

		switch(key) {
			case "constructor" :
			case "origin" : flightRegion = sourceValue; break;
			case "flight id" : flightId = sourceValue; break;
			case "motor(s)" : engineAmount = Integer.parseInt(sourceValue); break;
			case "mass aircraft" : aircraftMass = (float) (flightRegion.equals("US") ? Double.parseDouble(sourceValue) / 2.205 : Double.parseDouble(sourceValue)); break;
			case "mass fuel" : fuelMass = (float) (flightRegion.equals("US") ? Double.parseDouble(sourceValue) / 2.205 : Double.parseDouble(sourceValue)); break;
			default: break;
		}
	}
	
	public void print() {
		System.out.println("========== METADATA ==========");
		for(Map.Entry<String, String> metadata: sourceMetadata.entrySet()) {
			System.out.println(metadata.getKey() + ": " + metadata.getValue());
		}
		System.out.println("==============================");
	}
	
	public String getFlightId() { return this.flightId; }
	public String getFlightRegion() { return flightRegion ; }
	public String getFlightDate() { return flightDate; }
	public String getFlightOrigin() { return flightOrigin; }
	public String getFlightDestination() { return flightDestination; }
	public int getEngineAmount() { return engineAmount ; }
	public float getAircraftMass() { return aircraftMass; }
	public float getFuelMass() { return fuelMass; }
	public double getLiftCoef() { return liftCoef; }
	public double getDragCoef() { return dragCoef; }
}
