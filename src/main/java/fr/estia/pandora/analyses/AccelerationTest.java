package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;

// F(N) = P(W)/v(m/s) et a(m/s2) = F(N) / m(kg)

public class AccelerationTest {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double Newtons = 0;
		double aircraftmass = 0;
		
		aircraftmass = flight.getAircraftMass() ;
		
		for (int i = 0; i<flightRecords.size(); i++) {
			
			Newtons = flightRecords.get(i).getEnginePower() / flightRecords.get(i).getAir_speed();
			
			
			Newtons += Newtons ;
		};
		double NewtonsMean = Newtons / flightRecords.size() ;
		
		
		return NewtonsMean / aircraftmass;
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();	
		double Newtons = 0;
		double aircraftmass = 0;
		double maxNewtons = 0;
		
		aircraftmass = flight.getAircraftMass() ;
		
		for (int i = 0; i<flightRecords.size(); i++) {
			Newtons = flightRecords.get(i).getEnginePower() / flightRecords.get(i).getAir_speed();
			
			if(maxNewtons < Newtons) {
				maxNewtons = Newtons;
			};
		}
		return maxNewtons / aircraftmass;
	}
	
	public static double maxInG(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();	
		double Newtons = 0;
		double aircraftmass = 0;
		double maxNewtons = 0;
		
		aircraftmass = flight.getAircraftMass() ;
		
		for (int i = 0; i<flightRecords.size(); i++) {
			Newtons = flightRecords.get(i).getEnginePower() / flightRecords.get(i).getAir_speed();
			
			if(maxNewtons < Newtons) {
				maxNewtons = Newtons;
			};
		}
		return (maxNewtons / aircraftmass) / 9.80665;
	}
	

}

