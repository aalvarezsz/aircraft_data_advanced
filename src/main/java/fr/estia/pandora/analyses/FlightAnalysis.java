package fr.estia.pandora.analyses;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Position;
import fr.estia.pandora.model.Record;
import fr.estia.pandora.model.Utils;

import java.util.List;

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

	public static double ratioDistance(Flight flight) {
		int lastFlightRecordIndex = flight.getRecords().size() - 1;
		Position startPosition = new Position(flight.getRecords().get(0).getLatitude(), flight.getRecords().get(0).getLongitude(), flight.getRecords().get(0).getAltitude());
		Position endPosition = new Position(flight.getRecords().get(lastFlightRecordIndex).getLatitude(), flight.getRecords().get(lastFlightRecordIndex).getLongitude(), flight.getRecords().get(lastFlightRecordIndex).getAltitude());

		return computeFullDistance(flight) / Utils.ComputeDistance(startPosition, endPosition);
	}

	public static double reachDistance(Flight flight) {
		List<Record> flightRecords = flight.getRecords();
		double ratioReachedDistance = computeFullDistance(flight) * 0.8;

		double distanceReached = 0;
		int i = 0;

		for(i = 0; i<flightRecords.size() - 1; i++) {
			distanceReached += Utils.ComputeDistanceBetween(flight, i, i+1);
			if(distanceReached >= ratioReachedDistance) break;
		}

		double timeToReach = flightRecords.get(i).getTimestamp() - flightRecords.get(0).getTimestamp();

		return timeToReach / 60;
	}
}
