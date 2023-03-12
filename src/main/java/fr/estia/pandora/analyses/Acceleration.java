package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;
import fr.estia.pandora.model.Metadata;

// F(N) = P(W)/v(m/s) et a(m/s2) = F(N) / m(kg)

public class Acceleration {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double Newtons = 0;
		double aircraftmass = 0;
		double accelerationSum = 0;
		
		aircraftmass = flight.getAircraftMass() ;

		for (int i = 0; i<flightRecords.size(); i++) {
			
			Newtons = flightRecords.get(i).getEnginePower() / flightRecords.get(i).getAir_speed();
			
			
			accelerationSum += Newtons / aircraftmass ;
		};
		
		return accelerationSum / flightRecords.size();
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxAcceleration = 0 ;
		
		double Newtons = 0;
		double aircraftmass = 0;
		double accelerationSum = 0;
		ArrayList<Double> accelerationList = new ArrayList<Double>();
		
		aircraftmass = flight.getAircraftMass() ;
		
		for (int i = 0; i<flightRecords.size(); i++) {
			Newtons = flightRecords.get(i).getEnginePower() / flightRecords.get(i).getAir_speed();
			accelerationSum = Newtons / aircraftmass ;
			
			accelerationList.add(accelerationSum) ;
			
			if(maxAcceleration < accelerationSum) {
				maxAcceleration = accelerationSum;
			};
		}
		
//		System.out.println(accelerationList) ;
		/*// print the last item of the list
		int lastIndex = accelerationList.size() - 1;
		double lastItem = accelerationList.get(lastIndex);
		System.out.println("Last item: " + lastItem);*/
		return maxAcceleration;
	}
	
	public static double maxInG(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxAcceleration = 0 ;
		
		double Newtons = 0;
		double aircraftmass = 0;
		double accelerationSum = 0;
		ArrayList<Double> accelerationList = new ArrayList<Double>();
		
		aircraftmass = flight.getAircraftMass() ;
		
		for (int i = 0; i<flightRecords.size(); i++) {
			Newtons = flightRecords.get(i).getEnginePower() / flightRecords.get(i).getAir_speed();
			accelerationSum = Newtons / aircraftmass ;
			
			accelerationList.add(accelerationSum) ;
			
			if(maxAcceleration < accelerationSum) {
				maxAcceleration = accelerationSum;
			};
		}
		return maxAcceleration/9.80665;
	}
	

}

