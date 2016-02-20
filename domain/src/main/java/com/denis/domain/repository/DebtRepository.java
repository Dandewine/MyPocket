package com.denis.domain.repository;

import com.denis.domain.models.Debt;


import java.util.List;

import rx.Observable;

/**
 * Created by denis on 2/20/16.
 */
public interface DebtRepository {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link Debt}.
     */
    Observable<List<Debt>> getDebtList();

    /**
     * Get an {@link rx.Observable} which will emit a {@link Debt}.
     *
     * @param userId The getDebt id used to retrieve getDebt data.
     */
    Observable<Debt> getDebt(final int userId);

    /**
     * Add Debt into our local storage
     * @param debt
     * @return
     */

    Observable<Debt> addDebt(Debt debt);

    /**
     * Add Debt's list to our local storage
     * @param Debts
     * @return
     */
    Observable<List<Debt>> addDebt(List<Debt> Debts);
}
