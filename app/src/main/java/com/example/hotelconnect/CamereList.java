package com.example.hotelconnect;

public class CamereList {

    String camere;
    String status;

    public CamereList(String camere , String status){
        this.camere = camere;
        this.status = status;
    }

    public String getCamere() {
        return camere;
    }

    public String getStatus() {
        return status;
    }
}
