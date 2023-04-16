package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

public class AirSpeed { // m/s for US planes, knots for RU planes
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double airspeedSum = 0;
		
		for (int i = 0; i<flightRecords.size(); i++) airspeedSum += flightRecords.get(i).getAir_speed();
		
		return airspeedSum / flightRecords.size();
	}

	public static double average(Flight flight, int startIndex, int endIndex) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double airspeedSum = 0;

		for (int i = startIndex; i<endIndex; i++) airspeedSum += flightRecords.get(i).getAir_speed();

		return airspeedSum / flightRecords.size();
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxAirspeed = flightRecords.get(0).getAir_speed();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(maxAirspeed < flightRecords.get(i).getAir_speed()) {
				maxAirspeed = flightRecords.get(i).getAir_speed();
			};
		}
		return maxAirspeed;
	}

	public static double max(Flight flight, int startIndex, int endIndex) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxAirspeed = flightRecords.get(startIndex).getAir_speed();

		for (int i = startIndex + 1; i<endIndex; i++) {
			if(maxAirspeed < flightRecords.get(i).getAir_speed()) {
				maxAirspeed = flightRecords.get(i).getAir_speed();
			};
		}
		return maxAirspeed;
	}
}

