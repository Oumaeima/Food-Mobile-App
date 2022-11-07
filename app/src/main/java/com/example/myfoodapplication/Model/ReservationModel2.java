package com.example.myfoodapplication.Model;

public class ReservationModel2 {

    String date, personNbr, user_phone, user_id;

    public ReservationModel2() {
    }

    public ReservationModel2(String date, String personNbr, String user_phone, String user_id) {
        this.date = date;
        this.personNbr = personNbr;
        this.user_phone = user_phone;
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPersonNbr() {
        return personNbr;
    }

    public void setPersonNbr(String personNbr) {
        this.personNbr = personNbr;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_id() { return user_id; }

    public void setUser_id(String user_id) { this.user_id = user_id; }
}
