package fr.estia.pandora.analyses;

import java.util.ArrayList;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.Position;
import fr.estia.pandora.model.Record;
import fr.estia.pandora.model.Utils;
import fr.estia.pandora.analyses.AirSpeed;
import fr.estia.pandora.model.Flight;



public class Wind {
    private Flight flight;
    public static double speed(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();
        double windSpeed = 0;
        int nbOfPositions = 0;
        double groundSpeed;

        if (flightRecords.size() <= 1) {
            return 0;
        }

        for (int i = 0; i < flightRecords.size() - 1; i++) {
            Position position_0 = new Position(flightRecords.get(i).getLatitude(), flightRecords.get(i).getLongitude(), flightRecords.get(i).getAltitude());
            Position position_1 = new Position(flightRecords.get(i+1).getLatitude(), flightRecords.get(i+1).getLongitude(), flightRecords.get(i+1).getAltitude());
            double time_0 = flightRecords.get(i).getTimestamp();
            double time_1 = flightRecords.get(i+1).getTimestamp();
            double dTime = time_1 - time_0;
            double dDistance = Utils.ComputeDistance(position_0, position_1);
            groundSpeed = dDistance / dTime;
            double airSpeed = flightRecords.get(i+1).getAir_speed();
            windSpeed += groundSpeed - airSpeed;
            nbOfPositions++;
        }

        return windSpeed / nbOfPositions;
    }


}
