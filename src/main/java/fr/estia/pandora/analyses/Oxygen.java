package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

public class Oxygen {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double oxygenSum = 0;
		
		for (int i = 0; i<flightRecords.size(); i++) oxygenSum += flightRecords.get(i).getOxygen_mask();
		
		return oxygenSum / flightRecords.size();
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxOxygen = flightRecords.get(0).getOxygen_mask();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(maxOxygen < flightRecords.get(i).getOxygen_mask()) {
				maxOxygen = flightRecords.get(i).getOxygen_mask();
			};
		}
		return maxOxygen;
	}
	
	public static double min(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double minOxygen = flightRecords.get(0).getOxygen_mask();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(minOxygen > flightRecords.get(i).getOxygen_mask()) {
				minOxygen = flightRecords.get(i).getOxygen_mask();
			};
		}
		return minOxygen;
	}

}

