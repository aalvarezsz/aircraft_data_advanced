package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

public class Acceleration {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double speedDelta = 0;
		double timestampForDivision = 0;
		double accelerationSum = 0;
		
		for (int i = 1; i<flightRecords.size(); i++) {
			speedDelta += flightRecords.get(i).getAir_speed() - flightRecords.get(i-1).getAir_speed();
			timestampForDivision = flightRecords.get(i).getTimestamp() - flightRecords.get(i-1).getTimestamp();
			accelerationSum += speedDelta / timestampForDivision ;
		};
		
		return accelerationSum / flightRecords.size();
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxAcceleration = 0 ;
		
		double speedDelta = 0;
		double timestampForDivision = 0;
		double accelerationSum = 0;
		
		for (int i = 1; i<flightRecords.size(); i++) {
			speedDelta += flightRecords.get(i).getAir_speed() - flightRecords.get(i-1).getAir_speed();
			timestampForDivision = flightRecords.get(i).getTimestamp() - flightRecords.get(i-1).getTimestamp();
			accelerationSum += speedDelta / timestampForDivision ;
			
			if(maxAcceleration < accelerationSum) {
				maxAcceleration = accelerationSum;
			};
		}
		return maxAcceleration;
	}
	

}

