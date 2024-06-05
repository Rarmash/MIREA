package com.rarmash.prac10;

public class Game {
    private int id;
    private String title;
    private String yearOfRelease;
    public Game(int id, String title, String yearOfRelease) {
        this.id = id;
        this.title = title;
        this.yearOfRelease = yearOfRelease;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getYearOfRelease() {
        return yearOfRelease;
    }
    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }
}
