package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

public class Altitude {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double altitudeSum = 0;
		
		for (int i = 0; i<flightRecords.size(); i++) altitudeSum += flightRecords.get(i).getAltitude();
		
		return altitudeSum / flightRecords.size();
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxAltitude = flightRecords.get(0).getAltitude();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(maxAltitude < flightRecords.get(i).getAltitude()) {
				maxAltitude = flightRecords.get(i).getAltitude();
			};
		}
		return maxAltitude;
	}
	
	public static double min(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double minAltitude = flightRecords.get(0).getAltitude();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(minAltitude > flightRecords.get(i).getAltitude()) {
				minAltitude = flightRecords.get(i).getAltitude();
			};
		}
		return minAltitude;
	}

}

