package com.example.eleksayon;

public class Candidate {
    private String firstName;
    private String lastName;
    private String yearLevel;
    private String courseCandidate;
    private String position;
    private String platform;
    private String imagePath;

    public Candidate(String firstName, String lastName, String yearLevel, String courseCandidate, String position, String platform, String imagePath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearLevel = yearLevel;
        this.courseCandidate = courseCandidate;
        this.position = position;
        this.platform = platform;
        this.imagePath = imagePath;
    }

    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getPosition() {
        return position;
    }

    public String getDescription() {
        return platform;
    }

    public String getImagePath() {
        return imagePath;
    }
}
