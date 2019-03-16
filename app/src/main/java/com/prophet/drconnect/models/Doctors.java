package com.prophet.drconnect.models;

import com.google.firebase.database.PropertyName;

public class Doctors {


    private String name;
    private String degree;
    private String departments;
    private String bio;
    private String email;
    private String specialty;
    private String thumbnail;

    public Doctors(){} // no-argument constructor

    public Doctors(String name, String departments, String bio, String degree, String specialty, String thumbnail, String email) {
        this.name = name;
        this.departments = departments;
        this.bio = bio;
        this.degree = degree;
        this.email = email;
        this.specialty = specialty;
        this.thumbnail = thumbnail;
    }

    public Doctors(String name, String specialty, String thumbnail) {
        this.name = name;
        this.specialty = specialty;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }
    public String getSpecialty() {
        return specialty;
    }
    public String getThumbnail() {
        return thumbnail;
    }

    public String getDegree() {
        return degree;
    }

    public String getDepartments() {
        return departments;
    }

    public String getBio() {
        return bio;
    }

    public String getEmail() {
        return email;
    }
}
