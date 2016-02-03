package com.denis.domain.models;

public class CycleOperation {
    private int id;
    private Transaction transactionEntity;
    private String name;
    private String interval;

    public CycleOperation(int id) {
        this.id = id;
    }

    public CycleOperation(Transaction transaction, String name, String interval) {
        this.transactionEntity = transaction;
        this.name = name;
        this.interval = interval;
    }

    public CycleOperation(int id, Transaction transaction, String name, String interval) {
        this.id = id;
        this.transactionEntity = transaction;
        this.name = name;
        this.interval = interval;
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

    public Transaction getTransaction() {
        return transactionEntity;
    }

    public void setTransaction(Transaction transaction) {
        this.transactionEntity = transaction;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public enum CircleTypes {
        YEAR("365"),
        MONTH("30"),
        WEEK("7"),
        DAY("24"),
        CUSTOM("CUSTOM");

        CircleTypes(String mValue) {
            this.mValue = mValue;
        }

        private String mValue;

        public String getValue() {
            return mValue;
        }
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", name = " + name +
                ", interval = " + interval +
                ", transactionId = " + (transactionEntity == null ? "null" : String.valueOf(transactionEntity.getId()));
    }
}
