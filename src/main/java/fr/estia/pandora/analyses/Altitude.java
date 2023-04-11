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

	public static double maxAccel(Flight flight) {
		double maxAcceleration = 0;
		double altitudeForMaxAccel = 0;

		if(flight.getRecords().size() <= 1) return 0;

		for(int i = 0; i < flight.getRecords().size() - 2; i++) {
			Position position_0 = new Position(flight.getRecords().get(i).getLatitude(), flight.getRecords().get(i).getLongitude(), flight.getRecords().get(i).getAltitude());
			Position position_1 = new Position(flight.getRecords().get(i+1).getLatitude(), flight.getRecords().get(i+1).getLongitude(), flight.getRecords().get(i+1).getAltitude());
			Position position_2 = new Position(flight.getRecords().get(i+2).getLatitude(), flight.getRecords().get(i+2).getLongitude(), flight.getRecords().get(i+2).getAltitude());
			double time_0 = flight.getRecords().get(i).getTimestamp();
			double time_1 = flight.getRecords().get(i+1).getTimestamp();
			double time_2 = flight.getRecords().get(i+2).getTimestamp();

			double dTime_0 = time_1 - time_0;
			double dTime_1 = time_2 - time_1;

			double dDistance_0 = Utils.ComputeDistance(position_0, position_1);
			double dDistance_1 = Utils.ComputeDistance(position_1, position_2);

			double speed_0 = dDistance_0 / dTime_0;
			double speed_1 = dDistance_1 / dTime_1;
			double acceleration = (speed_1 - speed_0) / (dTime_1);

			if (Math.abs(maxAcceleration) < Math.abs(acceleration)) {
				maxAcceleration = acceleration;
				altitudeForMaxAccel = flight.getRecords().get(i+2).getAltitude();
			}
		}

		return altitudeForMaxAccel;
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

