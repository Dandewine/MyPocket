package com.denis.domain.repository;

import com.denis.domain.models.categories.Category;
import com.denis.domain.models.categories.ExpenseCategory;

import java.util.List;

import rx.Observable;

public interface ExpenseCategoriesRepository {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link ExpenseCategory}.
     */
    Observable<List<ExpenseCategory>> getExpenseCategoryList();

    /**
     * Get an {@link rx.Observable} which will emit a {@link ExpenseCategory}.
     *
     * @param userId The getExpenseCategory id used to retrieve getExpenseCategory data.
     */
    Observable<ExpenseCategory> getExpenseCategory(final String userId);

    /**
     * Add ExpenseCategory into our local storage
     * @param expenseCategory
     * @return
     */

    Observable<ExpenseCategory> addExpenseCategory(ExpenseCategory expenseCategory);

    /**
     * Add ExpenseCategory's list to our local storage
     * @param expenseCategorys
     * @return
     */
    Observable<List<ExpenseCategory>> addExpenseCategory(List<ExpenseCategory> expenseCategorys);
}
