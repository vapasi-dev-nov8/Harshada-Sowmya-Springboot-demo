package com.vapasi.demo.dto;

public class Movie {
    String name;
    String actorName;
    String directorName;

    public Movie(String name, String actorName, String directorName) {
        this.name = name;
        this.actorName = actorName;
        this.directorName = directorName;
    }

    public String getName() {
        return name;
    }

    public String getActorName() {
        return actorName;
    }

    public String getDirectorName() {
        return directorName;
    }
}
