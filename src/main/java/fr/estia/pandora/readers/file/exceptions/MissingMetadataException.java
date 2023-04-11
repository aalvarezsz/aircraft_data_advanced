package fr.estia.pandora.readers.file.exceptions;

import fr.estia.pandora.Pandora;

public class MissingMetadataException extends FileException {
	private static final long serialVersionUID = -3886159525507073605L;
	private String message;

	public MissingMetadataException(String filename) {
		super(filename, "MISSING_HEADER", Pandora.EXIT_INVALID_FLIGHT_RECORD);
	}
	
	public String getInfos() { return super.fileName; }
}
