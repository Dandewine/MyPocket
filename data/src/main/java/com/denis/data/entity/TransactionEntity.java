package com.denis.data.entity;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TransactionEntity extends RealmObject {
    @PrimaryKey @SerializedName("id")     private String id;
    @SerializedName("walletId")           private String walletId;
    @SerializedName("amount")             private float amount;
    @SerializedName("type")               private String type; // 1 - Income, 0 - Expense
    @SerializedName("categoryId")         private String categoryId; //IncomeCategoryEntity(1) or ExpenseCategoryEntity(0)
    @SerializedName("unixDateTime")       private long unixDateTime;

    public TransactionEntity(String id) {
        this.id = id;
    }

    public TransactionEntity() {
    }

    public TransactionEntity(String id, String walletId, float amount, String type, String categoryId, long unixDateTime) {
        this.id = id;
        this.walletId = walletId;
        this.amount = amount;
        this.type = type;
        this.categoryId = categoryId;
        this.unixDateTime = unixDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletEntity) {
        this.walletId = walletEntity;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public long getUnixDateTime() {
        return unixDateTime;
    }

    public void setUnixDateTime(long unixDateTime) {
        this.unixDateTime = unixDateTime;
    }
}
