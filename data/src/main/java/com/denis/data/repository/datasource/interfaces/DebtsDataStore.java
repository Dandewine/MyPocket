package com.denis.data.repository.datasource.interfaces;

import com.denis.data.entity.DebtEntity;
import com.denis.data.entity.DebtEntity;

import java.util.Collection;
import java.util.List;

import rx.Observable;

/**
 * Created by denis on 2/20/16.
 */
public interface DebtsDataStore {
    /**
     *Get an {@link rx.Observable} which will emit a {@link DebtEntity} by its id.
     * @param categoryId The id to retrieve wallet data.
     */
    Observable<DebtEntity> getDebtEntity(final int categoryId);
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link DebtEntity}.
     */
    Observable<List<DebtEntity>> getListExpenseEntities();

    Observable<DebtEntity> put(DebtEntity DebtEntity);
    Observable<List<DebtEntity>> put(Collection<DebtEntity> collection);
}
