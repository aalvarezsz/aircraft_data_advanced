package fr.estia.pandora.analyses;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Position;
import fr.estia.pandora.model.Utils;


public class Acceleration {
	public static double average(Flight flight) {
		double avgAcceleration = 0;
		double averageGroundSpeed = 0;
		double duration;

		if(flight.getRecords().size() <= 1) return 0;
		
		for(int i=0; i < flight.getRecords().size() - 1; i++) {
			Position currentPosition = new Position(flight.getRecords().get(i).getLatitude(), flight.getRecords().get(i).getLongitude(), flight.getRecords().get(i).getAltitude());
			Position nextPosition = new Position(flight.getRecords().get(i+1).getLatitude(), flight.getRecords().get(i+1).getLongitude(), flight.getRecords().get(i+1).getAltitude());
			duration = flight.getRecords().get(i).getTimestamp() - flight.getRecords().get(i+1).getTimestamp() ;
			
			double distance = Utils.ComputeDistance(currentPosition, nextPosition);
			System.out.println("DEBUG " + i + ": " + distance);
			averageGroundSpeed += distance / duration;
		}
		
		double startTime = flight.getRecords().get( 0 ).getTimestamp();
		double endTime = flight.getRecords().get(flight.getRecords().size() - 1) .getTimestamp();
		
		duration = endTime - startTime ;
		
		avgAcceleration = averageGroundSpeed / duration ;
		
		return avgAcceleration;
	}
	
	
	public static double max(Flight flight) {
		double maxAcceleration = 0;
		double distance = 0;
		double acceleration = 0;
		double fullDistance = 0;
		double duration = 0;

		if(flight.getRecords().size() <= 1) distance = fullDistance;
		
		for(int i=0; i < flight.getRecords().size() - 1; i++) {
			Position currentPosition = new Position(flight.getRecords().get(i).getLatitude(), flight.getRecords().get(i).getLongitude(), flight.getRecords().get(i).getAltitude());
			Position nextPosition = new Position(flight.getRecords().get(i+1).getLatitude(), flight.getRecords().get(i+1).getLongitude(), flight.getRecords().get(i+1).getAltitude());
			duration = flight.getRecords().get(i).getTimestamp() - flight.getRecords().get(i+1).getTimestamp() ;
			
			distance = Utils.ComputeDistance(currentPosition, nextPosition);

			acceleration = (distance / duration) / duration;
			
			if (maxAcceleration < Math.abs(acceleration)) maxAcceleration = acceleration;
		}
		
		return maxAcceleration;
	}
	
}