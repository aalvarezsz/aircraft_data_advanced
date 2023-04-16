package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.FlightPhase;
import fr.estia.pandora.model.Record;
import fr.estia.pandora.model.Utils;

public class PhaseAnalysis {
	/* Replace XXXXXX by Phase (TakeOff, Cruise, Landing)
	 * avgAirSpeedXXXXXX 		--> Done
	 * maxAirSpeedXXXXXX 		--> Done
	 * avgEnginePowerXXXXXX 	--> Done
	 * maxEnginePowerXXXXXX	 	--> Done
	 * flightDistanceXXXXXX		--> Done
	 * avgAccelerationXXXXXX	--> Done
	 * maxAccelerationXXXXXX	--> Done
	 * windSpeedXXXXXX 			--> Done
	 * 
	 *
	 * mostPowerPhase
	 * mostStressPhase
	 * mostAccelPhase
	 *       
	 *
	 */
	
    /* AVERAGE AIR SPEED IN ALL THREE PHASES */
    public static double avgAirSpeedTakeOff(Flight flight) {
        double airspeed = 0;
        ArrayList<Record> flightRecords = flight.getRecords();

        FlightPhase takeOff = Phases.getTakeOffData(flight);
        if(takeOff == null) return airspeed;

        int i_start = takeOff.startIndex;
        int i_end = takeOff.endIndex;

        for (int i = takeOff.startIndex; i < takeOff.endIndex; i++) airspeed += flightRecords.get(i).getAir_speed();

        return airspeed/(i_end-i_start);
    }
    
    public static double avgAirSpeedCruise(Flight flight) {
        double airspeed = 0;
        ArrayList<Record> flightRecords = flight.getRecords();

        FlightPhase cruise = Phases.getCruiseData(flight);
        if(cruise == null) return airspeed;
        
        int i_start = cruise.startIndex;
        int i_end = cruise.endIndex;

        for (int i = i_start; i < i_end; i++) airspeed += flightRecords.get(i).getAir_speed();

        return airspeed/(i_end-i_start);
    }
    
    public static double avgAirSpeedLanding(Flight flight) {
        double airspeed = 0;
        ArrayList<Record> flightRecords = flight.getRecords();

        FlightPhase landing = Phases.getLandingData(flight);
        if(landing == null) return airspeed;
        
        int i_start = landing.startIndex;
        int i_end = landing.endIndex;

        for (int i = i_start; i < i_end; i++) airspeed += flightRecords.get(i).getAir_speed();

        return airspeed/(i_end-i_start);
    }
    
    
    /* MAX AIR SPEED IN ALL THREE PHASES */
    public static double maxAirSpeedTakeOff(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        double airspeed = flightRecords.get(0).getAir_speed();

        FlightPhase takeOff = Phases.getTakeOffData(flight);
        if(takeOff == null) return 0;
        
        int i_start = takeOff.startIndex;
        int i_end = takeOff.endIndex;

        for (int i = i_start; i < i_end; i++) {
        	if (flightRecords.get(i).getAir_speed() > airspeed) {
        		airspeed = flightRecords.get(i).getAir_speed();
        	}
        }
        return airspeed;
    }
    
    public static double maxAirSpeedCruise(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        double airspeed = 0;

        FlightPhase cruise = Phases.getCruiseData(flight);
        if(cruise == null) return 0;
        
        int i_start = cruise.startIndex;
        int i_end = cruise.endIndex;

        for (int i = i_start; i < i_end; i++) {
        	if (flightRecords.get(i).getAir_speed() > airspeed) {
        		airspeed = flightRecords.get(i).getAir_speed();
        	}
        }
        return airspeed;
    }

    public static double maxAirSpeedLanding(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        double airspeed = flightRecords.get(0).getAir_speed();

        FlightPhase landing = Phases.getLandingData(flight);
        if(landing == null) return 0;
        
        int i_start = landing.startIndex;
        int i_end = landing.endIndex;

        for (int i = i_start; i < i_end; i++) {
        	if (flightRecords.get(i).getAir_speed() > airspeed) {
        		airspeed = flightRecords.get(i).getAir_speed();
        	}
        }
        return airspeed;
    }

    
    /* AVG ENGINE POWER IN ALL THREE PHASES */  
    public static double avgEnginePowerTakeOff(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        double power = flightRecords.get(0).getAir_speed();

        FlightPhase takeOff = Phases.getTakeOffData(flight);
        if(takeOff == null) return 0;
        
        int i_start = takeOff.startIndex;
        int i_end = takeOff.endIndex;
        for (int i = i_start; i < i_end; i++) {
            power += flightRecords.get(i).getEnginePower();
        }
        return power/(i_start-i_end);
    }
    
    public static double avgEnginePowerCruise(Flight flight) {
        double power = 0;
        ArrayList<Record> flightRecords = flight.getRecords();

        FlightPhase cruise = Phases.getCruiseData(flight);
        if(cruise == null) return 0;
        
        int i_start = cruise.startIndex;
        int i_end = cruise.endIndex;
        for (int i = i_start; i < i_end; i++) {
        	power += flightRecords.get(i).getEnginePower();
        }
        return power/(i_start-i_end);
    }
    
    public static double avgEnginePowerLanding(Flight flight) {
        double power = 0;
        ArrayList<Record> flightRecords = flight.getRecords();

        FlightPhase landing = Phases.getLandingData(flight);
        if(landing == null) return 0;
        
        int i_start = landing.startIndex;
        int i_end = landing.endIndex;
        for (int i = i_start; i < i_end; i++) {
            power += flightRecords.get(i).getEnginePower();
        }
        return power/(i_start-i_end);
    }


    /* MAX ENGINE POWER IN ALL THREE PHASES */
    public static double maxEnginePowerTakeOff(Flight flight) {
        double power = 0;
        ArrayList<Record> flightRecords = flight.getRecords();

        FlightPhase takeOff = Phases.getTakeOffData(flight);
        if(takeOff == null) return 0;
        
        int i_start = takeOff.startIndex;
        int i_end = takeOff.endIndex;
        for (int i = i_start; i < i_end; i++) {
        	if (flightRecords.get(i).getAir_speed() > power) {
        		power = flightRecords.get(i).getAir_speed();
        	}
            
        }
        return power;
    }
    
    public static double maxEnginePowerCruise(Flight flight) {
        double power = 0;
        ArrayList<Record> flightRecords = flight.getRecords();

        FlightPhase cruise = Phases.getCruiseData(flight);
        if(cruise == null) return 0;
        
        int i_start = cruise.startIndex;
        int i_end = cruise.endIndex;
        for (int i = i_start; i < i_end; i++) {
        	if (flightRecords.get(i).getAir_speed() > power) {
        		power = flightRecords.get(i).getAir_speed();
        	}        }
        return power;
    }
    
    public static double maxEnginePowerLanding(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        double power = flightRecords.get(0).getAir_speed();

        FlightPhase landing = Phases.getLandingData(flight);
        if(landing == null) return 0;
        
        int i_start = landing.startIndex;
        int i_end = landing.endIndex;
        for (int i = i_start; i < i_end; i++) {
        	if (flightRecords.get(i).getAir_speed() > power) {
        		power = flightRecords.get(i).getAir_speed();
        	}        }
        return power;
    }


    /* FLIGHT DISTANCE IN ALL THREE PHASES */
    public static double flightDistanceTakeOff(Flight flight) {
        FlightPhase takeOff = Phases.getTakeOffData(flight);

        if(takeOff == null) return 0;
        else return Utils.ComputeDistanceBetween(flight, takeOff.startIndex, takeOff.endIndex) / 1000;
    }

    public static double flightDistanceCruise(Flight flight) {
        FlightPhase cruise = Phases.getCruiseData(flight);

        if(cruise == null) return 0;
        else return Utils.ComputeDistanceBetween(flight, cruise.startIndex, cruise.endIndex) / 1000;
    }

    public static double flightDistanceLanding(Flight flight) {
        FlightPhase landing = Phases.getLandingData(flight);

        if(landing == null) return 0;
        else return Utils.ComputeDistanceBetween(flight, landing.startIndex, landing.endIndex) / 1000;
    }


    /* AVERAGE ACCELERATION IN ALL THREE PHASES */
    public static double avgAccelerationTakeOff(Flight flight) {
        FlightPhase takeOff = Phases.getTakeOffData(flight);

        if(takeOff == null) return 0;
        else return Acceleration.average(flight, takeOff.startIndex, takeOff.endIndex);
    }

    public static double avgAccelerationCruise(Flight flight) {
        FlightPhase cruise = Phases.getCruiseData(flight);

        if(cruise == null) return 0;
        else return Acceleration.average(flight, cruise.startIndex, cruise.endIndex);
    }

    public static double avgAccelerationLanding(Flight flight) {
        FlightPhase landing = Phases.getLandingData(flight);

        if(landing == null) return 0;
        else return Acceleration.average(flight, landing.startIndex, landing.endIndex);
    }


    /* MAX ACCELERATION IN ALL THREE PHASES */
    public static double maxAccelerationTakeOff(Flight flight) {
        FlightPhase takeOff = Phases.getTakeOffData(flight);

        if(takeOff == null) return 0;
        else return Acceleration.max(flight, takeOff.startIndex, takeOff.endIndex);
    }

    public static double maxAccelerationCruise(Flight flight) {
        FlightPhase cruise = Phases.getCruiseData(flight);

        if(cruise == null) return 0;
        else return Acceleration.max(flight, cruise.startIndex, cruise.endIndex);
    }

    public static double maxAccelerationLanding(Flight flight) {
        FlightPhase landing = Phases.getLandingData(flight);

        if(landing == null) return 0;
        else return Acceleration.max(flight, landing.startIndex, landing.endIndex);
    }


    /* AVERAGE WIND SPEED IN ALL THREE PHASES */
    public static double avgWindSpeedTakeOff(Flight flight) {
        FlightPhase takeOff = Phases.getTakeOffData(flight);

        if(takeOff == null) return 0;
        else return Wind.speed(flight, takeOff.startIndex, takeOff.endIndex);
    }

    public static double avgWindSpeedCruise(Flight flight) {
        FlightPhase cruise = Phases.getCruiseData(flight);

        if(cruise == null) return 0;
        else return Wind.speed(flight, cruise.startIndex, cruise.endIndex);
    }

    public static double avgWindSpeedLanding(Flight flight) {
        FlightPhase landing = Phases.getLandingData(flight);

        if(landing == null) return 0;
        else return Wind.speed(flight, landing.startIndex, landing.endIndex);
    }
    
    
}
