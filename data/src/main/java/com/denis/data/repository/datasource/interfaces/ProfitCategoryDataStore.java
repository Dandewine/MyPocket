package com.denis.data.repository.datasource.interfaces;

import com.denis.data.entity.IncomeCategoryEntity;

import java.util.Collection;
import java.util.List;

import rx.Observable;

public interface ProfitCategoryDataStore {
    /**
     *Get an {@link rx.Observable} which will emit a {@link IncomeCategoryEntity} by its id.
     * @param categoryId The id to retrieve wallet data.
     */
    Observable<IncomeCategoryEntity> getProfitCategoryEntity(final int categoryId);
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link IncomeCategoryEntity}.
     */
    Observable<List<IncomeCategoryEntity>> getListWalletEntities();

    Observable<IncomeCategoryEntity> put(IncomeCategoryEntity IncomeCategoryEntity);
    Observable<IncomeCategoryEntity> put(Collection<IncomeCategoryEntity> collection);
}
