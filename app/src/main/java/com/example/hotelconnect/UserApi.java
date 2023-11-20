package com.example.hotelconnect;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {
    @GET("/angajati/show")
    Call<List<User>> getAllUsers();
    @POST("/angajati/save")
    Call<User> save(@Body User user);
    @DELETE("/angajati/deleteAngajat/{nume}/{prenume}")
    Call<Void> deleteUser(@Path("nume") String nume, @Path("prenume") String prenume);
}
