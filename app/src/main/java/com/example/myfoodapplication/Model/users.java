package com.example.myfoodapplication.Model;

public class users {

    public String nom , email, address, tel, password;

    public users() {

    }

    public users(String nom, String email, String address, String tel, String password) {
        this.nom = nom;
        this.email = email;
        this.address = address;
        this.tel = tel;
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
