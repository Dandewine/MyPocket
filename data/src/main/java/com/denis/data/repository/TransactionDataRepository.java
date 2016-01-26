package com.denis.data.repository;

import com.denis.data.entity.mapper.TransactionDataMapper;
import com.denis.data.repository.datasource.interfaces.TransactionDataStore;
import com.denis.domain.models.Transaction;
import com.denis.domain.repository.TransactionRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;


public class TransactionDataRepository implements TransactionRepository {
    public TransactionDataStore dataStore;
    public TransactionDataMapper dataMapper;

    @Inject
    public TransactionDataRepository(TransactionDataStore dataStore, TransactionDataMapper dataMapper) {
        this.dataStore = dataStore;
        this.dataMapper = dataMapper;
    }

    @Override
    public Observable<List<Transaction>> getTransactionList() {
        return dataStore.getListTransactionEntities()
                .map(dataMapper::transform);
    }

    @Override
    public Observable<Transaction> getTransaction(int transactionId) {
        return dataStore.getTransactionEntity(transactionId).map(dataMapper::transform);
    }

    @Override
    public Observable<Transaction> addTransaction(Transaction transaction) {
        return dataStore.put(dataMapper.transform(transaction))
                .map(transactionEntity -> dataMapper.transform(transactionEntity));
    }

    @Override
    public Observable<List<Transaction>> addTransaction(List<Transaction> transactions) {
        throw new UnsupportedOperationException("You cant add a list of transaction");
    }
}
