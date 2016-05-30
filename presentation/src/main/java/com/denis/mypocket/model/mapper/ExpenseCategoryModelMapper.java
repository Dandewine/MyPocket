package com.denis.mypocket.model.mapper;

import com.denis.domain.models.ExpenseCategory;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.model.ExpenseCategoryModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class ExpenseCategoryModelMapper implements ModelMapper<ExpenseCategory,ExpenseCategoryModel> {

    @Inject
    public ExpenseCategoryModelMapper() {
    }

    public ExpenseCategoryModel toModel(ExpenseCategory expenseCategory) {
        ExpenseCategoryModel model = null;
        if (expenseCategory != null) {
            model = new ExpenseCategoryModel(expenseCategory.getId());
            model.setName(expenseCategory.getName());
            model.setPath(expenseCategory.getPath());
        }
        return model;
    }

    @Override
    public List<ExpenseCategoryModel> toModel(List<ExpenseCategory> expenseCategories) {
        List<ExpenseCategoryModel> modelList = null;
        if (expenseCategories != null && !expenseCategories.isEmpty()) {
            modelList = new ArrayList<>();
            for (ExpenseCategory ec : expenseCategories) {
                ExpenseCategoryModel cm = toModel(ec);
                modelList.add(cm);
            }
        }
        return modelList;
    }

    @Override
    public List<ExpenseCategory> fromModel(List<ExpenseCategoryModel> expenseCategoryModels) {
        return null;
    }

    @Override
    public ExpenseCategory fromModel(ExpenseCategoryModel expenseCategoryModel) {
        return null;
    }
}
