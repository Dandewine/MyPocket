package com.denis.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CycleOperation implements Parcelable {
    private int id;
    private Transaction transactionEntity;
    private String name;
    private String interval;
    private long triggerTime; //trigger time in unix

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

    public long getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(long triggerTime) {
        this.triggerTime = triggerTime;
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
                ", transactionId = " + (transactionEntity == null ? "null" : String.valueOf(transactionEntity.getId())) +
                ", triggerTime = " + triggerTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeParcelable(this.transactionEntity, flags);
        dest.writeString(this.name);
        dest.writeString(this.interval);
        dest.writeLong(this.triggerTime);
    }

    protected CycleOperation(Parcel in) {
        this.id = in.readInt();
        this.transactionEntity = in.readParcelable(Transaction.class.getClassLoader());
        this.name = in.readString();
        this.interval = in.readString();
        this.triggerTime = in.readLong();
    }

    public static final Parcelable.Creator<CycleOperation> CREATOR = new Parcelable.Creator<CycleOperation>() {
        public CycleOperation createFromParcel(Parcel source) {
            return new CycleOperation(source);
        }

        public CycleOperation[] newArray(int size) {
            return new CycleOperation[size];
        }
    };
}
