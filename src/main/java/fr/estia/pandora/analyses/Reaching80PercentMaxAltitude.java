package fr.estia.pandora.analyses;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;


//cette classe retourne le temps de vol jusqu'a atteindre le 80% de l'altitude max	
public class Reaching80PercentMaxAltitude {
	public static String getFormattedDuration(double timestamp) {
	    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

	    Date date = new Date((long) timestamp * 1000);
	    String formattedDate = formatter.format(date);
	    
	    return formattedDate;
	}
	
	public static String average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		double maxaltitude = 0;
		
		double altitude = 0;
		double Taltitude = 0;
		double timestamp = 0;
		String timestampFomatted;
		//le timestamp du début du vol
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

		//System.out.println("Le timestamp est"+timestamp);
		timestampFomatted = getFormattedDuration(timestamp);
		//falta formatear el tiempo
		
		return timestampFomatted;
		
	}
}
