package fr.estia.pandora.readers.file.exceptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileException extends Exception {
    /** An id for serialization purpose */
    private static final long serialVersionUID = -5255255831476166381L;
    /** code of the invalid exception */
    protected String fileName;
    protected String exception;
    protected int exitCode;

    public FileException(String fileName, String exception, int exitCode) {
        this.fileName = fileName;
        this.exception = exception;
        this.exitCode = exitCode;
    }

    @Override
    public String getMessage() { return "ERROR: " + exception + " - "; }
    public String getInfos() { return "File Exception"; }
    public int getExitCode() { return exitCode; }

    /**
     * ALL EXCEPTIONS :
     * 	Invalid command line option
     * 	Missing command line parameter
     * 	Not implemented handling
     * 	Missing files
     * 	Encoding problem
     * 	Corrupted files
     * 	Missing header
     * 	Incomplete header
     * 	Missing columns
     * 	Missing column name
     * 	Incorrect timestamp ordering
     * 	Incorrect input
     */
}
