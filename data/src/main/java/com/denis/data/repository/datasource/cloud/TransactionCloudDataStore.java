package com.denis.data.repository.datasource.cloud;

import com.denis.data.entity.TransactionEntity;
import com.denis.data.repository.datasource.interfaces.TransactionDataStore;
import com.denis.data.rest.TransactionService;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * @author denis at 5/27/16.
 */

public class TransactionCloudDataStore implements TransactionDataStore {

    private TransactionService transactionService;

    @Inject
    public TransactionCloudDataStore(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public Observable<TransactionEntity> getTransactionEntity(String transactionId) {
        return null;
    }

    @Override
    public Observable<List<TransactionEntity>> getListTransactionEntities() {
        return null;
    }

    @Override
    public Observable<TransactionEntity> put(TransactionEntity transactionEntity) {
        String json = new Gson().toJson(transactionEntity);
        RequestBody requestBody = RequestBody.create(MediaType.parse(json),json);
        return transactionService.addTransaction(transactionEntity.getWalletId(),requestBody);
    }

    @Override
    public Observable<List<TransactionEntity>> put(List<TransactionEntity> list) {
        throw new UnsupportedOperationException("Can't do this yet");
    }
}
