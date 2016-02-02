package com.denis.data.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CircleOperationEntity extends RealmObject {
    @PrimaryKey
    private int id;
    private TransactionEntity transactionEntity;
    private String interval;

    public CircleOperationEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TransactionEntity getTransactionEntity() {
        return transactionEntity;
    }

    public void setTransactionEntity(TransactionEntity transactionEntity) {
        this.transactionEntity = transactionEntity;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }
}
