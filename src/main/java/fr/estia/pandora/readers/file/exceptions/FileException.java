package fr.estia.pandora.readers.file.exceptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileException extends Exception {
    /** An id for serialization purpose */
    private static final long serialVersionUID = -1632350697567844111L;
    /** code of the invalid exception */
    protected List<String> missingFiles;

    public FileException( List<String> missingFiles ) {
        this.missingFiles = new ArrayList<>();
        this.missingFiles = missingFiles;
    }

    /**
     * @return the missingFile
     */
    public List<String> getMissingFiles() {
        return this.missingFiles;
    }

    /**
     * provide a one line explanation of the error
     * @return  "Missing file: file name(s)"
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return "ERROR: MISSING_FILE - " + stringify(this.missingFiles) ;
    }

    private String stringify(List<String> missingFiles) {
        Collections.sort(missingFiles);
        return String.join(" ", missingFiles);
    }
}
