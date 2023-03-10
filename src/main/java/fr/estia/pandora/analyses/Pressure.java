package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

public class Pressure {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double pressureSum = 0;
		
		for (int i = 0; i<flightRecords.size(); i++) pressureSum += flightRecords.get(i).getPressure_in();
		
		return pressureSum / flightRecords.size();
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxPressure = flightRecords.get(0).getPressure_in();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(maxPressure < flightRecords.get(i).getPressure_in()) {
				maxPressure = flightRecords.get(i).getPressure_in();
			};
		}
		return maxPressure;
	}
	
	public static double min(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double minPressure = flightRecords.get(0).getPressure_in();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(minPressure > flightRecords.get(i).getPressure_in()) {
				minPressure = flightRecords.get(i).getPressure_in();
			};
		}
		return minPressure;
	}

}

