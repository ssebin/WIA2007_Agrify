package com.example.agrifymad.models;


import java.io.Serializable;

public class ViewAllModel implements Serializable {

    String name;
    String rating;
    String img_url;
    String type;
    String mass;
    String farmName;
    String description;
    int price;

    public ViewAllModel() {
    }

    public ViewAllModel(String name, String rating, String img_url, String type, String mass, String farmName, String description, int price) {
        this.name = name;
        this.rating = rating;
        this.img_url = img_url;
        this.type = type;
        this.mass = mass;
        this.farmName = farmName;
        this.description = description;
        this.price = price;
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
