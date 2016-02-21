package com.denis.domain.models;

/**
 * Created by denis on 2/21/16.
 */
public class MoneyBox {
    private int id;
    private String name;
    private float currentBalance;
    private float targetBalance;
    private long startDate;
    private long endDate;

    public MoneyBox(int id) {
        this.id = id;
    }

    public MoneyBox() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(float currentBalance) {
        this.currentBalance = currentBalance;
    }

    public float getTargetBalance() {
        return targetBalance;
    }

    public void setTargetBalance(float targetBalance) {
        this.targetBalance = targetBalance;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }
}
