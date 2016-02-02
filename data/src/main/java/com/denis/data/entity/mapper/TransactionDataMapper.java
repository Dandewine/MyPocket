package com.denis.data.entity.mapper;

import com.denis.data.entity.TransactionEntity;
import com.denis.domain.models.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class TransactionDataMapper {

    @Inject
    public TransactionDataMapper() {
    }

    public Transaction transform(TransactionEntity entity) {
        Transaction transaction = null;
        if (entity != null) {
            transaction = new Transaction(entity.getCategoryId());
            transaction.setWalletId(entity.getWalletId());
            transaction.setAmount(entity.getAmount());
            transaction.setType(entity.getType());
            transaction.setUnixDateTime(entity.getUnixDateTime());
        }
        return transaction;
    }

    public List<Transaction> transform(Collection<TransactionEntity> entityList) {
        List<Transaction> transactionList = null;
        if (entityList != null && !entityList.isEmpty()) {
            transactionList = new ArrayList<>();
            for (TransactionEntity entity : entityList) {
                Transaction t = transform(entity);
                transactionList.add(t);
            }
        }
        return transactionList;
    }

    public TransactionEntity transform(Transaction transaction) {
        TransactionEntity transactionEntity = null;
        if (transaction != null) {
            transactionEntity = new TransactionEntity();
            transactionEntity.setId(transaction.getId());
            transactionEntity.setWalletId(transaction.getWalletId());
            transactionEntity.setAmount(transaction.getAmount());
            transactionEntity.setType(transaction.getType());
            transactionEntity.setCategoryId(transaction.getId());
            transactionEntity.setUnixDateTime(transaction.getUnixDateTime());
        }
        return transactionEntity;
    }

    public List<TransactionEntity> transform(List<Transaction> transactionList) {
        List<TransactionEntity> entityList = null;
        if (transactionList != null && !transactionList.isEmpty()) {
            entityList = new ArrayList<>();
            for (Transaction t : transactionList) {
                TransactionEntity e = transform(t);
                entityList.add(e);
            }
        }
        return entityList;
    }
}
