package com.denis.data.entity;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by denis on 1/5/16.
 */
public class WalletEntity extends RealmObject {

    @PrimaryKey  private String id;
    @SerializedName("walletName") private String name;
    private String currency;
    private float balance;
    private boolean isActive;

    public WalletEntity(String id, String name, String currency, float balance, boolean isActive) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.balance = balance;
        this.isActive = isActive;
    }

    public WalletEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
