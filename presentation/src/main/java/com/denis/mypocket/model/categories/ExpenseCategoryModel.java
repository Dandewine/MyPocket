package com.denis.mypocket.model.categories;

import android.os.Parcel;
import android.support.annotation.DrawableRes;

import com.denis.mypocket.R;
import com.denis.mypocket.model.ExpenseCategory;

public class ExpenseCategoryModel implements CategoryModel {
    private String id;
    private String name;
    private int path;

    public ExpenseCategoryModel(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DrawableRes @Override
    public int getPath() {
        int d = R.drawable.svg_beer;
        for (ExpenseCategory category: ExpenseCategory.values()) {
            if (category.getCategoryName().equals(name))
                d = category.getDrawable();
        }
        return d;
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

    protected ExpenseCategoryModel(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.path = in.readInt();
    }

    public static final Creator<ExpenseCategoryModel> CREATOR = new Creator<ExpenseCategoryModel>() {
        @Override
        public ExpenseCategoryModel createFromParcel(Parcel source) {
            return new ExpenseCategoryModel(source);
        }

        @Override
        public ExpenseCategoryModel[] newArray(int size) {
            return new ExpenseCategoryModel[size];
        }
    };
}
