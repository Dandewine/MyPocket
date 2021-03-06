package com.denis.data.repository;

import com.denis.data.entity.mapper.ExpenseCategoryDataMapper;
import com.denis.data.repository.datasource.interfaces.ExpenseCategoryDataStore;
import com.denis.domain.models.ExpenseCategory;
import com.denis.domain.repository.ExpenseCategoriesRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class ExpenseCategoryDataRepository implements ExpenseCategoriesRepository{

    private ExpenseCategoryDataMapper dataMapper;
    private ExpenseCategoryDataStore dataStore;

    @Inject
    public ExpenseCategoryDataRepository(ExpenseCategoryDataMapper dataMapper, ExpenseCategoryDataStore dataStore) {
        this.dataMapper = dataMapper;
        this.dataStore = dataStore;
    }

    @Override
    public Observable<List<ExpenseCategory>> getExpenseCategoryList() {
        return dataStore.getListExpenseEntities()
                .map(dataMapper::fromEntity);
    }

    @Override
    public Observable<ExpenseCategory> getExpenseCategory(String categoryId) {
        return dataStore.getExpenseCategoryEntity(categoryId)
                .map(dataMapper::fromEntity);
    }

    @Override
    public Observable<ExpenseCategory> addExpenseCategory(ExpenseCategory expenseCategory) {
        return dataStore.put(dataMapper.toEntity(expenseCategory))
                .map(dataMapper::fromEntity);
    }

    @Override
    public Observable<List<ExpenseCategory>> addExpenseCategory(List<ExpenseCategory> expenseCategorys) {
        return dataStore.put(dataMapper.toEntity(expenseCategorys))
                .map(dataMapper::fromEntity);
    }
}
