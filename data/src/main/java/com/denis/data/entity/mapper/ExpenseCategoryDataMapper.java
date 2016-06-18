package com.denis.data.entity.mapper;

import com.denis.data.entity.ExpenseCategoryEntity;
import com.denis.domain.models.categories.ExpenseCategory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ExpenseCategoryDataMapper implements EntityMapper<ExpenseCategoryEntity, ExpenseCategory> {

    @Inject
    public ExpenseCategoryDataMapper() {
    }

    @Override
    public ExpenseCategory fromEntity(ExpenseCategoryEntity expenseCategoryEntity) {
        ExpenseCategory expenseCategory = null;
        if (expenseCategoryEntity != null) {
            expenseCategory = new ExpenseCategory(expenseCategoryEntity.getId());
            expenseCategory.setName(expenseCategoryEntity.getName());
            expenseCategory.setPath(expenseCategoryEntity.getPath());
        }
        return expenseCategory;
    }

    @Override
    public List<ExpenseCategory> fromEntity(List<ExpenseCategoryEntity> wasteCategoryEntities) {
        List<ExpenseCategory> wasteCategories = null;
        if (wasteCategoryEntities != null && !wasteCategoryEntities.isEmpty()) {
            wasteCategories = new ArrayList<>();
            for (ExpenseCategoryEntity wce : wasteCategoryEntities) {
                ExpenseCategory wc = fromEntity(wce);
                wasteCategories.add(wc);
            }
        }
        return wasteCategories;
    }

    @Override
    public ExpenseCategoryEntity toEntity(ExpenseCategory expenseCategory) {
        ExpenseCategoryEntity expenseCategoryEntity = null;
        if (expenseCategory != null) {
            expenseCategoryEntity = new ExpenseCategoryEntity(expenseCategory.getId());
            expenseCategoryEntity.setPath(expenseCategory.getPath());
            expenseCategoryEntity.setName(expenseCategory.getName());
        }
        return expenseCategoryEntity;
    }

    @Override
    public List<ExpenseCategoryEntity> toEntity(List<ExpenseCategory> wasteCategories) {
        List<ExpenseCategoryEntity> wasteCategoriesEntities = null;
        if (wasteCategories != null && !wasteCategories.isEmpty()) {
            wasteCategoriesEntities = new ArrayList<>();
            for (ExpenseCategory wc : wasteCategories) {
                ExpenseCategoryEntity wce = toEntity(wc);
                wasteCategoriesEntities.add(wce);
            }
        }
        return wasteCategoriesEntities;
    }
}
