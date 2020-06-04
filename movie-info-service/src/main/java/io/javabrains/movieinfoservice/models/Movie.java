package io.javabrains.movieinfoservice.models;

public class Movie {
    private String modieId;
    private String name;

    public String getModieId() {
        return modieId;
    }

    public String getName() {
        return name;
    }

    public void setModieId(String modieId) {
        this.modieId = modieId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Movie(String modieId, String name) {
        this.modieId = modieId;
        this.name = name;
    }
}
