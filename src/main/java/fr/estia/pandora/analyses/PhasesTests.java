package fr.estia.pandora.analyses;

import java.util.ArrayList;
import java.util.List;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.FlightPhase;
import fr.estia.pandora.model.Record;

public class PhasesTests {
	private Flight flight;
    public static double avgAirSpeedTakeOff(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        double airspeed = flightRecords.get(0).getAir_speed();
        FlightPhase takeOff = Phases.getTakeOffData(flight);
        
        int i_start = takeOff.startIndex;
        int i_end = takeOff.endIndex;
        for (int i = i_start; i < i_end; i++) {
            airspeed += flightRecords.get(i).getAir_speed();
        }
        return airspeed/(i_end-i_start);
    }
    
    public static double avgAirSpeedCruise(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        double airspeed = 0;
        FlightPhase cruise = Phases.getCruiseData(flight);
        
        int i_start = cruise.startIndex;
        int i_end = cruise.endIndex;
        for (int i = i_start; i < i_end; i++) {
            airspeed += flightRecords.get(i).getAir_speed();
        }
        return airspeed/(i_end-i_start);
    }
    
    public static double avgAirSpeedLanding(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        double airspeed = flightRecords.get(0).getAir_speed();
        FlightPhase landing = Phases.getLandingData(flight);
        
        int i_start = landing.startIndex;
        int i_end = landing.endIndex;
        for (int i = i_start; i < i_end; i++) {
            airspeed += flightRecords.get(i).getAir_speed();
        }
        return airspeed/(i_end-i_start);
    }
    
    
    public static double maxAirSpeedTakeOff(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        double airspeed = flightRecords.get(0).getAir_speed();
        FlightPhase takeOff = Phases.getTakeOffData(flight);
        
        int i_start = 0;
        int i_end = 10;
        for (int i = i_start; i < i_end; i++) {
            airspeed += flightRecords.get(i).getAir_speed();
        }
        
        
        return airspeed/(i_end-i_start);
    }
    

}
