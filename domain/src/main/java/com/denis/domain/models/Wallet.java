package com.denis.domain.models;

/**
 * Created by denis on 1/4/16.
 */
public class Wallet {
    private long id;
    private String name;
    private String currency;
    private float balance;

    public Wallet(long id) {
        this.id = id;
    }

    public long getId() {
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
