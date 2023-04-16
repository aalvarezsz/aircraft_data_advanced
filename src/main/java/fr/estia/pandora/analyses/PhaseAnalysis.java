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

        return AirSpeed.average(flight, takeOff.startIndex, takeOff.endIndex);
    }
    
    public static double avgAirSpeedCruise(Flight flight) {
        double airspeed = 0;
        ArrayList<Record> flightRecords = flight.getRecords();

        FlightPhase cruise = Phases.getCruiseData(flight);
        if(cruise == null) return airspeed;

        return AirSpeed.average(flight, cruise.startIndex, cruise.endIndex);
    }
    
    public static double avgAirSpeedLanding(Flight flight) {
        double airspeed = 0;
        ArrayList<Record> flightRecords = flight.getRecords();

        FlightPhase landing = Phases.getLandingData(flight);
        if(landing == null) return airspeed;

        return AirSpeed.average(flight, landing.startIndex, landing.endIndex);
    }
    
    
    /* MAX AIR SPEED IN ALL THREE PHASES */
    public static double maxAirSpeedTakeOff(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        double airspeed = flightRecords.get(0).getAir_speed();

        FlightPhase takeOff = Phases.getTakeOffData(flight);
        if(takeOff == null) return 0;

        return AirSpeed.max(flight, takeOff.startIndex, takeOff.endIndex);
    }
    
    public static double maxAirSpeedCruise(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        double airspeed = 0;

        FlightPhase cruise = Phases.getCruiseData(flight);
        if(cruise == null) return 0;

        return AirSpeed.max(flight, cruise.startIndex, cruise.endIndex);
    }

    public static double maxAirSpeedLanding(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        double airspeed = flightRecords.get(0).getAir_speed();

        FlightPhase landing = Phases.getLandingData(flight);
        if(landing == null) return 0;

        return AirSpeed.max(flight, landing.startIndex, landing.endIndex);
    }

    
    /* AVG ENGINE POWER IN ALL THREE PHASES */  
    public static double avgEnginePowerTakeOff(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        double power = flightRecords.get(0).getAir_speed();

        FlightPhase takeOff = Phases.getTakeOffData(flight);
        if(takeOff == null) return 0;
        
        return EnginePower.average(flight, takeOff.startIndex, takeOff.endIndex);
    }
    
    public static double avgEnginePowerCruise(Flight flight) {
        double power = 0;
        ArrayList<Record> flightRecords = flight.getRecords();

        FlightPhase cruise = Phases.getCruiseData(flight);
        if(cruise == null) return 0;

        return EnginePower.average(flight, cruise.startIndex, cruise.endIndex);
    }
    
    public static double avgEnginePowerLanding(Flight flight) {
        double power = 0;
        ArrayList<Record> flightRecords = flight.getRecords();

        FlightPhase landing = Phases.getLandingData(flight);
        if(landing == null) return 0;

        return EnginePower.average(flight, landing.startIndex, landing.endIndex);
    }


    /* MAX ENGINE POWER IN ALL THREE PHASES */
    public static double maxEnginePowerTakeOff(Flight flight) {
        double power = 0;
        ArrayList<Record> flightRecords = flight.getRecords();

        FlightPhase takeOff = Phases.getTakeOffData(flight);
        if(takeOff == null) return 0;

        return EnginePower.max(flight, takeOff.startIndex, takeOff.endIndex);
    }
    
    public static double maxEnginePowerCruise(Flight flight) {
        double power = 0;
        ArrayList<Record> flightRecords = flight.getRecords();

        FlightPhase cruise = Phases.getCruiseData(flight);
        if(cruise == null) return 0;

        return EnginePower.max(flight, cruise.startIndex, cruise.endIndex);
    }
    
    public static double maxEnginePowerLanding(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        double power = flightRecords.get(0).getAir_speed();

        FlightPhase landing = Phases.getLandingData(flight);
        if(landing == null) return 0;

        return EnginePower.max(flight, landing.startIndex, landing.endIndex);
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
