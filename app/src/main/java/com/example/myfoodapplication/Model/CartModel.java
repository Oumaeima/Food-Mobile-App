package com.example.myfoodapplication.Model;

import com.google.firebase.database.Exclude;

public class CartModel {


    String id,name, price,quantity="1",userId, user_phone;


    public CartModel() {
    }

    public CartModel(String name, String price, String quantity, String userId,String user_phone) {

        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.userId = userId;
        this.user_phone = user_phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }
}