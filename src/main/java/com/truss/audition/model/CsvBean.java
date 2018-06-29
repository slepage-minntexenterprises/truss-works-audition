package com.truss.audition.model;

import java.time.Duration;
import java.time.ZonedDateTime;

/**
 */
public class CsvBean {


    private ZonedDateTime timestamp;

    private String address;

    private String zip;

    private String fullName;

    private Duration fooDuration;

    private Duration barDuration;

    private Duration totalDuration;

    private String notes;


    public CsvBean() {
    }


    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getNotes() {
        return notes;
    }

    public Duration getFooDuration() {
        return fooDuration;
    }

    public void setFooDuration(Duration fooDuration) {
        this.fooDuration = fooDuration;
    }

    public Duration getBarDuration() {
        return barDuration;
    }

    public void setBarDuration(Duration barDuration) {
        this.barDuration = barDuration;
    }

    public Duration getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Duration totalDuration) {
        this.totalDuration = totalDuration;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    @Override
    public String toString() {
        return "CsvBean{" +
                "timestamp=" + timestamp +
                ", address='" + address + '\'' +
                ", zip='" + zip + '\'' +
                ", fullName='" + fullName + '\'' +
                ", fooDuration='" + fooDuration + '\'' +
                ", barDuration='" + barDuration + '\'' +
                ", totalDuration='" + totalDuration + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
