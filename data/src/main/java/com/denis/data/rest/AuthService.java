package com.denis.data.rest;

import com.denis.data.entity.LoginResponseEntity;

import okhttp3.RequestBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by denis on 4/11/16.
 */
public interface AuthService {
    @POST("sign-up") @Headers("Content-Type: application/json")
    Call<Void> registerUser(@Body RequestBody body);

    @POST("login") @Headers("Content-Type: application/json")
    Call<LoginResponseEntity> loginUser(@Body RequestBody body);

    @GET("logout")
    Call<Void> logout();
}
