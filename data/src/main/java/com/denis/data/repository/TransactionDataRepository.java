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
                .map(dataMapper::fromEntity);
    }

    @Override
    public Observable<Transaction> getTransaction(String transactionId) {
        return dataStore.getTransactionEntity(transactionId).map(dataMapper::fromEntity);
    }

    @Override
    public Observable<Transaction> addTransaction(Transaction transaction) {
        return dataStore.put(dataMapper.toEntity(transaction))
                .map(transactionEntity -> dataMapper.fromEntity(transactionEntity));
    }

    @Override
    public Observable<List<Transaction>> addTransaction(List<Transaction> transactions) {
        throw new UnsupportedOperationException("You cant add a list of transaction");
    }
}
