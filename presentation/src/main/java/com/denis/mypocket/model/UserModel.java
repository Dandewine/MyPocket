package com.denis.mypocket.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by denis on 12/20/15.
 */
public class UserModel {
    @SerializedName("username") private String login;
    @SerializedName("email") private String email;
    @SerializedName("password") private String password;

    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserModel(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }
}
