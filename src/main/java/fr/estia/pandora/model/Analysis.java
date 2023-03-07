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
<<<<<<< HEAD
	private String targetFeature;

=======
>>>>>>> 6d0e914b14e0949a00250f329b262503284daa72

	/**
	 * Constructor, create a basic analysis 
	 * @param flight
	 */
<<<<<<< HEAD
	public Analysis(Flight flight, String targetFeature) {		
=======
	public Analysis(Flight flight, String targetFeature) {
>>>>>>> 6d0e914b14e0949a00250f329b262503284daa72
		this.flight = flight;
		this.flightDuration = FlightDuration.compute(this.flight);
		
		this.featureValues = new HashMap<String, String> ();
<<<<<<< HEAD
		this.targetFeature = targetFeature;
		
		this.execute();
	}
	
	public void execute() {
=======
		
		if(targetFeature.equals("null")) this.executeAll();
		else this.execute(targetFeature);
	}
	
	public void execute(String targetFeature) {
>>>>>>> 6d0e914b14e0949a00250f329b262503284daa72
		switch(targetFeature) {
			case "flightDuration":
				this.featureValues.put( "flightDuration", String.format("%.2f", this.flightDuration));
				break;
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
<<<<<<< HEAD
			default:
				System.out.println("La fonctionnalité n'existe pas.");
				break;
		}
	}
=======
		}
	}
	
	public void executeAll() {
		this.featureValues.put( "avgEnginePower", String.format("%.2f", EnginePower.average(flight)));
		this.featureValues.put( "flightDuration", String.format("%.2f", this.flightDuration));
		this.featureValues.put( "maxEnginePower", String.format("%.2f", EnginePower.max(flight)));
	}
>>>>>>> 6d0e914b14e0949a00250f329b262503284daa72
	
	public String getFeatureValue( String feature ) {
		String value = "" ;
		if( featureValues.containsKey(feature)) {
			value = featureValues.get(feature) ;
		}
		return value ;
	}

<<<<<<< HEAD
	/*
		This section compute the flight duration by taking the difference of timestamp
		between the first and last records. The difference is store in s in a variable for future computation.
		in the map the duration in second is stored
		TODO: for the feature -o flightDuration, the format is wrong it should be HH:mm:ss
	*/
//	private void computeFlightDuration() {
//		double startTime, endTime ;
//		startTime = flight.getRecords().get( 0 ).getTimestamp();
//		endTime = flight.getRecords().get( flight.getRecords().size() -1 ) .getTimestamp() ;
//		setFlightDuration(endTime - startTime) ;
//	}
//	public double getFlightDuration() {
//		return flightDuration;
//	}
//	public void setFlightDuration(double flightDuration) {
//		//Store for further computation where flight duration in second might be useful
//		this.flightDuration = flightDuration;
//		//TODO: change to store with the correct format HH:mm:ss
//		this.featureValues.put( "flightDuration", String.format("%.2f", flightDuration ) ) ;
//	}

=======
>>>>>>> 6d0e914b14e0949a00250f329b262503284daa72
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
