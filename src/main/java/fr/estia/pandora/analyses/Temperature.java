package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

public class Temperature {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double temperatureSum = 0;
		
		for (int i = 0; i<flightRecords.size(); i++) temperatureSum += flightRecords.get(i).getTemperature_in();
		
		return temperatureSum / flightRecords.size();
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxTemperature = flightRecords.get(0).getTemperature_in();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(maxTemperature < flightRecords.get(i).getTemperature_in()) {
				maxTemperature = flightRecords.get(i).getTemperature_in();
			};
		}
		return maxTemperature;
	}
	
	public static double min(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double minTemperature = flightRecords.get(0).getTemperature_in();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(minTemperature > flightRecords.get(i).getTemperature_in()) {
				minTemperature = flightRecords.get(i).getTemperature_in();
			};
		}
		return minTemperature;
	}
	
	
	public static double noise(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double temperatureSum = 0;
		
		for (int i = 0; i<flightRecords.size(); i++) temperatureSum += flightRecords.get(i).getTemperature_in();
		
		return temperatureSum / flightRecords.size() - 25;
	}
	
}

