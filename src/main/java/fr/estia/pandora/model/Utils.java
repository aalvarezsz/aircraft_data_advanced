package fr.estia.pandora.model;

public class Utils {
	public static final double EARTH_RADIUS = 6371000.00;
	
	public static double Haversine(Position startPosition, Position endPosition) {
	    double dLat = Math.toRadians(endPosition.getLatitude() - startPosition.getLatitude());
	    double dLon = Math.toRadians(endPosition.getLongitude() - startPosition.getLongitude());
	    
	    double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(Math.toRadians(startPosition.getLatitude())) * Math.cos(Math.toRadians(endPosition.getLatitude()));
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    
	    return (EARTH_RADIUS + startPosition.getAltitude()) * c;
	}
	
	public static double ComputeDistance(Position startPosition, Position endPosition) {
		double flatDistance = Haversine(startPosition, endPosition);
		return Math.sqrt(Math.pow(flatDistance, 2) + Math.pow(endPosition.getAltitude() - startPosition.getAltitude(), 2));
	}
	
	public static String TimestampDurationToString(double startTime, double endTime) {
		double duration = endTime - startTime;
		
		int hours = (int) duration / 3600;
		duration -= hours * 3600;
		int minutes = (int) duration / 60;
		duration -= minutes * 60;
		int seconds = (int) duration;
		
		return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
	}
}
