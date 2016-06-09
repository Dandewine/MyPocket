package com.denis.mypocket.model;

import android.os.Parcel;
import android.os.Parcelable;

public class WalletModel implements Parcelable {

    private String id;
    private String name;
    private String currency;
    private float balance;
    private boolean isActive;

    public WalletModel(String id) {
        this.id = id;
    }

    public WalletModel() {
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    protected WalletModel(Parcel in) {
        id = in.readString();
        name = in.readString();
        currency = in.readString();
        balance = in.readFloat();
        isActive = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(currency);
        dest.writeFloat(balance);
        dest.writeByte((byte) (isActive ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<WalletModel> CREATOR = new Parcelable.Creator<WalletModel>() {
        @Override
        public WalletModel createFromParcel(Parcel in) {
            return new WalletModel(in);
        }

        @Override
        public WalletModel[] newArray(int size) {
            return new WalletModel[size];
        }
    };
}
