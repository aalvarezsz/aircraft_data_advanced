package fr.estia.pandora.readers.file;


import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import fr.estia.pandora.model.Flight;
import fr.estia.pandora.readers.file.exceptions.*;


public class FileReader {

	private final Path testFolderRoot;
	private Scanner source;
	private Path sourcePath;
	private Flight flight;
	private String fileName;

	private boolean metadataParsed = false ;

	public FileReader( String root ) { testFolderRoot = Paths.get( root ).toAbsolutePath().normalize(); }

	public Flight GetRecordsFromFile(String fileName) throws MissingFileException, MissingHeaderException, IncompleteHeaderException, MetadataException {
		this.fileName = fileName.split("/")[fileName.split("/").length - 1];
		flight = new Flight();
		metadataParsed = false ;
		openFlightRecordFile(fileName) ;
		parseMetaData();
		parseData();
		source.close();

		return flight ;
	}
	
	private void openFlightRecordFile( String fileName ) throws MissingFileException {
		try {
			sourcePath = testFolderRoot.resolve( Paths.get( fileName )).toAbsolutePath().normalize() ;
			File flightRecordFile = new File(sourcePath.toString()); 
			source = new Scanner(flightRecordFile);
		} catch (FileNotFoundException e) { //Catch missing files
			throw new MissingFileException(this.fileName);
		}
	}
	
	private boolean isEmptyLine( String line ) {
		return line.matches("^$" );
	}

	private void parseMetaData() throws MetadataException {
		String line ; 
		//use scanner only if the metadata have not yet been used 
		if( !metadataParsed ) {
			if( !readyToParse() ) throw new MetadataException(sourcePath.toString()) ;
			while (readyToParse() && !isEmptyLine(line = source.nextLine())) {
				// System.out.println(line);
				flight.parseMetaData(line);
			}
			metadataParsed = true ;
		}		
	}

	private boolean readyToParse() {
		return source != null && source.hasNextLine() ;
	}

	private void parseData() throws MissingHeaderException, IncompleteHeaderException {
		// Verify that metadata have been read
		if( metadataParsed ) {
			String header, recordLine;
			if(!readyToParse() || isEmptyLine( header = source.nextLine())) throw new MissingHeaderException(fileName);

			RecordParser parser = new RecordParser(fileName, header, flight.getOrigin(), flight.getEngineAmount());

			while ( readyToParse() ) {
				recordLine = source.nextLine();
				flight.addRecord(parser.parse(recordLine));
			}
		}
	}

}
