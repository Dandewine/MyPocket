package com.denis.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by denis on 1/4/16.
 */
public class Wallet implements Parcelable {

    private String id;
    private String walletName;
    private String currency;
    private float balance;

    public Wallet(String id, String name, String currency, float balance) {
        this.id = id;
        this.walletName = name;
        this.currency = currency;
        this.balance = balance;
    }

    public Wallet(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.walletName);
        dest.writeString(this.currency);
        dest.writeFloat(this.balance);
    }

    protected Wallet(Parcel in) {
        this.id = in.readString();
        this.walletName = in.readString();
        this.currency = in.readString();
        this.balance = in.readFloat();
    }

    public static final Parcelable.Creator<Wallet> CREATOR = new Parcelable.Creator<Wallet>() {
        public Wallet createFromParcel(Parcel source) {
            return new Wallet(source);
        }

        public Wallet[] newArray(int size) {
            return new Wallet[size];
        }
    };
}
