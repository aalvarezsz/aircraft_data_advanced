package fr.estia.pandora.analyses;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.FlightPhase;
import fr.estia.pandora.model.Record;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


//Les phases de vols sont: takeoff, cruise, landing
//les fonctions: takeOff
//Take Off Phase Detection >> CLI option: -o takeOff

public class Phases {

    public static FlightPhase takeOff(Flight flight) {
        List<FlightPhase> plateaus = findPlateaux(flight);

        double takeOffDebutTimestamp = flight.getRecords().get(0).getTimestamp();
        double takeOffEndTimestamp = plateaus.get(0).startTimestamp;

        return new FlightPhase(takeOffDebutTimestamp, takeOffEndTimestamp);
    }

    public static FlightPhase cruise(Flight flight) {
        List<FlightPhase> plateaus = findPlateaux(flight);

        double cruiseDebutTimestamp = plateaus.get(0).startTimestamp;
        double cruiseEndTimestamp = plateaus.get(plateaus.size() - 1).endTimestamp;

        return new FlightPhase(cruiseDebutTimestamp, cruiseEndTimestamp);
    }

    public static FlightPhase landing(Flight flight) {
        List<FlightPhase> plateaus = findPlateaux(flight);

        double landingDebutTimestamp = plateaus.get(plateaus.size() - 1).endTimestamp;
        double landingEndTimestamp = flight.getRecords().get(flight.getRecords().size() - 1).getTimestamp();;

        return new FlightPhase(landingDebutTimestamp, landingEndTimestamp);
    }

    public static List<FlightPhase> findPlateaux(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        List<Float> yawDeltaList = getYawDeltaList(flightRecords);

        // Plateau start and finish indexes
        List<Integer> startIndexes = new ArrayList<>();
        List<Integer> endIndexes = new ArrayList<>();

        // Get plateau beginning indexes
        int stableCount = 0;

        for(int i=0; i<yawDeltaList.size(); i++) {
            float delta = yawDeltaList.get(i);

            if(delta != -1 && delta<1) stableCount++;
            else {
                if(stableCount>=10) endIndexes.add(i);
                stableCount = 0;
            }

            if(stableCount == 10) {
                int plateauIndex = i - stableCount + 1;
                startIndexes.add(plateauIndex);
            }
        }

        if(startIndexes.size() > endIndexes.size()) endIndexes.add(yawDeltaList.size() - 1);

        for(int i=0; i<startIndexes.size();) {
            double startTime = flightRecords.get(startIndexes.get(i)).getTimestamp();
            double endTime = flightRecords.get(endIndexes.get(i)).getTimestamp();

            if(endTime-startTime <= 60) {
                startIndexes.remove(i);
                endIndexes.remove(i);
            } else { i++; }

            // System.out.println("DEBUG " + i + "/" + startIndexes.size());
        }

        List<FlightPhase> plateaus = new ArrayList<>();
        for(int i=0; i<startIndexes.size(); i++) {
            double startTime = flightRecords.get(startIndexes.get(i)).getTimestamp();
            double endTime = flightRecords.get(endIndexes.get(i)).getTimestamp();

            FlightPhase plateau = new FlightPhase(startTime,endTime);
            plateaus.add(plateau);
        }

        return plateaus;
    }

    public static List<Float> getYawDeltaList(ArrayList<Record> flightRecords) {
        List<Float> deltaList = new ArrayList<>();

        for(int i=1; i<flightRecords.size(); i++) {
            float delta = flightRecords.get(i).getYaw() - flightRecords.get(i-1).getYaw();
            deltaList.add(delta);
        }

        return deltaList;
    }
}

