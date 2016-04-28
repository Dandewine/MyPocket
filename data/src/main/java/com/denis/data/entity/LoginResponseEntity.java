package com.denis.data.entity;

import com.denis.domain.models.User;
import com.google.gson.annotations.SerializedName;

/**
 * Created by denis on 4/28/16.
 */
public class LoginResponseEntity {
    @SerializedName("user")
    private User user;

    @SerializedName("token")
    private String token;

    public LoginResponseEntity(String token) {
        this.token = token;
    }

    public LoginResponseEntity() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
