package fr.estia.pandora.analyses;

import java.util.Calendar;
import java.util.Date;

import fr.estia.pandora.model.Flight;

public class FlightDuration {
	
	public static double compute(Flight flight) {
		double startTime = flight.getRecords().get( 0 ).getTimestamp();
		double endTime = flight.getRecords().get( flight.getRecords().size() -1 ) .getTimestamp() ;
		
		return endTime - startTime;
	}
	
	public static String getFormattedDuration(double timestamp) {
		Date date = new Date((long) timestamp);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		
		return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
	}

}
