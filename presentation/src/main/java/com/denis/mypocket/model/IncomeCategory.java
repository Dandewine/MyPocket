package com.denis.mypocket.model;

import com.denis.mypocket.R;

/**
 * @author Denis_Zinkovskiy at 6/11/16.
 */

public enum IncomeCategory {
    SELLING(R.drawable.svg_selling, "Selling"),
    SALARY(R.drawable.svg_salary, "Salary"),
    GIFTS(R.drawable.svg_gifts, "Gifts and presents"),
    AWARD(R.drawable.svg_award, "Awards"),
    INTEREST_MONEY(R.drawable.svg_coins, "Interest money"),
    OTHER(R.drawable.svg_other, "Other");

    private int drawable;
    private String categoryName;

    IncomeCategory(int drawable, String categoryName) {
        this.drawable = drawable;
        this.categoryName = categoryName;
    }

    public int getDrawable() {
        return drawable;
    }

    public String getCategoryName() {
        return categoryName;
    }

}
