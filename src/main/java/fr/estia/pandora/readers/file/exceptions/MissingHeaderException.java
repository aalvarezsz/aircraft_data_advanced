package fr.estia.pandora.readers.file.exceptions;

import fr.estia.pandora.Pandora;

public class MissingHeaderException extends FileException {
	private static final long serialVersionUID = -3886159525507073605L;
	public MissingHeaderException(String filename) {
		super(filename, "MISSING_HEADER", Pandora.EXIT_INVALID_FLIGHT_RECORD);
	}

	@Override
	public String getInfos() { return super.fileName; }
}
