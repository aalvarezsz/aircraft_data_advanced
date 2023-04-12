package fr.estia.pandora.analyses;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Position;
import fr.estia.pandora.model.Record;
import fr.estia.pandora.model.Utils;
import fr.estia.pandora.analyses.Wind;

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

		double altitudeAtMaxSpeed = flightRecords.get(0).getAltitude();
		int maxSpeedIndex = 0;
		double maxSpeed = 0;

		for (int i = 0; i < flightRecords.size(); i++) {
			if (flightRecords.get(i).getAir_speed() >= maxSpeed) {
				maxSpeed = flightRecords.get(i).getAir_speed();
				maxSpeedIndex = i;
			}
		}

		// Get the altitude at the maximum airspeed index
		altitudeAtMaxSpeed = flightRecords.get(maxSpeedIndex).getAltitude();

		return altitudeAtMaxSpeed;
	}

	public static double fastWindAltitude(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();

		double altitudeAtMaxWindspeed = flightRecords.get(0).getAltitude();
		int maxSpeedIndex = 0;
		double maxSpeed = 0;


		double windSpeed = 0;
		int nbOfPositions = 0;
		double groundSpeed;

		if (flightRecords.size() <= 1) {
			return 0;
		}

		for (int i = 0; i < flightRecords.size()-1; i++) {

			Position position_0 = new Position(flightRecords.get(i).getLatitude(), flightRecords.get(i).getLongitude(), flightRecords.get(i).getAltitude());
			Position position_1 = new Position(flightRecords.get(i+1).getLatitude(), flightRecords.get(i+1).getLongitude(), flightRecords.get(i+1).getAltitude());
			double time_0 = flightRecords.get(i).getTimestamp();
			double time_1 = flightRecords.get(i+1).getTimestamp();
			double dTime = time_1 - time_0;
			double dDistance = Utils.ComputeDistance(position_0, position_1);
			groundSpeed = dDistance / dTime;
			double airSpeed = flightRecords.get(i+1).getAir_speed();
			windSpeed = groundSpeed - airSpeed;
			nbOfPositions++;

			if (windSpeed >= maxSpeed) {
				maxSpeed = windSpeed;
				maxSpeedIndex = i;
			}
		}

		// Get the altitude at the maximum windspeed index
		altitudeAtMaxWindspeed = flightRecords.get(maxSpeedIndex+1).getAltitude();

		return altitudeAtMaxWindspeed;
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

