package com.denis.domain.models;

/**
 * Created by denis on 1/4/16.
 */
public class Wallet {
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
}
