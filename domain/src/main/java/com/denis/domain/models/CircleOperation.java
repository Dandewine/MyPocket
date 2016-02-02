package com.denis.domain.models;

public class CircleOperation {
    private int id;
    private Transaction transactionEntity;
    private String interval;

    public CircleOperation(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }


    public Transaction getTransactionEntity() {
        return transactionEntity;
    }

    public void setTransactionEntity(Transaction transactionEntity) {
        this.transactionEntity = transactionEntity;
    }

    public CircleTypes getInterval() {
        return CircleTypes.valueOf(interval);
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
}
