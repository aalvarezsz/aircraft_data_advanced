package fr.estia.pandora.analyses;

import java.util.Calendar;
import java.util.Date;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Position;

public class FlightAnalysis {
	public static final double EARTH_RADIUS = 6371000.0; // Earth's radius in m
	
	public static double computeDuration(Flight flight) {
		double startTime = flight.getRecords().get( 0 ).getTimestamp();
		double endTime = flight.getRecords().get(flight.getRecords().size() - 1) .getTimestamp() ;
		
		return endTime - startTime;
	}
	
	public static String getFormattedDuration(double timestamp) {
		Date date = new Date((long) timestamp * 1000);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		
		return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
	}
	
	public static double computeFullDistance(Flight flight) {
		double fullDistance = 0.0;
		
		for(int i=0; i < flight.getRecords().size() - 1; i++) {
			Position currentPosition = new Position(flight.getRecords().get(i).getLatitude(), flight.getRecords().get(i).getLongitude());
			Position nextPosition = new Position(flight.getRecords().get(i+1).getLatitude(), flight.getRecords().get(i+1).getLongitude());
			
			fullDistance += haversine(currentPosition, nextPosition);
		}
		
		return fullDistance;
	}
	
	private static double haversine(Position startPosition, Position endPosition) {
	     double dLat = Math.toRadians(endPosition.getLatitude() - startPosition.getLatitude());
	     double dLon = Math.toRadians(endPosition.getLongitude() - startPosition.getLongitude());
		
	    double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(startPosition.getLatitude()) * Math.cos(endPosition.getLatitude());
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

	    return EARTH_RADIUS * c;
	}

}
