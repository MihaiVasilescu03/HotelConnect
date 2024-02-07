package com.example.hotelconnect.API_SERVICES;

import com.example.hotelconnect.models.Anunturi;
import com.example.hotelconnect.models.Camere;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AnunturiApi {

    @GET("/anunturi/show")
    Call<List<Anunturi>> getAllAnunturi();
    @FormUrlEncoded
    @POST("/anunturi/add")
    Call<Void> addNewAnunt(
            @Field("titlu") String titlu,
            @Field("mainbody") String mainBody
    );


}
