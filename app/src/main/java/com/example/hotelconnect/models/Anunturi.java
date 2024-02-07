package com.example.hotelconnect.models;

import java.util.Date;

public class Anunturi {

    int id;
    Date data;
    String titlu, mainbody;

    public Anunturi(int id, Date data, String title, String mainbody) {
        this.id = id;
        this.data = data;
        this.titlu = title;
        this.mainbody = mainbody;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getMainbody() {
        return mainbody;
    }

    public void setMainbody(String mainbody) {
        this.mainbody = mainbody;
    }
}
