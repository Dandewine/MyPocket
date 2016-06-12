package com.denis.mypocket.model;

import android.support.annotation.DrawableRes;

import com.denis.mypocket.R;

public class ExpenseCategoryModel {
    private String id;
    private String name;
    private int path;

    public ExpenseCategoryModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DrawableRes
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
}
