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

	public static double ComputeDistanceBetween(Flight flight, int startIndex, int endIndex) {
		double fullDistance = 0;
		if(flight.getRecords().size() <= 1) return fullDistance;

		for(int i=startIndex; i < endIndex; i++) {
			Position currentPosition = new Position(flight.getRecords().get(i).getLatitude(), flight.getRecords().get(i).getLongitude(), flight.getRecords().get(i).getAltitude());
			Position nextPosition = new Position(flight.getRecords().get(i+1).getLatitude(), flight.getRecords().get(i+1).getLongitude(), flight.getRecords().get(i+1).getAltitude());

			fullDistance += Utils.ComputeDistance(currentPosition, nextPosition);
		}

		return fullDistance / 1000;
	}
	
	public static String TimestampDurationToString(double duration) {
		int hours = (int) duration / 3600;
		duration -= hours * 3600;
		int minutes = (int) duration / 60;
		duration -= minutes * 60;
		int seconds = (int) duration;
		
		return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
	}
}
