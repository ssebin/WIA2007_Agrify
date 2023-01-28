package com.example.agrifymad.models;

public class RecommendedModel {

        String name;
        String description;
        String rating;
        String img_url;
        String discount;

        public RecommendedModel() {
        }

        public RecommendedModel(String name, String description, String rating, String img_url, String discount) {
                this.name = name;
                this.description = description;
                this.rating = rating;
                this.img_url = img_url;
                this.discount = discount;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
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

        public String getDiscount() {
                return discount;
        }

        public void setDiscount(String discount) {
                this.discount = discount;
        }
}
