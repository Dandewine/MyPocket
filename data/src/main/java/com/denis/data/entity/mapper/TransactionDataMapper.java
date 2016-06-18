package com.denis.data.entity.mapper;

import com.denis.data.entity.ExpenseCategoryEntity;
import com.denis.data.entity.IncomeCategoryEntity;
import com.denis.data.entity.TransactionEntity;
import com.denis.data.local_store.RealmStore;
import com.denis.domain.models.Transaction;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TransactionDataMapper implements EntityMapper<TransactionEntity, Transaction> {

    private RealmStore<IncomeCategoryEntity> incomeCategoryEntityRealmStore;
    private RealmStore<ExpenseCategoryEntity> expenseCategoryEntityRealmStore;
    private ExpenseCategoryDataMapper expenseCategoryDataMapper = new ExpenseCategoryDataMapper();
    private IncomeCategoryDataMapper incomeCategoryDataMapper = new IncomeCategoryDataMapper();

    @Inject
    public TransactionDataMapper(RealmStore<IncomeCategoryEntity> incomeCategoryEntityRealmStore,
                                 RealmStore<ExpenseCategoryEntity> expenseCategoryEntityRealmStore) {
        this.incomeCategoryEntityRealmStore = incomeCategoryEntityRealmStore;
        this.expenseCategoryEntityRealmStore = expenseCategoryEntityRealmStore;
    }

    @Override
    public Transaction fromEntity(TransactionEntity entity) {
        Transaction transaction = null;
        if (entity != null) {
            transaction = new Transaction(entity.getId());
            transaction.setAmount(entity.getAmount());
            transaction.setType(entity.getType());
            transaction.setUnixDateTime(entity.getUnixDateTime());
            String categoryId = entity.getCategoryId();

            if(entity.getType().equals(Transaction.TransactionType.EXPENSE.getType())){
                Transaction finalTransaction = transaction;
                expenseCategoryEntityRealmStore.get(categoryId)
                      .map(expenseCategoryEntity -> expenseCategoryDataMapper.fromEntity(expenseCategoryEntity))
                      .map(expenseCategory -> {
                          finalTransaction.setCategory(expenseCategory);
                          return expenseCategory;
                      });

            }else if (entity.getType().equals(Transaction.TransactionType.INCOME.getType())){
                Transaction finalTransaction1 = transaction;
                incomeCategoryEntityRealmStore.get(categoryId)
                        .map(incomeCategoryEntity -> incomeCategoryDataMapper.fromEntity(incomeCategoryEntity))
                        .map(incomeCategoryEntity -> {
                            finalTransaction1.setCategory(incomeCategoryEntity);
                            return incomeCategoryEntity;
                        });
            }else transaction.setCategory(null);

            transaction.setWalletId(entity.getWalletId());
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
            transactionEntity.setAmount(transaction.getAmount());
            transactionEntity.setType(transaction.getType());
            transactionEntity.setCategoryId(transaction.getCategory().getId());
            transactionEntity.setUnixDateTime(transaction.getUnixDateTime());
            transactionEntity.setWalletId(transaction.getWalletId());
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
