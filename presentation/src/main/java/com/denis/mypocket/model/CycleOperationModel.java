package com.denis.mypocket.model;

public class CycleOperationModel {
    private int id;
    private TransactionModel transactionModel;
    private String interval;
    private String name;
    private long triggerTime;

    public CycleOperationModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public TransactionModel getTransactionModel() {
        return transactionModel;
    }

    public void setTransactionModel(TransactionModel transactionModel) {
        this.transactionModel = transactionModel;
    }

    public long getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(long triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
