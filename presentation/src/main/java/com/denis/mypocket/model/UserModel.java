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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
