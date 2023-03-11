package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

public class MachSpeed {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double airspeedSum = 0;
		
		for (int i = 0; i<flightRecords.size(); i++) airspeedSum += flightRecords.get(i).getAir_speed();
		
		return (airspeedSum / flightRecords.size())  *0.00291175304 ;
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxAirspeed = flightRecords.get(0).getAir_speed();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(maxAirspeed < flightRecords.get(i).getAir_speed()) {
				maxAirspeed = flightRecords.get(i).getAir_speed();
			};
		}
		return maxAirspeed *0.00291175304 ;
	}
	

}