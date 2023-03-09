package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

public class EnginePower {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		boolean hasTwoEngines = flightRecords.get(0).getEngine_1_power() != 0;
		boolean hasFourEngines = flightRecords.get(0).getEngine_3_power() != 0;
		double enginePowerSum = 0;
		
		for (int i = 0; i<flightRecords.size(); i++) enginePowerSum += flightRecords.get(i).getEngine_0_power();
		double averagePower = enginePowerSum / flightRecords.size();
		
		return hasFourEngines ? averagePower * 4 : hasTwoEngines ? averagePower * 2 : averagePower;
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxEnginePower = flightRecords.get(0).getEngine_0_power();
		boolean hasTwoEngines = flightRecords.get(0).getEngine_1_power() != 0;
		boolean hasFourEngines = flightRecords.get(0).getEngine_3_power() != 0;
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(maxEnginePower < flightRecords.get(i).getEngine_0_power()) {
				maxEnginePower = flightRecords.get(i).getEngine_0_power();
			};
		}
		return hasFourEngines ? maxEnginePower * 4 : hasTwoEngines ? maxEnginePower * 2 : maxEnginePower;
	}

}
