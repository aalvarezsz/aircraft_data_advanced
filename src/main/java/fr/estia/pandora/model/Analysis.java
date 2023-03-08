package fr.estia.pandora.model;

import java.util.HashMap;
import java.util.Map;
import fr.estia.pandora.analyses.*;

/**
 * @author <@dhmmasson>
 * Generic Analysis, you should create specialized analysis
 * that extends this class for the different milestones
 */
public class Analysis {
	private Flight flight;
	private double flightDuration;
	//List of feature computed by this analysis
	//The Map associate the name of the feature with the textual representation to be printed
	private Map<String, String> featureValues ;

	/**
	 * Constructor, create a basic analysis 
	 * @param flight
	 */
	public Analysis(Flight flight, String targetFeature) {
		this.flight = flight;
		this.flightDuration = FlightDuration.compute(this.flight);
		
		this.featureValues = new HashMap<String, String> ();
		
		if(targetFeature.equals("null")) this.executeAll();
		else this.execute(targetFeature);
	}
	
	public void execute(String targetFeature) {
		switch(targetFeature) {
			case "flightDuration":
				this.featureValues.put( "flightDuration", String.format("%.2f", this.flightDuration));
				break;
			case "avgAltitude":
				this.featureValues.put( "avgAltitude", String.format("%.2f", Altitude.average(flight)));
				break;
			case "maxAltitude":
				this.featureValues.put( "maxAltitude", String.format("%.2f", Altitude.max(flight)));
				break;
			case "minAltitude":
				this.featureValues.put( "minAltitude", String.format("%.2f", Altitude.min(flight)));
				break;
			case "avgTemperature":
				this.featureValues.put( "avgTemperature", String.format("%.2f", Temperature.average(flight)));
				break;
			case "maxTemperature":
				this.featureValues.put( "maxTemperature", String.format("%.2f", Temperature.max(flight)));
				break;
			case "minTemperature":
				this.featureValues.put( "minTemperature", String.format("%.2f", Temperature.min(flight)));
				break;
			case "avgPressure":
				this.featureValues.put( "avgPressure", String.format("%.2f", Pressure.average(flight)));
				break;
			case "maxPressure":
				this.featureValues.put( "maxPressure", String.format("%.2f", Pressure.max(flight)));
				break;
			case "minPressure":
				this.featureValues.put( "minPressure", String.format("%.2f", Pressure.min(flight)));
				break;
			case "avgHumidity":
				this.featureValues.put( "avgHumidity", String.format("%.2f", Humidity.average(flight)));
				break;
			case "maxHumidity":
				this.featureValues.put( "maxHumidity", String.format("%.2f", Humidity.max(flight)));
				break;
			case "minHumidity":
				this.featureValues.put( "minHumidity", String.format("%.2f", Humidity.min(flight)));
				break;
			case "avgHeartrate":
				this.featureValues.put( "avgHeartrate", String.format("%.2f", Heartrate.average(flight)));
				break;
			case "maxHeartrate":
				this.featureValues.put( "maxHeartrate", String.format("%.2f", Heartrate.max(flight)));
				break;
			case "minHeartrate":
				this.featureValues.put( "minHeartrate", String.format("%.2f", Heartrate.min(flight)));
				break;
			case "avgOxygen":
				this.featureValues.put( "avgOxygen", String.format("%.2f", Oxygen.average(flight)));
				break;
			case "maxOxygen":
				this.featureValues.put( "maxOxygen", String.format("%.2f", Oxygen.max(flight)));
				break;
			case "minOxygen":
				this.featureValues.put( "minOxygen", String.format("%.2f", Oxygen.min(flight)));
				break;
			case "avgAirSpeed":
				this.featureValues.put( "minOxygen", String.format("%.2f", AirSpeed.average(flight)));
				break;
			case "maxAirSpeed":
				this.featureValues.put( "minOxygen", String.format("%.2f", AirSpeed.max(flight)));
				break;
			case "avgEnginePower":
				this.featureValues.put( "avgEnginePower", String.format("%.2f", EnginePower.average(flight)));
				break;
			case "maxEnginePower":
				this.featureValues.put( "maxEnginePower", String.format("%.2f", EnginePower.max(flight)));
				break;
		}
	}
	
	public void executeAll() {
		this.featureValues.put( "avgEnginePower", String.format("%.2f", EnginePower.average(flight)));
		this.featureValues.put( "flightDuration", String.format("%.2f", this.flightDuration));
		this.featureValues.put( "maxEnginePower", String.format("%.2f", EnginePower.max(flight)));
		this.featureValues.put( "avgAltitude", String.format("%.2f", Altitude.average(flight)));
		this.featureValues.put( "maxAltitude", String.format("%.2f", Altitude.max(flight)));
		this.featureValues.put( "minAltitude", String.format("%.2f", Altitude.min(flight)));
		this.featureValues.put( "avgTemperature", String.format("%.2f", Temperature.average(flight)));
		this.featureValues.put( "maxTemperature", String.format("%.2f", Temperature.max(flight)));
		this.featureValues.put( "minTemperature", String.format("%.2f", Temperature.min(flight)));
		this.featureValues.put( "avgPressure", String.format("%.2f", Pressure.average(flight)));
		this.featureValues.put( "maxPressure", String.format("%.2f", Pressure.max(flight)));
		this.featureValues.put( "minPressure", String.format("%.2f", Pressure.min(flight)));
		this.featureValues.put( "avgHumidity", String.format("%.2f", Humidity.average(flight)));
		this.featureValues.put( "maxHumidity", String.format("%.2f", Humidity.max(flight)));
		this.featureValues.put( "minHumidity", String.format("%.2f", Humidity.min(flight)));
		this.featureValues.put( "avgOxygen", String.format("%.2f", Oxygen.average(flight)));
		this.featureValues.put( "maxOxygen", String.format("%.2f", Oxygen.max(flight)));
		this.featureValues.put( "minOxygen", String.format("%.2f", Oxygen.min(flight)));
		this.featureValues.put( "avgHeartrate", String.format("%.2f", Heartrate.average(flight)));
		this.featureValues.put( "maxHeartrate", String.format("%.2f", Heartrate.max(flight)));
		this.featureValues.put( "minHeartrate", String.format("%.2f", Heartrate.min(flight)));
		this.featureValues.put( "avgAirSpeed", String.format("%.2f", AirSpeed.average(flight)));
		this.featureValues.put( "maxAirSpeed", String.format("%.2f", AirSpeed.max(flight)));
		
	}
	
	public String getFeatureValue( String feature ) {
		String value = "" ;
		if( featureValues.containsKey(feature)) {
			value = featureValues.get(feature) ;
		}
		return value ;
	}

	/**
	 * @return a string that represents all the feature of the analysis and their value
	 */
	@Override
	public String toString() {
		String description = "" ;
		for (String feature : featureValues.keySet() ) {
			description += feature + ": " + featureValues.get( feature ) + "\n";
		}
		return description;
	}
}
