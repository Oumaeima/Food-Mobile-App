package com.example.myfoodapplication.Category;

public class MainModel {

    String name, image;


    MainModel() {


    }

    public MainModel(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
