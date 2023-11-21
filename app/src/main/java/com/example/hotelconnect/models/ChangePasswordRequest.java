package com.example.hotelconnect.models;

public class ChangePasswordRequest {

    private String username;
    private String oldPassword;
    private String newPassword;

    public ChangePasswordRequest(String username, String oldPassword, String newPassword) {
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

}
