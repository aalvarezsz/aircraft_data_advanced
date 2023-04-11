package fr.estia.pandora.readers.file.exceptions;


import fr.estia.pandora.Pandora;

public class CorruptedFileException extends FileException {

    public CorruptedFileException(String fileName) {
        super(fileName, "CORRUPTED", Pandora.EXIT_INVALID_FLIGHT_RECORD);
    }

    @Override
    public String getInfos() { return super.fileName; }
}
