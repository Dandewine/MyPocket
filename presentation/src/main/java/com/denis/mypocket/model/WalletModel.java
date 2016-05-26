package com.denis.mypocket.model;

public class WalletModel{

    private String id;
    private String name;
    private String currency;
    private float balance;

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
}
