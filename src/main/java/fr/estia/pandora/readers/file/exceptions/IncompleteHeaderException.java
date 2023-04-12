package fr.estia.pandora.readers.file.exceptions;

import fr.estia.pandora.Pandora;

import java.util.Collections;
import java.util.List;

public class IncompleteHeaderException extends FileException {
	private static final long serialVersionUID = -3886159525507073605L;
	private List<String> missingParameters;

	public IncompleteHeaderException(String filename, List<String> params) {
		super(filename, "MISSING_COLUMN", Pandora.EXIT_INVALID_FLIGHT_RECORD);
		this.missingParameters = params;
	}

	@Override
	public String getInfos() {
		Collections.sort(missingParameters);
		return super.fileName + "=" + missingParameters.toString();
	}
}
