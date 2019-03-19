package com.prophet.drconnect.models;


import com.google.firebase.storage.StorageReference;

import java.io.Serializable;

public class Doctors implements Serializable {


    private String id;
    private String name;
    private String degree;
    private String departments;
    private String bio;
    private String email;
    private String specialty;
    private String thumbnail;

    public Doctors(){} // no-argument constructor

    public Doctors(String name, String degree, String departments, String specialty, String thumbnail, String  bio) {
        this.name = name;
        this.degree = degree;
        this.departments = departments;
        this.specialty = specialty;
        this.thumbnail = thumbnail;
        this.bio = bio;
    }

    public Doctors(String id, String name, String departments, String bio, String degree, String specialty, String thumbnail, String email) {
        this.id = id;
        this.name = name;
        this.departments = departments;
        this.bio = bio;
        this.degree = degree;
        this.email = email;
        this.specialty = specialty;
        this.thumbnail = thumbnail;
    }

    public Doctors(String id, String name, String specialty, String thumbnail) {
        this.id = id;
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

    public String getId() {
        return id;
    }
}
