package fr.estia.pandora.printer;

import fr.estia.pandora.model.Analysis;
import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.MultiAnalysis;

import java.util.List;

public class ConsolePrinter {
	public static void print( Flight flight, Analysis analysis ) {		
		System.out.println("Flight Report for flight:" + flight.getMetadata().getFlightId());
		System.out.println(analysis.toString() ) ;
	}

	public static void print(List<Flight> flights, MultiAnalysis multiAnalysis ) {
		String flightPrompt = "Flight Report for flights:";
		for(Flight flight: flights) flightPrompt += flight.getMetadata().getFlightId() + ";";

		System.out.println(flightPrompt);
		System.out.println(multiAnalysis.toString());
	}
}
