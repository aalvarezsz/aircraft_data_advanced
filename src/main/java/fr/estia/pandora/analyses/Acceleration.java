package fr.estia.pandora.analyses;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Position;
import fr.estia.pandora.model.Utils;


public class Acceleration {
	public static double average(Flight flight) {
		double avgAcceleration = 0;
		double tempDistance = 0;
		double averageGroundSpeed = 0;
		double fullDistance = 0.00;
		
		double startTime = flight.getRecords().get( 0 ).getTimestamp();
		double endTime = flight.getRecords().get(flight.getRecords().size() - 1) .getTimestamp();
		
		double duration = Double.parseDouble(Utils.TimestampDurationToString(startTime, endTime)) ;
		

		if(flight.getRecords().size() <= 1) tempDistance = fullDistance;
		
		for(int i=0; i < flight.getRecords().size() - 1; i++) {
			Position currentPosition = new Position(flight.getRecords().get(i).getLatitude(), flight.getRecords().get(i).getLongitude());
			Position nextPosition = new Position(flight.getRecords().get(i+1).getLatitude(), flight.getRecords().get(i+1).getLongitude());
			
			tempDistance += Utils.Haversine(currentPosition, nextPosition);
			
		}
		
		
		averageGroundSpeed = tempDistance / duration ;
		
		avgAcceleration = averageGroundSpeed / duration ;
		
		return avgAcceleration;
	}
	
	
	public static double max(Flight flight) {
		double maxAcceleration = 0;
		double tempAcceleration = 0 ;
		double tempDistance = 0;
		double instantGroundSpeed = 0;
		double fullDistance = 0.00;
		
		double startTime = flight.getRecords().get( 0 ).getTimestamp();
		double endTime = flight.getRecords().get(flight.getRecords().size() - 1) .getTimestamp();
		
		double duration = Double.parseDouble(Utils.TimestampDurationToString(startTime, endTime)) ;
		

		if(flight.getRecords().size() <= 1) tempDistance = fullDistance;
		
		for(int i=0; i < flight.getRecords().size() - 1; i++) {
			Position currentPosition = new Position(flight.getRecords().get(i).getLatitude(), flight.getRecords().get(i).getLongitude());
			Position nextPosition = new Position(flight.getRecords().get(i+1).getLatitude(), flight.getRecords().get(i+1).getLongitude());
			
			tempDistance += Utils.Haversine(currentPosition, nextPosition);
			instantGroundSpeed = tempDistance / duration ;
			
			tempAcceleration = instantGroundSpeed / flight.getRecords().get( i ).getTimestamp() ;
			
			if (maxAcceleration < tempAcceleration) {
				maxAcceleration = tempAcceleration ;
			};
		}
		
		return maxAcceleration;
	}
	
}