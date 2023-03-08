package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

public class EnginePower {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double enginePowerSum = 0;
		
		for (int i = 0; i<flightRecords.size(); i++) enginePowerSum += flightRecords.get(i).getEngine_0_power();
		
		return enginePowerSum / flightRecords.size() / 754.7;
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxEnginePower = flightRecords.get(0).getEngine_0_power();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(maxEnginePower < flightRecords.get(i).getEngine_0_power()) {
				maxEnginePower = flightRecords.get(i).getEngine_0_power();
			};
		}
		return maxEnginePower / 754.7;
	}

}
