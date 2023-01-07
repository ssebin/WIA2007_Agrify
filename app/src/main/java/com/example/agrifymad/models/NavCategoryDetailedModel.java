package com.example.agrifymad.models;

public class NavCategoryDetailedModel {
    String name;
    String type;
    String img_url;
    int price;
    String mass;

    public NavCategoryDetailedModel() {
    }

    public NavCategoryDetailedModel(String name, String type, String img_url, int price, String mass) {
        this.name = name;
        this.type = type;
        this.img_url = img_url;
        this.price = price;
        this.mass = mass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }
}
