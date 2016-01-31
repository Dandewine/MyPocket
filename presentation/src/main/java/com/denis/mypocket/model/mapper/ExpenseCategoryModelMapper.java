package com.denis.mypocket.model.mapper;

import com.denis.domain.models.ExpenseCategory;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.model.ExpenseCategoryModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class ExpenseCategoryModelMapper {

    @Inject
    public ExpenseCategoryModelMapper() {
    }

    public ExpenseCategoryModel transform(ExpenseCategory expenseCategory) {
        ExpenseCategoryModel model = null;
        if (expenseCategory != null) {
            model = new ExpenseCategoryModel(expenseCategory.getId());
            model.setName(expenseCategory.getName());
            model.setPath(expenseCategory.getPath());
        }
        return model;
    }

    public List<ExpenseCategoryModel> transform(List<ExpenseCategory> expenseCategories) {
        List<ExpenseCategoryModel> modelList = null;
        if (expenseCategories != null && !expenseCategories.isEmpty()) {
            modelList = new ArrayList<>();
            for (ExpenseCategory ec : expenseCategories) {
                ExpenseCategoryModel cm = transform(ec);
                modelList.add(cm);
            }
        }
        return modelList;
    }


}
