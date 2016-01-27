package com.denis.data.entity.mapper;

import com.denis.data.entity.ExpenseCategoryEntity;
import com.denis.domain.models.ExpenseCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

public class WasteCategoryDataMapper {

    @Inject
    public WasteCategoryDataMapper() {
    }

    public ExpenseCategory transform(ExpenseCategoryEntity expenseCategoryEntity) {
        ExpenseCategory expenseCategory = null;
        if (expenseCategoryEntity != null) {
            expenseCategory = new ExpenseCategory(expenseCategoryEntity.getId());
            expenseCategory.setName(expenseCategoryEntity.getName());
            expenseCategory.setPath(expenseCategoryEntity.getPath());
        }
        return expenseCategory;
    }

    public List<ExpenseCategory> transform(Collection<ExpenseCategoryEntity> wasteCategoryEntities){
        List<ExpenseCategory> wasteCategories = null;
        if(wasteCategoryEntities != null && !wasteCategoryEntities.isEmpty()){
            wasteCategories = new ArrayList<>();
            for (ExpenseCategoryEntity wce:wasteCategoryEntities){
                ExpenseCategory wc = transform(wce);
                wasteCategories.add(wc);
            }
        }
        return wasteCategories;
    }

    public ExpenseCategoryEntity transform(ExpenseCategory expenseCategory){
        ExpenseCategoryEntity expenseCategoryEntity = null;
        if(expenseCategory != null){
            expenseCategoryEntity = new ExpenseCategoryEntity(expenseCategory.getId());
            expenseCategoryEntity.setPath(expenseCategory.getName());
            expenseCategoryEntity.setName(expenseCategory.getName());
        }
        return expenseCategoryEntity;
    }


    public List<ExpenseCategoryEntity> transform(List<ExpenseCategory> wasteCategories){
        List<ExpenseCategoryEntity> wasteCategoriesEntities = null;
        if(wasteCategories != null && !wasteCategories.isEmpty()){
            wasteCategoriesEntities = new ArrayList<>();
            for (ExpenseCategory wc:wasteCategories){
                ExpenseCategoryEntity wce = transform(wc);
                wasteCategoriesEntities.add(wce);
            }
        }
        return wasteCategoriesEntities;
    }
}
