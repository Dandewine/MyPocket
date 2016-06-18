package com.denis.domain.models;

import com.denis.domain.models.categories.ExpenseCategory;
import com.denis.domain.models.categories.IncomeCategory;

import java.util.List;

/**
 * Created by denis on 4/28/16.
 */
public class LoginResponse {
    private User user;
    private String token;
    private List<IncomeCategory> incomeCategories;
    private List<ExpenseCategory> expenseCategories;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<IncomeCategory> getIncomeCategories() {
        return incomeCategories;
    }

    public void setIncomeCategories(List<IncomeCategory> incomeCategories) {
        this.incomeCategories = incomeCategories;
    }

    public List<ExpenseCategory> getExpenseCategories() {
        return expenseCategories;
    }

    public void setExpenseCategories(List<ExpenseCategory> expenseCategories) {
        this.expenseCategories = expenseCategories;
    }
}
