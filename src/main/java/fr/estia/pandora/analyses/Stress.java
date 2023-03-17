package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

public class Stress {

	
	public static String attack(Flight flight) {
		boolean test = false;
		ArrayList<Record> flightRecords = flight.getRecords();
		for (int i = 0; i<flightRecords.size()-1; i++) {
			double n0 = flightRecords.get(i).getHeart_rate();
			double n1 = flightRecords.get(i+1).getHeart_rate();
			if ((n1-n0) > 10 || (n1-n0) < -10) {
				System.out.println("n1 est"+n1+"n0 est"+n0);

				test = true;
			}
		}
			
		
		if (test) {
			return "y";
		} else {
			return "n";
		}
		
		
	}
}
