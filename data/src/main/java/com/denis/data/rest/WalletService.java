package com.denis.data.rest;

import com.denis.data.entity.WalletEntity;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by denis on 5/4/16.
 */
public interface WalletService {
    @POST("/users/{userId}/wallets")
    Observable<WalletEntity> createWallet(@Path("userId") String userId, @Body RequestBody body);

    @GET("/users/{userId}/wallets")
    Observable<List<WalletEntity>> getAllWallets(@Path("userId") String userId);
}
