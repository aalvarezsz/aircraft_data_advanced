package fr.estia.pandora.readers.file.exceptions;

public class FlightRecordException extends Exception {
	/** An id for serialization purpose */
	private static final long serialVersionUID = -5255255831476166381L;
	/** code of the invalid exception */
	protected String fileName;
	protected String exception;

	public FlightRecordException(String fileName, String exception) {
		this.fileName = fileName;
		this.exception = exception;
	}

	@Override
	public String getMessage() { return "ERROR: " + exception + " - "; }
	public String getInfos() { return "File Exception"; }
}