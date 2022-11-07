package com.example.myfoodapplication.Order_Responsable;

public class OrderR {
    String id;
    String user_id,name,itemprice,quantity,phone_User;

    public OrderR() {
    }

    public OrderR(String user_id, String name,String itemprice, String quantity) {
        this.user_id = user_id;
        this.name = name;
        this.itemprice = itemprice;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPhone_User() {
        return phone_User;
    }

    public void setPhone_User(String phone_User) {
        this.phone_User = phone_User;
    }
}
