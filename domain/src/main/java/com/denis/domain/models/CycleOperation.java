package com.denis.domain.models;

public class CycleOperation {
    private int id;
    private Transaction transactionEntity;
    private String name;
    private String interval;

    public CycleOperation(int id) {
        this.id = id;
    }

    public CycleOperation(Transaction transactionEntity, String name, String interval) {
        this.transactionEntity = transactionEntity;
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

    @Override
    public String toString() {
        return "id = " + id +
                ", name = " + name +
                ", interval = " + interval +
                ", transactionId = " + transactionEntity.getId();
    }
}
