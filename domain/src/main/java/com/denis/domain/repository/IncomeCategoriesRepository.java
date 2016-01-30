package com.denis.domain.repository;

import com.denis.domain.models.IncomeCategory;

import java.util.List;

import rx.Observable;

public interface IncomeCategoriesRepository {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link IncomeCategory}.
     */
    Observable<List<IncomeCategory>> getIncomeCategoryList();

    /**
     * Get an {@link rx.Observable} which will emit a {@link IncomeCategory}.
     *
     * @param userId The getIncomeCategory id used to retrieve getIncomeCategory data.
     */
    Observable<IncomeCategory> getIncomeCategory(final int userId);

    /**
     * Add IncomeCategory into our local storage
     * @param incomeCategory
     * @return
     */

    Observable<IncomeCategory> addIncomeCategory(IncomeCategory incomeCategory);

    /**
     * Add IncomeCategory's list to our local storage
     * @param incomeCategorys
     * @return
     */
    Observable<List<IncomeCategory>> addIncomeCategory(List<IncomeCategory> incomeCategorys);
}
