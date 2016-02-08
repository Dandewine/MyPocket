package com.denis.domain.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Transaction implements Parcelable {
    private int id;
    private Wallet wallet;
    private float amount;
    private int type;
    private int categoryId;
    private long unixDateTime;

    public Transaction(int id, Wallet wallet, float amount, int type, long unixDateTime, int categoryId) {
        this.id = id;
        this.wallet = wallet;
        this.amount = amount;
        this.type = type;
        this.categoryId = categoryId;
        this.unixDateTime = unixDateTime;
    }

    public Transaction(Wallet wallet, float amount, int type, int categoryId, long unixDateTime) {
        this.wallet = wallet;
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

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeParcelable(this.wallet, flags);
        dest.writeFloat(this.amount);
        dest.writeInt(this.type);
        dest.writeInt(this.categoryId);
        dest.writeLong(this.unixDateTime);
    }

    protected Transaction(Parcel in) {
        this.id = in.readInt();
        this.wallet = in.readParcelable(Wallet.class.getClassLoader());
        this.amount = in.readFloat();
        this.type = in.readInt();
        this.categoryId = in.readInt();
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
