package com.denis.data.entity;

import com.denis.domain.models.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by denis on 4/28/16.
 */
public class LoginResponseEntity {
    @SerializedName("user") private User user;
    @SerializedName("token") private String token;
    @SerializedName("expenseCategories") private List<ExpenseCategoryEntity> expenseCategoryEntities;
    @SerializedName("incomeCategories") private List<IncomeCategoryEntity> incomeCategoryEntities;

    public LoginResponseEntity(String token) {
        this.token = token;
    }

    public LoginResponseEntity() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ExpenseCategoryEntity> getExpenseCategoryEntities() {
        return expenseCategoryEntities;
    }

    public void setExpenseCategoryEntities(List<ExpenseCategoryEntity> expenseCategoryEntities) {
        this.expenseCategoryEntities = expenseCategoryEntities;
    }

    public List<IncomeCategoryEntity> getIncomeCategoryEntities() {
        return incomeCategoryEntities;
    }

    public void setIncomeCategoryEntities(List<IncomeCategoryEntity> incomeCategoryEntities) {
        this.incomeCategoryEntities = incomeCategoryEntities;
    }
}
