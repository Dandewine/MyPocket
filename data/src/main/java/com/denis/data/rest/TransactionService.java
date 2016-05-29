package com.denis.data.rest;

import com.denis.data.entity.TransactionEntity;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author denis at 5/27/16.
 */

public interface TransactionService {

    @POST("wallets/{walletId}/transactions")
    Observable<TransactionEntity> addTransaction(@Path("walletId") String walletId, @Body RequestBody body);

    @GET("wallets/{walletId}/transactions")
    Observable<List<TransactionEntity>> getTransactions(@Path("walletId") String walletId);

    @GET("wallets/{walletId}/transactions?filter=expense")
    Observable<List<TransactionEntity>> getExpenseTransactions(@Path("walletId") String walletId);

    @GET("wallets/{walletId}/transactions?filter=income")
    Observable<List<TransactionEntity>> getIncomeTransactions(@Path("walletId") String walletId);
}
