package fr.estia.pandora.model;

import fr.estia.pandora.analyses.FlightAnalysis;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FlightPhase {
    public double startTimestamp;
    public double endTimestamp;

    public FlightPhase(double startTimestamp, double endTimestamp) {
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
    }

    public double getRawDuration() { return endTimestamp - startTimestamp; }
    public String formattedStartTime() {  return formatTime(startTimestamp); }
    public String formattedEndTime() {  return formatTime(endTimestamp); }
    public String toString() { return "start=" + formattedStartTime() + " / end=" + formattedEndTime(); }

    public static String formatTime(double timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        Instant instant = Instant.ofEpochSecond((long) timestamp);
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("GMT+2"));

        return zonedDateTime.format(formatter);
    }
}
