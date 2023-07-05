package com.example.eleksayon;

public class Candidate {
    private String name;
    private String position;
    private String description;
    private String imagePath;

    public Candidate(String name, String position, String description, String imagePath) {
        this.name = name;
        this.position = position;
        this.description = description;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }
}
