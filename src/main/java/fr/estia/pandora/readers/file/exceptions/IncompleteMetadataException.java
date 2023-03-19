package fr.estia.pandora.readers.file.exceptions;

import fr.estia.pandora.Pandora;

import java.util.Collections;
import java.util.List;

public class IncompleteMetadataException extends FileException {
    private List<String> missingData;

    public IncompleteMetadataException (String filename, List<String> missingData) {
        super(filename, "INCOMPLETE_HEADER", Pandora.EXIT_INVALID_FLIGHT_RECORD);
        this.missingData = missingData;
    }

    @Override
    public String getInfos() {
        Collections.sort(missingData);
        return super.fileName + "=" + missingData.toString();
    }
}
