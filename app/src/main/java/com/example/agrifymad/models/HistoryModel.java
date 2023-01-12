package com.example.agrifymad.models;

public class HistoryModel {
    private String title, status, cost, date, url;

    public HistoryModel(String title, String status, String cost, String date, String url) {
        this.title = title;
        this.status = status;
        this.cost = cost;
        this.date = date;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getCost() {
        return cost;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}