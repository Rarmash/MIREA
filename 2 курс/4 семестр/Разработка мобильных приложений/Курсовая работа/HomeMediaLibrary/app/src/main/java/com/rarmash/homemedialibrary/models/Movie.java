package com.rarmash.homemedialibrary.models;

public class Movie {
    private String title;
    private String author;
    private String genre;
    private String dataImage;
    private int releaseYear;

    public Movie() {
    }

    public Movie(String title, String author, String genre, int releaseYear, String dataImage) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.dataImage = dataImage;
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getDataImage() {
        return dataImage;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
}