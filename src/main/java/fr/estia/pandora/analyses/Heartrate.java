package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

public class Heartrate {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double heartrateSum = 0;
		
		for (int i = 0; i<flightRecords.size(); i++) heartrateSum += flightRecords.get(i).getHeart_rate();
		
		return heartrateSum / flightRecords.size();
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxHeartrate = flightRecords.get(0).getHeart_rate();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(maxHeartrate < flightRecords.get(i).getHeart_rate()) {
				maxHeartrate = flightRecords.get(i).getHeart_rate();
			};
		}
		return maxHeartrate;
	}
	
	public static double min(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double minHeartrate = flightRecords.get(0).getHeart_rate();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(minHeartrate > flightRecords.get(i).getHeart_rate()) {
				minHeartrate = flightRecords.get(i).getHeart_rate();
			};
		}
		return minHeartrate;
	}

}

