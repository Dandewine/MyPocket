package com.denis.data.repository.datasource.local;

import com.denis.data.entity.TransactionEntity;
import com.denis.data.local_store.RealmStore;
import com.denis.data.repository.datasource.interfaces.TransactionDataStore;
import com.denis.domain.models.categories.Category;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;

public class TransactionLocalDataStore implements TransactionDataStore {
    private RealmStore<TransactionEntity> realmStore;

    @Inject
    public TransactionLocalDataStore(@Named("transactions") RealmStore<TransactionEntity> realmStore) {
        this.realmStore = realmStore;
    }

    @Override
    public Observable<TransactionEntity> getTransactionEntity(String transactionId) {
        return realmStore.get(transactionId);
    }

    @Override
    public Observable<List<TransactionEntity>> getListTransactionEntities() {
        return realmStore.getList();
    }

    @Override
    public Observable<TransactionEntity> put(TransactionEntity transactionEntity) {
        return realmStore.put(transactionEntity);
    }

    @Override
    public Observable<List<TransactionEntity>> put(List<TransactionEntity> list) {
        return realmStore.put(list);
    }

}
