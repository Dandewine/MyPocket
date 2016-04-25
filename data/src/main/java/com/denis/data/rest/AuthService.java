package com.denis.data.rest;

import com.denis.data.repository.datasource.cloud.UserCloudDataStore;

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
    @POST("registr") @Headers("Content-Type: application/json")
    Call<Void> registerUser(@Body RequestBody body);

    @POST("token-auth") @Headers("Content-Type: application/json")
    Call<UserCloudDataStore.Token> loginUser(@Body RequestBody body);

    @GET("logout")
    Call<Void> logout();
}
