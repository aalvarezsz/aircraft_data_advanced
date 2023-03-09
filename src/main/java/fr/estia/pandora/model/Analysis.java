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
			case "avgAltitude":
				System.out.println("Average Altitude");
				break;
			case "maxAltitude":
				System.out.println("Average Altitude");
				break;
			case "avgAirSpeed":
				System.out.println("Average Altitude");
				break;
			case "maxAirSpeed":
				System.out.println("Average Altitude");
				break;
			case "avgEnginePower":
				this.featureValues.put( "avgEnginePower", String.format("%.2f", EnginePower.average(flight)));
				break;
			case "maxEnginePower":
				this.featureValues.put( "maxEnginePower", String.format("%.2f", EnginePower.max(flight)));
				break;
			case "flightDuration":
				this.featureValues.put( "flightDuration", FlightAnalysis.getDuration(flight));
				break;
			case "flightDistance":
				this.featureValues.put( "flightDistance", String.format("%.2f", FlightAnalysis.computeFullDistance(flight)));
				break;
		}
	}
	
	public void executeAll() {
		this.featureValues.put( "avgEnginePower", String.format("%.2f", EnginePower.average(flight)));
		this.featureValues.put( "flightDistance", String.format("%.2f", FlightAnalysis.computeFullDistance(flight)));
		this.featureValues.put( "flightDuration", FlightAnalysis.getDuration(flight));
		this.featureValues.put( "maxEnginePower", String.format("%.2f", EnginePower.max(flight)));
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
