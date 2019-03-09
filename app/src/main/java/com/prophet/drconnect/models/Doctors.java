package com.prophet.drconnect.models;

public class Doctors {

    private int id, thumbnail;
    private String name, qualification, specialty;

    public Doctors(){}

    public Doctors(String name, String qualification, String specialty, int thumbnail) {
        this.name = name;
        this.qualification = qualification;
        this.specialty = specialty;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getThumbnail() {
        return thumbnail;
    }
}
