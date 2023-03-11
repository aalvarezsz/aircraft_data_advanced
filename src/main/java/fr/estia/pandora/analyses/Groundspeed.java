package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

public class Groundspeed {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double groundspeedSum = 0;
		
		for (int i = 0; i<flightRecords.size(); i++) groundspeedSum += flightRecords.get(i).getGroundspeed();
		
		return groundspeedSum / flightRecords.size();
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxGroundspeed = flightRecords.get(0).getGroundspeed();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(maxGroundspeed < flightRecords.get(i).getGroundspeed()) {
				maxGroundspeed = flightRecords.get(i).getGroundspeed();
			};
		}
		return maxGroundspeed;
	}
}