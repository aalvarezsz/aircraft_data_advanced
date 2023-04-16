package fr.estia.pandora.analyses;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.FlightPhase;
import fr.estia.pandora.model.Record;

import java.util.ArrayList;
import java.util.List;


public class Phases {
    private static final String error_message = "not detected";

    public static String takeOff(Flight flight) {
        FlightPhase takeOff = getTakeOffData(flight);

        if(takeOff != null) return takeOff.toString();
        else return error_message;
    }

    public static String cruise(Flight flight) {
        FlightPhase cruise = getCruiseData(flight);

        if(cruise != null) return cruise.toString();
        else return error_message;
    }

    public static String landing(Flight flight) {
        FlightPhase landing = getLandingData(flight);

        if(landing != null) return landing.toString();
        else return error_message;
    }

    public static FlightPhase getTakeOffData(Flight flight) {
        List<FlightPhase> plateaus = findPlateaux(flight);
        if(plateaus.size() == 0) return null;

        double takeOffDebutTimestamp = flight.getRecords().get(0).getTimestamp();
        double takeOffEndTimestamp = plateaus.get(0).startTimestamp;

        int takeOffDebutIndex = 0;
        int takeOffEndIndex = plateaus.get(0).startIndex;

        return new FlightPhase(takeOffDebutTimestamp, takeOffEndTimestamp, takeOffDebutIndex, takeOffEndIndex);
    }

    public static FlightPhase getCruiseData(Flight flight) {
        List<FlightPhase> plateaus = findPlateaux(flight);
        if(plateaus.size() == 0) return null;

        double cruiseDebutTimestamp = plateaus.get(0).startTimestamp;
        double cruiseEndTimestamp = plateaus.get(plateaus.size() - 1).endTimestamp;

        int cruiseDebutIndex = plateaus.get(0).startIndex;
        int cruiseEndIndex = plateaus.get(plateaus.size() - 1).endIndex;

        return new FlightPhase(cruiseDebutTimestamp, cruiseEndTimestamp, cruiseDebutIndex, cruiseEndIndex);
    }

    public static FlightPhase getLandingData(Flight flight) {
        List<FlightPhase> plateaus = findPlateaux(flight);
        if(plateaus.size() == 0) return null;

        double landingDebutTimestamp = plateaus.get(plateaus.size() - 1).endTimestamp;
        double landingEndTimestamp = flight.getRecords().get(flight.getRecords().size() - 1).getTimestamp();

        int landingDebutIndex = plateaus.get(plateaus.size() - 1).endIndex;
        int landingEndIndex = flight.getRecords().size() - 1;

        return new FlightPhase(landingDebutTimestamp, landingEndTimestamp, landingDebutIndex, landingEndIndex);
    }

    public static List<FlightPhase> findPlateaux(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        List<Float> yawDeltaList = getYawDeltaList(flightRecords);

        // Plateaus' start and finish indexes
        List<Integer> startIndexes = new ArrayList<>();
        List<Integer> endIndexes = new ArrayList<>();

        // Get plateaus' beginning and end indexes
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

        // Filter for plateaus that last > 60 seconds
        for(int i=0; i<startIndexes.size();) {
            double startTime = flightRecords.get(startIndexes.get(i)).getTimestamp();
            double endTime = flightRecords.get(endIndexes.get(i)).getTimestamp();

            if(endTime-startTime <= 60) {
                startIndexes.remove(i);
                endIndexes.remove(i);
            } else { i++; }

            // System.out.println("DEBUG " + i + "/" + startIndexes.size());
        }

        // Response data formatting
        List<FlightPhase> plateaus = new ArrayList<>();
        for(int i=0; i<startIndexes.size(); i++) {
            double startTime = flightRecords.get(startIndexes.get(i)).getTimestamp();
            double endTime = flightRecords.get(endIndexes.get(i)).getTimestamp();

            FlightPhase plateau = new FlightPhase(startTime,endTime, startIndexes.get(i), endIndexes.get(i));
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

