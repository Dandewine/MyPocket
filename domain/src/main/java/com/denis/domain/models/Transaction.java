package com.denis.domain.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Transaction implements Parcelable {
    private String id;
    private String walletId;
    private float amount;
    private String type;
    private String categoryId;
    private long unixDateTime;

    public enum TransactionTypes {
        EXPENSE("expense"),
        INCOME("income");

        TransactionTypes(String s) {
        }
    }

    public Transaction(String id, float amount, String type, long unixDateTime, String categoryId) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.categoryId = categoryId;
        this.unixDateTime = unixDateTime;
    }

    public Transaction(String walletId , float amount, String type, String categoryId, long unixDateTime) {
        this.walletId = walletId;
        this.amount = amount;
        this.type = type;
        this.categoryId = categoryId;
        this.unixDateTime = unixDateTime;
    }

    public Transaction(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public long getUnixDateTime() {
        return unixDateTime;
    }

    public void setUnixDateTime(long unixDateTime) {
        this.unixDateTime = unixDateTime;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.walletId);
        dest.writeFloat(this.amount);
        dest.writeString(this.type);
        dest.writeString(this.categoryId);
        dest.writeLong(this.unixDateTime);
    }

    protected Transaction(Parcel in) {
        this.id = in.readString();
        this.walletId = in.readString();
        this.amount = in.readFloat();
        this.type = in.readString();
        this.categoryId = in.readString();
        this.unixDateTime = in.readLong();
    }

    public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() {
        public Transaction createFromParcel(Parcel source) {
            return new Transaction(source);
        }

        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };
}
