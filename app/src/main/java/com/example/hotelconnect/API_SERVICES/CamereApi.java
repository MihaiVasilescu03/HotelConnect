package com.example.hotelconnect.API_SERVICES;

import com.example.hotelconnect.models.Camere;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CamereApi {

    @GET("/camere/show")
    Call<List<Camere>> getAllCamere();

    @POST("/camere/change")
    Call<Void> changeCamera(@Body Camere Camera);

}
