package com.denis.data.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TransactionEntity extends RealmObject {
    @PrimaryKey private int id;
    private int walletId;
    private float amount;
    private int type;
    private long unixDateTime;

    public TransactionEntity(int id) {
        this.id = id;
    }

    public TransactionEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getUnixDateTime() {
        return unixDateTime;
    }

    public void setUnixDateTime(long unixDateTime) {
        this.unixDateTime = unixDateTime;
    }
}
