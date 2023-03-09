package fr.estia.pandora.analyses;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Record;


//cette classe retourne le temps de vol jusqu'a atteindre le 80% de l'altitude max	
public class Reaching80PercentMaxDistance {
	public static String getFormattedDuration(double timestamp) {
	    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

	    Date date = new Date((long) timestamp * 1000);
	    String formattedDate = formatter.format(date);
	    
	    return formattedDate;
	}
	
	public static String average(Flight flight) {
		ArrayList<Record> flightRecords = flight.getRecords();
		
		String timestampFomatted = null;

		
		return timestampFomatted;
		
	}
}
