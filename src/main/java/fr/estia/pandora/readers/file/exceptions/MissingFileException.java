package fr.estia.pandora.readers.file.exceptions;

import fr.estia.pandora.Pandora;

public class MissingFileException extends FileException {
    private static final long serialVersionUID = -1632350697567844111L;
    private String missingFile;

    public MissingFileException(String missingFile) {
        super(missingFile, "MISSING_FILE", Pandora.EXIT_INVALID_FLIGHT_RECORD);
        this.missingFile = missingFile;
    }

    @Override
    public String getInfos() { return missingFile; }
}
