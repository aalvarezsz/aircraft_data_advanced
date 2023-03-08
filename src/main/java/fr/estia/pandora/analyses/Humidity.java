package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

public class Humidity {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double humiditySum = 0;
		
		for (int i = 0; i<flightRecords.size(); i++) humiditySum += flightRecords.get(i).getHumidity_in();
		
		return humiditySum / flightRecords.size();
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxHumidity = flightRecords.get(0).getHumidity_in();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(maxHumidity < flightRecords.get(i).getHumidity_in()) {
				maxHumidity = flightRecords.get(i).getHumidity_in();
			};
		}
		return maxHumidity;
	}
	
	public static double min(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double minHumidity = flightRecords.get(0).getHumidity_in();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(minHumidity > flightRecords.get(i).getHumidity_in()) {
				minHumidity = flightRecords.get(i).getHumidity_in();
			};
		}
		return minHumidity;
	}

}

