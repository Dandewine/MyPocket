package com.denis.domain.models;

public class Transaction {
    private int id;
    private int walletId;
    private float amount;
    private int type;
    private int categoryId;
    private long unixDateTime;

    public Transaction(int walletId, float amount, int type, long unixDateTime, int categoryId) {
        this.walletId = walletId;
        this.amount = amount;
        this.type = type;
        this.categoryId = categoryId;
        this.unixDateTime = unixDateTime;
    }

    public Transaction(int id) {
        this.id = id;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
