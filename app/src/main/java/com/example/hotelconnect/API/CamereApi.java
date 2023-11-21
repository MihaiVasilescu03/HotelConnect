package com.example.hotelconnect.API;

import com.example.hotelconnect.models.Camere;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CamereApi {

    @GET("/camere/show")
    Call<List<Camere>> getAllCamere();

}
