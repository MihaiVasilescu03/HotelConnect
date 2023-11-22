package com.example.hotelconnect.models;

public class Camere {

    int id;
    String status, obs;

    public Camere(int id, String status, String obs) {
        this.id = id;
        this.status = status;
        this.obs = obs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
