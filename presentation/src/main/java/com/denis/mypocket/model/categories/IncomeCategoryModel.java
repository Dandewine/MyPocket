package com.denis.mypocket.model.categories;

import android.os.Parcel;
import android.support.annotation.DrawableRes;

import com.denis.mypocket.R;
import com.denis.mypocket.model.IncomeCategory;

public class IncomeCategoryModel implements CategoryModel {
    private String id;
    private String name;
    private int path;

    public IncomeCategoryModel(String id) {
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
        int d = R.drawable.green_oval;
        for (IncomeCategory category: IncomeCategory.values()) {
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

    private IncomeCategoryModel(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.path = in.readInt();
    }

    public static final Creator<IncomeCategoryModel> CREATOR = new Creator<IncomeCategoryModel>() {
        @Override
        public IncomeCategoryModel createFromParcel(Parcel source) {
            return new IncomeCategoryModel(source);
        }

        @Override
        public IncomeCategoryModel[] newArray(int size) {
            return new IncomeCategoryModel[size];
        }
    };
}
