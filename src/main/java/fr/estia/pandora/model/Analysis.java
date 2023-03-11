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
	//List of feature computed by this analysis
	//The Map associate the name of the feature with the textual representation to be printed
	private Map<String, String> featureValues ;

	/**
	 * Constructor, create a basic analysis 
	 * @param flight
	 */
	public Analysis(Flight flight, String targetFeature) {
		this.flight = flight;
		this.featureValues = new HashMap<String, String> ();
		
		if(targetFeature.equals("null")) this.executeAll();
		else this.execute(targetFeature);
	}
	
	public void execute(String targetFeature) {
		switch(targetFeature) {
			case "avgAlt":
				this.featureValues.put( "avgAlt", String.format("%.2f", Altitude.average(flight)));
				break;
			case "maxAlt":
				this.featureValues.put( "maxAlt", String.format("%.2f", Altitude.max(flight)));
				break;
			case "avgTemp":
				this.featureValues.put( "avgTemp", String.format("%.2f", Temperature.average(flight)));
				break;
			case "maxTemp":
				this.featureValues.put( "maxTemp", String.format("%.2f", Temperature.max(flight)));
				break;
			case "minTemp":
				this.featureValues.put( "minTemp", String.format("%.2f", Temperature.min(flight)));
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
			case "avgHeartRate":
				this.featureValues.put( "avgHeartRate", String.format("%.2f", Heartrate.average(flight)));
				break;
			case "maxHeartRate":
				this.featureValues.put( "maxHeartRate", String.format("%.2f", Heartrate.max(flight)));
				break;
			case "minHeartRate":
				this.featureValues.put( "minHeartRate", String.format("%.2f", Heartrate.min(flight)));
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
				this.featureValues.put( "avgAirSpeed", String.format("%.2f", AirSpeed.average(flight)));
				break;
			case "maxAirSpeed":
				this.featureValues.put( "maxAirSpeed", String.format("%.2f", AirSpeed.max(flight)));
				break;
			case "avgEnginePower":
				this.featureValues.put( "avgEnginePower", String.format("%.2f", EnginePower.average(flight)));
				break;
			case "maxEnginePower":
				this.featureValues.put( "maxEnginePower", String.format("%.2f", EnginePower.max(flight)));
				break;
			case "avgAcceleration":
				this.featureValues.put( "avgAcceleration", String.format("%.2f", Acceleration.average(flight)));
				break;
			case "maxAcceleration":
				this.featureValues.put( "maxAcceleration", String.format("%.2f", Acceleration.max(flight)));
				break;
			case "maxAccelG":
				this.featureValues.put( "maxAccelG", String.format("%.2f", Acceleration.maxInG(flight)));
				break;
			case "avgMachSpeed":
				this.featureValues.put( "avgMachSpeed", String.format("%.2f", MachSpeed.average(flight)));
				break;
			case "maxMachSpeed":
				this.featureValues.put( "maxMachSpeed", String.format("%.2f", MachSpeed.max(flight)));
				break;
				
		}
	}
	
	public void executeAll() {
		this.featureValues.put( "avgEnginePower", String.format("%.2f", EnginePower.average(flight)));
		this.featureValues.put( "flightDistance", String.format("%.2f", FlightAnalysis.computeFullDistance(flight)));
		this.featureValues.put( "flightDuration", FlightAnalysis.getDuration(flight));
		this.featureValues.put( "maxEnginePower", String.format("%.2f", EnginePower.max(flight)));
		this.featureValues.put( "avgAlt", String.format("%.2f", Altitude.average(flight)));
		this.featureValues.put( "maxAlt", String.format("%.2f", Altitude.max(flight)));
		this.featureValues.put( "avgTemp", String.format("%.2f", Temperature.average(flight)));
		this.featureValues.put( "maxTemp", String.format("%.2f", Temperature.max(flight)));
		this.featureValues.put( "minTemp", String.format("%.2f", Temperature.min(flight)));
		this.featureValues.put( "avgPressure", String.format("%.2f", Pressure.average(flight)));
		this.featureValues.put( "maxPressure", String.format("%.2f", Pressure.max(flight)));
		this.featureValues.put( "minPressure", String.format("%.2f", Pressure.min(flight)));
		this.featureValues.put( "avgHumidity", String.format("%.2f", Humidity.average(flight)));
		this.featureValues.put( "maxHumidity", String.format("%.2f", Humidity.max(flight)));
		this.featureValues.put( "minHumidity", String.format("%.2f", Humidity.min(flight)));
		this.featureValues.put( "avgOxygen", String.format("%.2f", Oxygen.average(flight)));
		this.featureValues.put( "maxOxygen", String.format("%.2f", Oxygen.max(flight)));
		this.featureValues.put( "minOxygen", String.format("%.2f", Oxygen.min(flight)));
		this.featureValues.put( "avgHeartRate", String.format("%.2f", Heartrate.average(flight)));
		this.featureValues.put( "maxHeartRate", String.format("%.2f", Heartrate.max(flight)));
		this.featureValues.put( "minHeartRate", String.format("%.2f", Heartrate.min(flight)));
		this.featureValues.put( "avgAirSpeed", String.format("%.2f", AirSpeed.average(flight)));
		this.featureValues.put( "maxAirSpeed", String.format("%.2f", AirSpeed.max(flight)));
		this.featureValues.put( "avgAcceleration", String.format("%.2f", Acceleration.average(flight)));
		this.featureValues.put( "maxAcceleration", String.format("%.2f", Acceleration.max(flight)));
		this.featureValues.put( "maxAccelG", String.format("%.2f", Acceleration.maxInG(flight)));
		this.featureValues.put( "avgMachSpeed", String.format("%.2f", MachSpeed.average(flight)));
		this.featureValues.put( "maxMachSpeed", String.format("%.2f", MachSpeed.max(flight)));
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
