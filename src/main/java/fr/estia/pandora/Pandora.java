
package fr.estia.pandora;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.estia.pandora.model.Analysis;
import fr.estia.pandora.model.ExceptionManager;
import fr.estia.pandora.model.Flight;
import fr.estia.pandora.model.MultiAnalysis;
import fr.estia.pandora.printer.ConsolePrinter;
import fr.estia.pandora.printer.FeaturePrinter;
import fr.estia.pandora.readers.commandLine.CLI;
import fr.estia.pandora.readers.commandLine.Configuration;
import fr.estia.pandora.readers.commandLine.exceptions.NoOpException;
import fr.estia.pandora.readers.commandLine.exceptions.OptionException;
import fr.estia.pandora.readers.file.FileReader;
import fr.estia.pandora.readers.file.exceptions.FileException;


/**
 * @author William Delamare
 * @author Dimitri Masson
 *
 */
public class Pandora {
	public static final int EXIT_OK = 0;
	public static final int EXIT_ILLEGAL_ARGUMENT = 1;
	public static final int EXIT_INVALID_FLIGHT_RECORD = 2;
	public static final int EXIT_UNHANDLED = 3;

	public static void main(String[] arguments) {
		try {
			CLI.initialize("Pandora", 1, 0, 1);
			Configuration config = CLI.read(arguments);

			List<String> sources = CLI.getConfiguration().getSources();
			if(sources.size() == 0) throw new OptionException("No source provided");
			FileReader fileReader = new FileReader( "./" );

			switch(config.getInputMode()) {
				case mono:
					try {
						if(sources.size() != 1) throw new OptionException("Too much sources provided");
						Flight flight = fileReader.GetRecordsFromFile(sources.get(0));
						Analysis analysis = new Analysis(flight, String.valueOf(config.getTargetFeature()));
						print(flight, analysis);
					} catch (FileException e) { ExceptionManager.handleFileException(e); }
					break;
				case batch:
					List<Flight> flights = new ArrayList<>();
					List<FileException> exceptions = new ArrayList<>();
					Collections.sort(sources);

					for(String source: sources) {
						try {
							flights.add(fileReader.GetRecordsFromFile(source));
						} catch (FileException e) { exceptions.add(e); }
					}

					if (!exceptions.isEmpty()) ExceptionManager.handleFileExceptions(exceptions);

					for(Flight flight: flights) {
						Analysis analysis = new Analysis(flight, String.valueOf(config.getTargetFeature()));
						print( flight, analysis );
					}

					break;
				case multi:
					flights = new ArrayList<>();
					exceptions = new ArrayList<>();
					Collections.sort(sources);

					for(String source: sources) {
						try {
							flights.add(fileReader.GetRecordsFromFile(source));
						} catch (FileException e) { exceptions.add(e); }
					}

					if (!exceptions.isEmpty()) ExceptionManager.handleFileExceptions(exceptions);

					MultiAnalysis analysis = new MultiAnalysis(flights, String.valueOf(config.getTargetFeature()));
					// print whatever

					break;
				default: break;
			}
		} catch (OptionException e) {
			System.out.println( e.getMessage() );
			System.exit( EXIT_ILLEGAL_ARGUMENT );
		} catch (NoOpException e) {
			System.exit( EXIT_OK );
		} catch ( Exception e ) {
			System.out.println( "ERROR: Unhandled error" );
			if( CLI.getConfiguration().isDebugSession() ) e.printStackTrace();
			System.exit( EXIT_UNHANDLED );
		}
	}

	/**
	 * Print information about flight and analysis according to configuration from the command line
	 * @param flight
	 * @param analysis
	 */
	static void print( Flight flight, Analysis analysis ) {
		//Select printer according to configuration
		switch (CLI.getConfiguration().getOutputMode()) {
			case feature:
				//Output mode is set to only one feature, set target and print
				FeaturePrinter.setTargetFeature(CLI.getConfiguration().getTargetFeature());
				FeaturePrinter.print(flight, analysis);
				break;
			default:
				//Default mode print everything
				ConsolePrinter.print(flight, analysis);
		}
	}
}
