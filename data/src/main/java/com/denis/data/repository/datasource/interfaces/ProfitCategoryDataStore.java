package com.denis.data.repository.datasource.interfaces;

import com.denis.data.entity.ProfitCategoryEntity;

import java.util.Collection;
import java.util.List;

import rx.Observable;

public interface ProfitCategoryDataStore {
    /**
     *Get an {@link rx.Observable} which will emit a {@link ProfitCategoryEntity} by its id.
     * @param categoryId The id to retrieve wallet data.
     */
    Observable<ProfitCategoryEntity> getProfitCategoryEntity(final int categoryId);
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link ProfitCategoryEntity}.
     */
    Observable<List<ProfitCategoryEntity>> getListWalletEntities();

    Observable<ProfitCategoryEntity> put(ProfitCategoryEntity ProfitCategoryEntity);
    Observable<ProfitCategoryEntity> put(Collection<ProfitCategoryEntity> collection);
}
