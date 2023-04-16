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
		return Utils.ComputeDistanceBetween(flight, 0, flight.getRecords().size() - 1);
	}
}
