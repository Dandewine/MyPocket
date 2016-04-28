package com.denis.data.repository.datasource.interfaces;

import com.denis.data.entity.ExpenseCategoryEntity;

import java.util.Collection;
import java.util.List;

import rx.Observable;

public interface ExpenseCategoryDataStore {
    /**
     *Get an {@link rx.Observable} which will emit a {@link ExpenseCategoryEntity} by its id.
     * @param categoryId The id to retrieve wallet data.
     */
    Observable<ExpenseCategoryEntity> getExpenseCategoryEntity(final String categoryId);
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link ExpenseCategoryEntity}.
     */
    Observable<List<ExpenseCategoryEntity>> getListExpenseEntities();

    Observable<ExpenseCategoryEntity> put(ExpenseCategoryEntity ExpenseCategoryEntity);
    Observable<List<ExpenseCategoryEntity>> put(Collection<ExpenseCategoryEntity> collection);
}
