package com.example.agrifymad.models;

public class ShopModel {
    String farmName;
    String farmLocation;
    String img_url;
    String rating;
    String phone;

    public ShopModel() {
    }


    public ShopModel(String farmName, String farmLocation, String img_url, String rating, String phone) {
        this.farmName = farmName;
        this.farmLocation = farmLocation;
        this.img_url = img_url;
        this.rating = rating;
        this.phone = phone;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getFarmLocation() {
        return farmLocation;
    }

    public void setFarmLocation(String farmLocation) {
        this.farmLocation = farmLocation;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
