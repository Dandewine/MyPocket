package com.denis.data.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CycleOperationEntity extends RealmObject {
    @PrimaryKey
    private int id;
    private String name;
    private TransactionEntity transactionEntity;
    private String interval;
    private long triggerTime;

    public CycleOperationEntity(int id) {
        this.id = id;
    }

    public CycleOperationEntity() {
    }

    public CycleOperationEntity(int id, String name, TransactionEntity transactionEntity, String interval) {
        this.id = id;
        this.name = name;
        this.transactionEntity = transactionEntity;
        this.interval = interval;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(long triggerTime) {
        this.triggerTime = triggerTime;
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
