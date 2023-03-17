
package fr.estia.pandora;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.estia.pandora.model.Analysis;
import fr.estia.pandora.model.Flight;
import fr.estia.pandora.printer.ConsolePrinter;
import fr.estia.pandora.printer.FeaturePrinter;
import fr.estia.pandora.readers.commandLine.CLI;
import fr.estia.pandora.readers.commandLine.Configuration;
import fr.estia.pandora.readers.commandLine.exceptions.NoOpException;
import fr.estia.pandora.readers.commandLine.exceptions.OptionException;
import fr.estia.pandora.readers.file.FileReader;
import fr.estia.pandora.readers.file.exceptions.FlightRecordException;


/**
 * @author William Delamare
 * @author Dimitri Masson
 *
 */
public class Pandora {
	static final int EXIT_OK = 0 ;
	static final int EXIT_ILLEGAL_ARGUMENT = 1 ;
	static final int EXIT_INVALID_FLIGHT_RECORD = 2 ;
	static final int EXIT_UNHANDLED = 3 ;

	public static void main(String[] arguments) {
		try {
			CLI.initialize("Pandora", 1, 0, 1);
			// Parse the command line arguments
			Configuration config = CLI.read(arguments);

			// Check that exactly one source file was provided, exit otherwise
			List<String> sources = CLI.getConfiguration().getSources();
			if(sources.size() == 0) throw new OptionException("No source provided");
			
			// Create a file reader
			FileReader fileReader = new FileReader( "./" );
			Flight flight; Analysis analysis;
			
			// Parse files, execute analysis and print result
			switch(config.getInputMode()) {
				case mono:
					if(sources.size() != 1) throw new OptionException("Too much sources provided");
					flight = fileReader.GetRecordsFromFile(sources.get(0));
					analysis = new Analysis(flight, String.valueOf(config.getTargetFeature()));
					print(flight, analysis);
					break;
				default:
					Collections.sort(sources);
					List<String> files = new ArrayList<String>();
					for(String source: sources) {
						try {
							flight = fileReader.GetRecordsFromFile(source);
							analysis = new Analysis(flight, String.valueOf(config.getTargetFeature()));
							print( flight, analysis );
						} catch (Exception e) {

						}
					}
					Collections.sort(files);

					
					break;
			}
			
		} catch (OptionException e) {
			System.out.println( e.getMessage() );
			System.exit( EXIT_ILLEGAL_ARGUMENT );
		} catch (FlightRecordException e) {
			System.out.println( e.getMessage() );
			System.exit( EXIT_INVALID_FLIGHT_RECORD );
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
