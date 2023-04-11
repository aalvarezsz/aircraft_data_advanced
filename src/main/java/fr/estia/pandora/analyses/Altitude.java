package fr.estia.pandora.analyses;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Position;
import fr.estia.pandora.model.Record;
import fr.estia.pandora.model.Utils;

public class Altitude {
	public static double average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double altitudeSum = 0;
		
		for (int i = 0; i<flightRecords.size(); i++) altitudeSum += flightRecords.get(i).getAltitude();
		
		return altitudeSum / flightRecords.size();
	}
	
	public static double max(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxAltitude = flightRecords.get(0).getAltitude();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(maxAltitude < flightRecords.get(i).getAltitude()) {
				maxAltitude = flightRecords.get(i).getAltitude();
			};
		}
		return maxAltitude;
	}
	
	public static double min(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double minAltitude = flightRecords.get(0).getAltitude();
		
		for (int i = 1; i<flightRecords.size(); i++) {
			if(minAltitude > flightRecords.get(i).getAltitude()) {
				minAltitude = flightRecords.get(i).getAltitude();
			};
		}
		return minAltitude;
	}

	public static double fastJetAltitude(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();

		ArrayList<Double> altitudeList = new ArrayList<Double>();
		ArrayList<Double> airspeedList = new ArrayList<Double>();

		for (int i = 0; i<flightRecords.size(); i++) {
			altitudeList.add(flightRecords.get(i).getAltitude());
			airspeedList.add(flightRecords.get(i).getAir_speed());
		}

		double altitudeAtMaxSpeed = flightRecords.get(0).getAltitude();

		// Find the index of the maximum airspeed
		int maxSpeedIndex = 0;
		double maxSpeed = Double.MIN_VALUE;
		for (int i = 0; i < airspeedList.size(); i++) {
			if (airspeedList.get(i) > maxSpeed) {
				maxSpeed = airspeedList.get(i);
				maxSpeedIndex = i;
			}
		}

		// Get the altitude at the maximum airspeed index
		altitudeAtMaxSpeed = altitudeList.get(maxSpeedIndex);

		return altitudeAtMaxSpeed;
	}



		public static class Reaching80PercentMaxAltitude {
		public String getFormattedDuration(double timestamp) {
		    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

		    Date date = new Date((long) timestamp * 1000);
		    String formattedDate = formatter.format(date);
		    
		    return formattedDate;
		}
		
		public static double average(Flight flight) {
			ArrayList<Record> flightRecords = flight.getRecords();
			double maxaltitude = 0;
			
			double altitude = 0;
			double Taltitude = 0;
			double timestamp = 0;
			//le timestamp du debut du vol
			double startTime = flight.getRecords().get( 0 ).getTimestamp();
			
			//calculer max altitude
			for (int i = 0; i<flightRecords.size(); i++) {
				if (flightRecords.get(i).getAltitude()>maxaltitude) {
					maxaltitude = flightRecords.get(i).getAltitude();
				}
			}
			//calculer le timestramp du 80% de max altitude
			for (int i = 0; i<flightRecords.size(); i++) {
				altitude = flightRecords.get(i).getAltitude();
				if ((altitude/maxaltitude)>0.8) {
					Taltitude = flightRecords.get(i).getTimestamp();				
					break;
				}
			}
			//System.out.println("L'altitude max est"+maxaltitude);
			timestamp = Taltitude-startTime;
			return timestamp/60;
			
			
			
		}
	}
	
	
	
	
	
	
	

}

