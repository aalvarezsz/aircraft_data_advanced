package fr.estia.pandora.readers.file;


import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

	public Flight GetRecordsFromFile(String fileName) throws FileException {
		this.fileName = fileName.split("/")[fileName.split("/").length - 1];
		flight = new Flight();
		metadataParsed = false;
		openFlightRecordFile(fileName);
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

	private void parseMetaData() throws MissingMetadataException, IncompleteMetadataException {
		String line;

		List<String> metadataList = new ArrayList<>(Arrays.asList(
			"flight id","flight code","origin","date","from","to", "motor(s)",
			"mass aircraft", "mass fuel", "lift coef","drag coef"
		));

		if( !metadataParsed ) {
			if( !readyToParse() ) throw new MissingMetadataException(this.fileName) ;
			while (readyToParse() && !isEmptyLine(line = source.nextLine())) {
				String[] metadata = line.split("\\s*:\\s*");
				int dataIndex = metadataList.indexOf(metadata[0]);

				if(dataIndex >= 0) {
					metadataList.remove(dataIndex);
					flight.parseMetaData(line);
				}
			}

			if (metadataList.size() >= 11) throw new MissingMetadataException(fileName);
			else if (metadataList.size() > 0) throw new IncompleteMetadataException(fileName, metadataList);

			metadataParsed = true ;
		}
	}

	private boolean readyToParse() {
		return source != null && source.hasNextLine() ;
	}

	private void parseData() throws FileException {
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
