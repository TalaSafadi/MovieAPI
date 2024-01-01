package com.example.assigment_2;

public class Shows {
    private String Title,year,poster;

    public Shows(String title, String year, String poster) {
        Title = title;
        this.year = year;
        this.poster = poster;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
