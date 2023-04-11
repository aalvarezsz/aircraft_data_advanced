package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

public class Wind {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double averageWindSpeed = 0;
		
		for (int i = 0; i<flightRecords.size(); i++) {
			averageWindSpeed += flightRecords.get(i).getAir_speed();
		}
		return averageWindSpeed / flightRecords.size();
	}
	
	
	public static double altitudeWithFastestWind(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		
		return 0.0;
	}
	
}
