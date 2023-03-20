package fr.estia.pandora.readers.file.exceptions;


import fr.estia.pandora.Pandora;

public class OrderingException extends FileException {
    public OrderingException(String fileName) {
        super(fileName, "ORDERING", Pandora.EXIT_INVALID_FLIGHT_RECORD);
    }

    public String getInfos() { return super.fileName; }
}
