package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

public class EnginePower {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double enginePowerSum = 0;
		
<<<<<<< HEAD
		for (int i = 0; i<flightRecords.size(); i++) enginePowerSum += flightRecords.get(i).getEnginePower();
=======
		for (int i = 0; i<flightRecords.size(); i++) enginePowerSum += flightRecords.get(i).getEngine_0_power();
>>>>>>> 6d0e914b14e0949a00250f329b262503284daa72
		
		return enginePowerSum / flightRecords.size();
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
<<<<<<< HEAD
		double maxEnginePower = flightRecords.get(0).getEnginePower();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(maxEnginePower < flightRecords.get(i).getEnginePower()) {
				maxEnginePower = flightRecords.get(i).getEnginePower();
=======
		double maxEnginePower = flightRecords.get(0).getEngine_0_power();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(maxEnginePower < flightRecords.get(i).getEngine_0_power()) {
				maxEnginePower = flightRecords.get(i).getEngine_0_power();
>>>>>>> 6d0e914b14e0949a00250f329b262503284daa72
			};
		}
		return maxEnginePower;
	}

}
