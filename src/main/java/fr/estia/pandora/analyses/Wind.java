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
        double avgGroundSpeed = 0;
        double groundSpeed = 0;
        double result = 0;
        double nbOfPositions = 0;

        if(flight.getRecords().size() <= 1) return 0;

        for(int i = 0; i < flight.getRecords().size() - 1; i++) {
            Position position_0 = new Position(flight.getRecords().get(i).getLatitude(), flight.getRecords().get(i).getLongitude(), flight.getRecords().get(i).getAltitude());
            Position position_1 = new Position(flight.getRecords().get(i+1).getLatitude(), flight.getRecords().get(i+1).getLongitude(), flight.getRecords().get(i+1).getAltitude());
            double time_0 = flight.getRecords().get(i).getTimestamp();
            double time_1 = flight.getRecords().get(i+1).getTimestamp();

            double dTime = time_1 - time_0;

            double dDistance = Utils.ComputeDistance(position_0, position_1);

            double speed = dDistance / dTime;

            groundSpeed += speed ;
            nbOfPositions = i;
        }
        double avgAirSpeed = AirSpeed.average(flight);
        System.out.println(nbOfPositions);
        System.out.println(flight.getRecords().size());
        avgGroundSpeed = groundSpeed / flight.getRecords().size();


        result = avgGroundSpeed - avgAirSpeed;
        return result;
    }


    public static double altitudeWithFastestWind(Flight flight) {
        ArrayList<Record> flightRecords = flight.getRecords();

        return 0.0;
    }

}
