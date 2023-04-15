package fr.estia.pandora.analyses;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Position;
import fr.estia.pandora.model.Utils;

public class FlightAnalysis {
	
	public static String getDuration(Flight flight) {
		double rawDuration = getRawDuration(flight);
		return Utils.TimestampDurationToString(rawDuration);
	}

	public static double getRawDuration(Flight flight) {
		double startTime = flight.getRecords().get( 0 ).getTimestamp();
		double endTime = flight.getRecords().get(flight.getRecords().size() - 1) .getTimestamp();

		return endTime - startTime;
	}
	
	public static double computeFullDistance(Flight flight) {
		double fullDistance = 0;
		
		if(flight.getRecords().size() <= 1) return fullDistance;
		for(int i=0; i < flight.getRecords().size() - 1; i++) {
			Position currentPosition = new Position(flight.getRecords().get(i).getLatitude(), flight.getRecords().get(i).getLongitude(), flight.getRecords().get(i).getAltitude());
			Position nextPosition = new Position(flight.getRecords().get(i+1).getLatitude(), flight.getRecords().get(i+1).getLongitude(), flight.getRecords().get(i+1).getAltitude());
			
			fullDistance += Utils.ComputeDistance(currentPosition, nextPosition);
		}
		
		return fullDistance;
	}
}
