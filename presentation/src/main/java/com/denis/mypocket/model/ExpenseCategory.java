package com.denis.mypocket.model;

import com.denis.mypocket.R;

/**
 * @author Denis_Zinkovskiy at 6/11/16.
 */

public enum ExpenseCategory {
    FOOD_AND_BEVERAGE(R.drawable.svg_food, "Food & Beverage"),
    SHOPPING_AND_CLOTHES(R.drawable.svg_shopping, "Shopping & Clothes"),
    HEALTH_AND_MEDICINE(R.drawable.svg_medicine, "Health & Medicine"),
    GYM(R.drawable.svg_gym, "Sport & Gym"),
    INSURANCE(R.drawable.svg_insurance, "Insurance"),
    EDUCATION(R.drawable.svg_education, "Education"),
    GIFTS_AND_PRESENTS(R.drawable.svg_gifts, "Gifts and presents"),
    TRAVEL(R.drawable.svg_travel, "Travel"),
    LOVE(R.drawable.svg_heart, "Love"),
    ENTERTAINMENT(R.drawable.svg_entertaiment, "Entertainment"),
    INVESTMENT(R.drawable.svg_investment, "Investment"),
    REPAIRS(R.drawable.svg_repair, "Repairs"),
    TRANSPORT(R.drawable.svg_transport, "Transport"),
    BILLS_AND_UTILITIES(R.drawable.svg_bills, "Bills and Utilities"),
    RENT(R.drawable.svg_rent,"Rent"),
    FAMILY(R.drawable.svg_family,"Family"),
    OTHER(R.drawable.svg_other,"Other");

    private int drawable;
    private String categoryName;

    ExpenseCategory(int drawable, String categoryName) {
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
