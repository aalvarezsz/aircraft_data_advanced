package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

public class EnginePower {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double enginePowerSum = 0;
		
		for (int i = 0; i<flightRecords.size(); i++) enginePowerSum += flightRecords.get(i).getEnginePower();
		
		return enginePowerSum / flightRecords.size();
	}

	public static double average(Flight flight, int startIndex, int endIndex) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double enginePowerSum = 0;

		for (int i = startIndex; i<endIndex; i++) enginePowerSum += flightRecords.get(i).getEnginePower();

		return enginePowerSum / flightRecords.size();
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxEnginePower = flightRecords.get(0).getEnginePower();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(maxEnginePower < flightRecords.get(i).getEnginePower()) {
				maxEnginePower = flightRecords.get(i).getEnginePower();
			};
		}
		return maxEnginePower;
	}

	public static double max(Flight flight, int startIndex, int endIndex) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxEnginePower = flightRecords.get(startIndex).getEnginePower();

		for (int i = startIndex + 1; i<endIndex; i++) {
			if(maxEnginePower < flightRecords.get(i).getEnginePower()) {
				maxEnginePower = flightRecords.get(i).getEnginePower();
			};
		}
		return maxEnginePower;
	}

}
