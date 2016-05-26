package com.denis.data.repository.datasource.interfaces;

import com.denis.data.entity.TransactionEntity;

import java.util.List;

import rx.Observable;

public interface TransactionDataStore {
    /**
     *Get an {@link rx.Observable} which will emit a {@link TransactionEntity} by its id.
     * @param transactionId The id to retrieve wallet data.
     */
    Observable<TransactionEntity> getTransactionEntity(final String transactionId);
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link TransactionEntity}.
     */
    Observable<List<TransactionEntity>> getListTransactionEntities();

    /**
     * Store out model into local storage {@link io.realm.Realm}
     * @param transactionEntity
     * @return
     */
    Observable<TransactionEntity> put(TransactionEntity transactionEntity);

    /**
     * Store collection into local storage {@link io.realm.Realm}
     * @param list
     * @return
     */
    Observable<List<TransactionEntity>> put(List<TransactionEntity> list);
}
