package fr.estia.pandora.model;

import fr.estia.pandora.Pandora;
import fr.estia.pandora.readers.file.exceptions.FileException;

import java.util.*;

public class ExceptionManager {

    public static void handleFileException(FileException exception) {
        System.out.println(exception.getMessage() + exception.getInfos());
        System.exit(exception.getExitCode());
    }

    public static void handleFileExceptions(List<FileException> exceptions) {
        Map<Class<? extends FileException>, List<FileException>> exceptionMap = sort(exceptions);

        for (Map.Entry<Class<? extends FileException>, List<FileException>> exceptionType : exceptionMap.entrySet()) {
            List<FileException> exceptionList = exceptionType.getValue();

            System.out.print(exceptionList.get(0).getMessage());
            for (FileException exception : exceptionList) System.out.print(exception.getInfos() + " ");
            System.out.println();
            // System.exit(exceptionList.get(0).getExitCode());
        }

        System.exit(Pandora.EXIT_INVALID_FLIGHT_RECORD);
    }

    public static Map<Class<? extends FileException>, List<FileException>> sort(List<FileException> exceptions) {
        Collections.sort(exceptions, Comparator.comparing(FileException::getInfos));

        Map<Class<? extends FileException>, List<FileException>> exceptionMap = new HashMap<>();
        for (FileException exception : exceptions) {
            Class<? extends FileException> exceptionType = exception.getClass();
            exceptionMap.computeIfAbsent(exceptionType, k -> new ArrayList<>()).add(exception);
        }

        return exceptionMap;
    }

}
