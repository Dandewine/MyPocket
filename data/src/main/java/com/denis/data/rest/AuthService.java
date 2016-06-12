package com.denis.data.rest;

import com.denis.data.entity.LoginResponseEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by denis on 4/11/16.
 */
public interface AuthService {
    @POST("sign-up")
    Call<Void> registerUser(@Body RequestBody body);

    @POST("login")
    Call<LoginResponseEntity> loginUser(@Body RequestBody body);

    @GET("logout")
    Call<Void> logout();
}
