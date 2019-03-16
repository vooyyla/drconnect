package com.prophet.drconnect.models;

public class Profile {
    private String title;
    private String image;
    private String department;
    private String specialty;
    private String degree;
    private String bio;

    public Profile() {}

    public Profile(String title, String image, String department, String specialty, String degree, String bio) {
        this.title = title;
        this.image = image;
        this.department = department;
        this.specialty = specialty;
        this.degree = degree;
        this.bio = bio;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDepartment() {
        return department;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getDegree() {
        return degree;
    }

    public String getBio() {
        return bio;
    }
}
