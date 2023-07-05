package com.example.eleksayon;

public class Candidate {
    private int id;
    private String firstName;
    private String lastName;
    private String yearLevel;
    private String courseCandidate;
    private String position;
    private String platform;
    private String imagePath;
    private int voteCount;

    public Candidate(int id, String firstName, String lastName, String yearLevel, String courseCandidate, String position, String platform, String imagePath) {
        this.id = id;
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
    public int getId() {
        return id;
    }
    public int getVoteCount(){
        return voteCount;
    }
    public void setVoteCount(int voteCount){
        this.voteCount = voteCount;
    }
}
