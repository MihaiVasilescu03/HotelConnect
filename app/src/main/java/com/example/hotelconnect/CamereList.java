package com.example.hotelconnect;

public class CamereList {

    String camere;
    String status;
    String obs;

    public CamereList(String camere , String status, String obs){
        this.camere = camere;
        this.status = status;
        this.obs = obs;
    }

    public String getCamere() {
        return camere;
    }

    public String getStatus() {
        return status;
    }

    public String getObs() {
        return obs;
    }
}
