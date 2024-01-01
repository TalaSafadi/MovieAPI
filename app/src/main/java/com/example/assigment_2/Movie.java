package com.example.assigment_2;

public class Movie {
    private String title , poster , Type,year,ImdbID;

    public Movie(String title, String poster, String type, String year, String imdbID) {
        this.title = title;
        this.poster = poster;
        Type = type;
        this.year = year;
        ImdbID = imdbID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return ImdbID;
    }

    public void setImdbID(String imdbID) {
        ImdbID = imdbID;
    }

    @Override
    public String toString() {
        return title;
    }
}
