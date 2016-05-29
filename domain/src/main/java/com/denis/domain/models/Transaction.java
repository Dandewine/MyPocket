package com.denis.domain.models;


import android.os.Parcel;
import android.os.Parcelable;

public class Transaction implements Parcelable {
    private String id;
    private float amount;
    private String type;
    private int categoryId;
    private long unixDateTime;

   public enum TransactionTypes{
        EXPENSE("expense"),
        INCOME("income");

        TransactionTypes(String s) {

        }
    }

    public Transaction(String id, float amount, String type, long unixDateTime, int categoryId) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.categoryId = categoryId;
        this.unixDateTime = unixDateTime;
    }

    public Transaction(float amount, String type, int categoryId, long unixDateTime) {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeFloat(this.amount);
        dest.writeString(this.type);
        dest.writeInt(this.categoryId);
        dest.writeLong(this.unixDateTime);
    }

    protected Transaction(Parcel in) {
        this.id = in.readString();
        this.amount = in.readFloat();
        this.type = in.readString();
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
