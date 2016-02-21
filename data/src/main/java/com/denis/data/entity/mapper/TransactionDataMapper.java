package com.denis.data.entity.mapper;

import com.denis.data.entity.TransactionEntity;
import com.denis.domain.models.Transaction;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TransactionDataMapper implements EntityMapper<TransactionEntity, Transaction> {

    private WalletDataMapper dataMapper;

    @Inject
    public TransactionDataMapper(WalletDataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    @Override
    public Transaction fromEntity(TransactionEntity entity) {
        Transaction transaction = null;
        if (entity != null) {
            transaction = new Transaction(entity.getId());
            transaction.setWallet(dataMapper.fromEntity(entity.getWalletEntity()));
            transaction.setAmount(entity.getAmount());
            transaction.setType(entity.getType());
            transaction.setUnixDateTime(entity.getUnixDateTime());
            transaction.setCategoryId(entity.getCategoryId());
        }
        return transaction;
    }

    @Override
    public List<Transaction> fromEntity(List<TransactionEntity> entityList) {
        List<Transaction> transactionList = null;
        if (entityList != null && !entityList.isEmpty()) {
            transactionList = new ArrayList<>();
            for (TransactionEntity entity : entityList) {
                Transaction t = fromEntity(entity);
                transactionList.add(t);
            }
        }
        return transactionList;
    }

    @Override
    public TransactionEntity toEntity(Transaction transaction) {
        TransactionEntity transactionEntity = null;
        if (transaction != null) {
            transactionEntity = new TransactionEntity();
            transactionEntity.setId(transaction.getId());
            transactionEntity.setWalletEntity(dataMapper.toEntity(transaction.getWallet()));
            transactionEntity.setAmount(transaction.getAmount());
            transactionEntity.setType(transaction.getType());
            transactionEntity.setCategoryId(transaction.getCategoryId());
            transactionEntity.setUnixDateTime(transaction.getUnixDateTime());
        }
        return transactionEntity;
    }

    @Override
    public List<TransactionEntity> toEntity(List<Transaction> transactionList) {
        List<TransactionEntity> entityList = null;
        if (transactionList != null && !transactionList.isEmpty()) {
            entityList = new ArrayList<>();
            for (Transaction t : transactionList) {
                TransactionEntity e = toEntity(t);
                entityList.add(e);
            }
        }
        return entityList;
    }
}
