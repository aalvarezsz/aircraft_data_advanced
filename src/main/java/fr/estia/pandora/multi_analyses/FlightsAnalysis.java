package fr.estia.pandora.multi_analyses;

import fr.estia.pandora.analyses.FlightAnalysis;
import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Utils;

import java.util.List;

public class FlightsAnalysis {
    public static String totalCumulativeDuration(List<Flight> flights) {
        double totalDuration = 0;
        for(Flight flight: flights) totalDuration += FlightAnalysis.getRawDuration(flight);

        return Utils.TimestampDurationToString(totalDuration);
    }

    public static double totalCumulativeFlightDistance(List<Flight> flights) {
        double totalDistance = 0;
        for(Flight flight: flights) totalDistance += FlightAnalysis.computeFullDistance(flight) / 1000;

        return totalDistance;
    }
}
