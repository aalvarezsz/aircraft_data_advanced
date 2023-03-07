package fr.estia.pandora.analyses;

import fr.estia.pandora.model.Flight;

public class FlightDuration {
	
	public static double compute(Flight flight) {
		double startTime = flight.getRecords().get( 0 ).getTimestamp();
		double endTime = flight.getRecords().get( flight.getRecords().size() -1 ) .getTimestamp() ;
		
		return endTime - startTime;
	}

}
