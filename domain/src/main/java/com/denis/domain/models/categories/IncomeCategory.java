package com.denis.domain.models.categories;

import android.os.Parcel;

public class IncomeCategory implements Category {
    private String id;
    private String name;
    private int path;

    public IncomeCategory(String id) {
        this.id = id;
    }

    public IncomeCategory(String id, String name, int path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.path);
    }

    protected IncomeCategory(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.path = in.readInt();
    }

    public static final Creator<IncomeCategory> CREATOR = new Creator<IncomeCategory>() {
        @Override
        public IncomeCategory createFromParcel(Parcel source) {
            return new IncomeCategory(source);
        }

        @Override
        public IncomeCategory[] newArray(int size) {
            return new IncomeCategory[size];
        }
    };
}
