package com.denis.data.rest;

import com.denis.data.entity.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by denis on 4/11/16.
 */
public interface AuthService {
    @POST("registr") Call<Void> registerUser(@Body String body);
    @POST("token-auth") Call<Token> loginUser(@Body String body);
}
