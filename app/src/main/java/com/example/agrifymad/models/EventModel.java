package com.example.agrifymad.models;

public class EventModel {
    private String day, month, title, url;

    public EventModel(String day, String month, String title, String url) {
        this.day = day;
        this.month = month;
        this.title = title;
        this.url = url;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}