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
	private Map<String, String> featureValues;

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
			case "avgAlt": this.featureValues.put( "avgAlt", String.format("%.2f", Altitude.average(flight))); break;
			case "avgAcceleration": this.featureValues.put( "avgAcceleration", String.format("%.2f", Acceleration.average(flight))); break;
			case "avgAccelerationCruise": this.featureValues.put( "avgAccelerationCruise", String.format("%.2f", PhaseAnalysis.avgAccelerationCruise(flight))); break;
			case "avgAccelerationLanding": this.featureValues.put( "avgAccelerationLanding", String.format("%.2f", PhaseAnalysis.avgAccelerationLanding(flight))); break;
			case "avgAccelerationTakeOff": this.featureValues.put( "avgAccelerationTakeOff", String.format("%.2f", PhaseAnalysis.avgAccelerationTakeOff(flight))); break;
			case "maxAlt": this.featureValues.put( "maxAlt", String.format("%.2f", Altitude.max(flight))); break;
			case "avgTemp": this.featureValues.put( "avgTemp", String.format("%.2f", Temperature.average(flight))); break;
			case "maxTemp": this.featureValues.put( "maxTemp", String.format("%.2f", Temperature.max(flight))); break;
			case "minTemp": this.featureValues.put( "minTemp", String.format("%.2f", Temperature.min(flight))); break;
			case "avgPressure": this.featureValues.put( "avgPressure", String.format("%.2f", Pressure.average(flight))); break;
			case "maxPressure": this.featureValues.put( "maxPressure", String.format("%.2f", Pressure.max(flight))); break;
			case "minPressure": this.featureValues.put( "minPressure", String.format("%.2f", Pressure.min(flight))); break;
			case "avgHumidity": this.featureValues.put( "avgHumidity", String.format("%.2f", Humidity.average(flight))); break;
			case "maxHumidity": this.featureValues.put( "maxHumidity", String.format("%.2f", Humidity.max(flight))); break;
			case "minHumidity": this.featureValues.put( "minHumidity", String.format("%.2f", Humidity.min(flight))); break;
			case "avgHeartRate": this.featureValues.put( "avgHeartRate", String.format("%.2f", Heartrate.average(flight))); break;
			case "maxHeartRate": this.featureValues.put( "maxHeartRate", String.format("%.2f", Heartrate.max(flight))); break;
			case "minHeartRate": this.featureValues.put( "minHeartRate", String.format("%.2f", Heartrate.min(flight))); break;
			case "avgOxygen": this.featureValues.put( "avgOxygen", String.format("%.2f", Oxygen.average(flight))); break;
			case "maxOxygen": this.featureValues.put( "maxOxygen", String.format("%.2f", Oxygen.max(flight))); break;
			case "minOxygen": this.featureValues.put( "minOxygen", String.format("%.2f", Oxygen.min(flight))); break;
			case "avgAirSpeed": this.featureValues.put( "avgAirSpeed", String.format("%.2f", AirSpeed.average(flight))); break;
			case "maxAirSpeed": this.featureValues.put( "maxAirSpeed", String.format("%.2f", AirSpeed.max(flight))); break;
			case "avgEnginePower": this.featureValues.put( "avgEnginePower", String.format("%.2f", EnginePower.average(flight))); break;
			case "maxEnginePower": this.featureValues.put( "maxEnginePower", String.format("%.2f", EnginePower.max(flight))); break;
			case "reachAlt": this.featureValues.put( "reachAlt", String.format("%.2f", Altitude.Reaching80PercentMaxAltitude.average(flight))); break;
			case "reachDist": this.featureValues.put( "reachDist", String.format("%.2f", FlightAnalysis.reachDistance(flight))); break;
			case "fastJetAlt": this.featureValues.put( "fastJetAlt", String.format("%.2f", Altitude.fastJetAltitude(flight))); break;
			case "flightDuration": this.featureValues.put( "flightDuration", FlightAnalysis.getDuration(flight)); break;
			case "flightDistance": this.featureValues.put( "flightDistance", String.format("%.2f", FlightAnalysis.computeFullDistance(flight))); break;
			case "flightDistanceCruise": this.featureValues.put( "flightDistanceCruise", String.format("%.2f", PhaseAnalysis.flightDistanceCruise(flight))); break;
			case "flightDistanceLanding": this.featureValues.put( "flightDistanceLanding", String.format("%.2f", PhaseAnalysis.flightDistanceLanding(flight))); break;
			case "flightDistanceTakeOff": this.featureValues.put( "flightDistanceTakeOff", String.format("%.2f", PhaseAnalysis.flightDistanceTakeOff(flight))); break;
			case "ratioDistance": this.featureValues.put( "ratioDistance", String.format("%.2f", FlightAnalysis.ratioDistance(flight))); break;
			case "windSpeed": this.featureValues.put( "windSpeed", String.format("%.2f", Wind.speed(flight))); break;
			case "windSpeedTakeOff": this.featureValues.put( "windSpeedTakeOff", String.format("%.2f", PhaseAnalysis.avgWindSpeedTakeOff(flight))); break;
			case "windSpeedCruise": this.featureValues.put( "windSpeedCruise", String.format("%.2f", PhaseAnalysis.avgWindSpeedCruise(flight))); break;
			case "windSpeedLanding": this.featureValues.put( "windSpeedLanding", String.format("%.2f", PhaseAnalysis.avgWindSpeedLanding(flight))); break;
			case "fastWindAlt": this.featureValues.put( "fastWindAlt", String.format("%.2f", Altitude.fastWindAltitude(flight))); break;
			case "noiseTemp": this.featureValues.put( "noiseTemp", String.format("%.2f", Temperature.noise(flight))); break;
			case "stressedPilot": this.featureValues.put( "stressedPilot", Stress.attack(flight)); break;
			case "maxAcceleration": this.featureValues.put( "maxAcceleration", String.format("%.2f", Acceleration.max(flight))); break;
			case "maxAccelerationTakeOff": this.featureValues.put( "maxAccelerationTakeOff", String.format("%.2f", PhaseAnalysis.maxAccelerationTakeOff(flight))); break;
			case "maxAccelerationCruise": this.featureValues.put( "maxAccelerationCruise", String.format("%.2f", PhaseAnalysis.maxAccelerationCruise(flight))); break;
			case "maxAccelerationLanding": this.featureValues.put( "maxAccelerationLanding", String.format("%.2f", PhaseAnalysis.maxAccelerationLanding(flight))); break;
			case "maxAccelG": this.featureValues.put( "maxAccelG", String.format("%.2f", Acceleration.maxInG(flight))); break;
			case "avgMachSpeed": this.featureValues.put( "avgMachSpeed", String.format("%.2f", MachSpeed.average(flight))); break;
			case "maxMachSpeed": this.featureValues.put( "maxMachSpeed", String.format("%.2f", MachSpeed.max(flight))); break;
			case "takeOff": this.featureValues.put("takeOff", Phases.takeOff(flight)); break;
			case "cruise": this.featureValues.put("cruise", Phases.cruise(flight)); break;
			case "landing": this.featureValues.put("landing", Phases.landing(flight)); break;
			case "avgAirSpeedTakeOff": this.featureValues.put("avgAirSpeedTakeOff", String.format("%.2f", PhaseAnalysis.avgAirSpeedTakeOff(flight))); break;
			case "avgAirSpeedCruise": this.featureValues.put("avgAirSpeedCruise", String.format("%.2f", PhaseAnalysis.avgAirSpeedCruise(flight))); break;
			case "avgAirSpeedLanding": this.featureValues.put("avgAirSpeedLanding", String.format("%.2f", PhaseAnalysis.avgAirSpeedLanding(flight))); break;
			case "maxAirSpeedTakeOff": this.featureValues.put("maxAirSpeedTakeOff", String.format("%.2f", PhaseAnalysis.maxAirSpeedTakeOff(flight))); break;
			case "maxAirSpeedCruise": this.featureValues.put("maxAirSpeedCruise", String.format("%.2f", PhaseAnalysis.maxAirSpeedCruise(flight))); break;
			case "maxAirSpeedLanding": this.featureValues.put("maxAirSpeedLanding", String.format("%.2f", PhaseAnalysis.maxAirSpeedLanding(flight))); break;
			case "avgEnginePowerTakeOff": this.featureValues.put("avgEnginePowerTakeOff", String.format("%.2f", PhaseAnalysis.avgEnginePowerTakeOff(flight))); break;
		    case "avgEnginePowerCruise": this.featureValues.put("avgEnginePowerCruise", String.format("%.2f", PhaseAnalysis.avgEnginePowerCruise(flight))); break;
		    case "avgEnginePowerLanding": this.featureValues.put("avgEnginePowerLanding", String.format("%.2f", PhaseAnalysis.avgEnginePowerLanding(flight))); break;
		    case "maxEnginePowerTakeOff": this.featureValues.put("maxEnginePowerTakeOff", String.format("%.2f", PhaseAnalysis.maxEnginePowerTakeOff(flight))); break;
		    case "maxEnginePowerCruise": this.featureValues.put("maxEnginePowerCruise", String.format("%.2f", PhaseAnalysis.maxEnginePowerCruise(flight))); break;
		    case "maxEnginePowerLanding": this.featureValues.put("maxEnginePowerLanding", String.format("%.2f", PhaseAnalysis.maxEnginePowerLanding(flight))); break;
		}
	}
	
	public void executeAll() {
		this.featureValues.put("avgAcceleration", String.format("%.3f", Acceleration.average(flight)));
		this.featureValues.put("avgAccelerationCruise", String.format("%.2f", PhaseAnalysis.avgAccelerationCruise(flight)));
		this.featureValues.put("avgAccelerationLanding", String.format("%.2f", PhaseAnalysis.avgAccelerationLanding(flight)));
		this.featureValues.put("avgAccelerationTakeOff", String.format("%.2f", PhaseAnalysis.avgAccelerationTakeOff(flight)));
		this.featureValues.put("avgAirSpeed", String.format("%.2f", AirSpeed.average(flight)));
		this.featureValues.put("avgAirSpeedCruise", String.format("%.2f", PhaseAnalysis.avgAirSpeedCruise(flight)));
		this.featureValues.put("avgAirSpeedLanding", String.format("%.2f", PhaseAnalysis.avgAirSpeedLanding(flight)));
		this.featureValues.put("avgAirSpeedTakeOff", String.format("%.2f", PhaseAnalysis.avgAirSpeedTakeOff(flight)));
		this.featureValues.put("avgAlt", String.format("%.2f", Altitude.average(flight)));
		this.featureValues.put("avgEnginePower", String.format("%.2f", EnginePower.average(flight)));
		this.featureValues.put("avgEnginePowerCruise", String.format("%.2f", PhaseAnalysis.avgEnginePowerCruise(flight)));
		this.featureValues.put("avgEnginePowerLanding", String.format("%.2f", PhaseAnalysis.avgEnginePowerLanding(flight)));
		this.featureValues.put("avgEnginePowerTakeOff", String.format("%.2f", PhaseAnalysis.avgEnginePowerTakeOff(flight)));
		this.featureValues.put("avgHeartRate", String.format("%.2f", Heartrate.average(flight)));
		this.featureValues.put("avgHumidity", String.format("%.2f", Humidity.average(flight)));
		this.featureValues.put("avgMachSpeed", String.format("%.2f", MachSpeed.average(flight)));
		this.featureValues.put("avgOxygen", String.format("%.2f", Oxygen.average(flight)));
		this.featureValues.put("avgPressure", String.format("%.2f", Pressure.average(flight)));
		this.featureValues.put("avgTemp", String.format("%.2f", Temperature.average(flight)));
    
    	this.featureValues.put("cruise", Phases.cruise(flight));
		this.featureValues.put("fastJetAlt", String.format("%.2f", Altitude.fastJetAltitude(flight)));
		this.featureValues.put("fastWindAlt", String.format("%.2f", Altitude.fastWindAltitude(flight)));
		this.featureValues.put("flightDistance", String.format("%.2f", FlightAnalysis.computeFullDistance(flight)));
		this.featureValues.put("flightDistanceCruise", String.format("%.2f", PhaseAnalysis.flightDistanceCruise(flight)));
		this.featureValues.put("flightDistanceLanding", String.format("%.2f", PhaseAnalysis.flightDistanceLanding(flight)));
		this.featureValues.put("flightDistanceTakeOff", String.format("%.2f", PhaseAnalysis.flightDistanceTakeOff(flight)));
		this.featureValues.put("flightDuration", FlightAnalysis.getDuration(flight));
    	this.featureValues.put("landing", Phases.landing(flight));
    
		this.featureValues.put("maxAirSpeed", String.format("%.2f", AirSpeed.max(flight)));
		this.featureValues.put("maxAirSpeedCruise", String.format("%.2f", PhaseAnalysis.maxAirSpeedCruise(flight)));
		this.featureValues.put("maxAirSpeedLanding", String.format("%.2f", PhaseAnalysis.maxAirSpeedLanding(flight)));
    	this.featureValues.put("maxAirSpeedTakeOff", String.format("%.2f", PhaseAnalysis.maxAirSpeedTakeOff(flight)));
    
		this.featureValues.put("maxAcceleration", String.format("%.2f", Acceleration.max(flight)));
		this.featureValues.put("maxAccelerationCruise", String.format("%.2f", PhaseAnalysis.maxAccelerationCruise(flight)));
		this.featureValues.put("maxAccelerationLanding", String.format("%.2f", PhaseAnalysis.maxAccelerationLanding(flight)));
		this.featureValues.put("maxAccelerationTakeOff", String.format("%.2f", PhaseAnalysis.maxAccelerationTakeOff(flight)));
		this.featureValues.put("maxAccelG", String.format("%.2f", Acceleration.maxInG(flight)));
		this.featureValues.put("maxAlt", String.format("%.2f", Altitude.max(flight)));
    
		this.featureValues.put("maxEnginePower", String.format("%.2f", EnginePower.max(flight)));
		this.featureValues.put("maxEnginePowerCruise", String.format("%.2f", PhaseAnalysis.maxEnginePowerCruise(flight)));
		this.featureValues.put("maxEnginePowerLanding", String.format("%.2f", PhaseAnalysis.maxEnginePowerLanding(flight)));
    	this.featureValues.put("maxEnginePowerTakeOff", String.format("%.2f", PhaseAnalysis.maxEnginePowerTakeOff(flight)));
    
		this.featureValues.put("maxHeartRate", String.format("%.2f", Heartrate.max(flight)));
		this.featureValues.put("maxHumidity", String.format("%.2f", Humidity.max(flight)));
		this.featureValues.put("maxMachSpeed", String.format("%.2f", MachSpeed.max(flight)));
		this.featureValues.put("maxOxygen", String.format("%.2f", Oxygen.max(flight)));
		this.featureValues.put("maxPressure", String.format("%.2f", Pressure.max(flight)));
		this.featureValues.put("maxTemp", String.format("%.2f", Temperature.max(flight)));
    
		this.featureValues.put("minHeartRate", String.format("%.2f", Heartrate.min(flight)));
		this.featureValues.put("minHumidity", String.format("%.2f", Humidity.min(flight)));
		this.featureValues.put("minOxygen", String.format("%.2f", Oxygen.min(flight)));
		this.featureValues.put("minPressure", String.format("%.2f", Pressure.min(flight)));
		this.featureValues.put("minTemp", String.format("%.2f", Temperature.min(flight)));
    
		this.featureValues.put("noiseTemp", String.format("%.2f", Temperature.noise(flight)));
		this.featureValues.put("ratioDistance", String.format("%.2f", FlightAnalysis.ratioDistance(flight)));
		this.featureValues.put("reachAlt", String.format("%.2f", Altitude.Reaching80PercentMaxAltitude.average(flight)));
		this.featureValues.put("reachDist", String.format("%.2f", FlightAnalysis.reachDistance(flight)));
		this.featureValues.put("stressedPilot", Stress.attack(flight));
    	this.featureValues.put("takeOff", Phases.takeOff(flight));

		this.featureValues.put("windSpeed", String.format("%.2f", Wind.speed(flight)));
		this.featureValues.put("windSpeedCruise", String.format("%.2f", PhaseAnalysis.avgWindSpeedCruise(flight)));
		this.featureValues.put("windSpeedLanding", String.format("%.2f", PhaseAnalysis.avgWindSpeedLanding(flight)));
		this.featureValues.put("windSpeedTakeOff", String.format("%.2f", PhaseAnalysis.avgWindSpeedTakeOff(flight)));
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
