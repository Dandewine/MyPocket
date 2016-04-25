package com.denis.data.entity;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Token extends RealmObject {
    @SerializedName("token")
    private String token;

    public Token(String token) {
        this.token = token;
    }

    public Token() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}