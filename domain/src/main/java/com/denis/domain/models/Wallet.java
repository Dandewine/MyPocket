package com.denis.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by denis on 1/4/16.
 */
public class Wallet implements Parcelable {
    private int id;
    private String name;
    private String currency;
    private float balance;

    public Wallet(int id, String name, String currency, float balance) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.balance = balance;
    }

    public Wallet(int id) {
        this.id = id;
    }

    public int getId() {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.currency);
        dest.writeFloat(this.balance);
    }

    protected Wallet(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
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
