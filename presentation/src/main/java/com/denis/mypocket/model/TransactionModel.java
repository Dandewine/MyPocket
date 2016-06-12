package com.denis.mypocket.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TransactionModel implements Parcelable {

    private String id;
    private String walletId;
    private float amount;
    private String type;
    private long unixDateTime;
    private String categoryId;

    public TransactionModel(String id) {
        this.id = id;
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

    public void setWalletId(String walletId) {
        this.walletId = walletId;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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
        dest.writeLong(this.unixDateTime);
        dest.writeString(this.categoryId);
    }

    protected TransactionModel(Parcel in) {
        this.id = in.readString();
        this.walletId = in.readString();
        this.amount = in.readFloat();
        this.type = in.readString();
        this.unixDateTime = in.readLong();
        this.categoryId = in.readString();
    }

    public static final Parcelable.Creator<TransactionModel> CREATOR = new Parcelable.Creator<TransactionModel>() {
        @Override
        public TransactionModel createFromParcel(Parcel source) {
            return new TransactionModel(source);
        }

        @Override
        public TransactionModel[] newArray(int size) {
            return new TransactionModel[size];
        }
    };
}
