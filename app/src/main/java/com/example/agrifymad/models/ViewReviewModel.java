package com.example.agrifymad.models;

public class ViewReviewModel {

    String img_url;
    String name;
    String rating;
    String description;
    String type;

    public ViewReviewModel() {
    }

    public ViewReviewModel(String img_url, String name, String rating, String description, String type) {
        this.img_url = img_url;
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
