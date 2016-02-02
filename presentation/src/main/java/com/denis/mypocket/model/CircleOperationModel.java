package com.denis.mypocket.model;

public class CircleOperationModel {
    private int id;
    private TransactionModel transactionEntity;
    private String interval;

    public CircleOperationModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public TransactionModel getTransactionEntity() {
        return transactionEntity;
    }

    public void setTransactionEntity(TransactionModel transactionEntity) {
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
