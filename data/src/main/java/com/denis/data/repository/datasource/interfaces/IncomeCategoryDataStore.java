package com.denis.data.repository.datasource.interfaces;

import com.denis.data.entity.IncomeCategoryEntity;

import java.util.List;

import rx.Observable;

public interface IncomeCategoryDataStore {
    /**
     *Get an {@link rx.Observable} which will emit a {@link IncomeCategoryEntity} by its id.
     * @param categoryId The id to retrieve wallet data.
     */
    Observable<IncomeCategoryEntity> getIncomeCategoryEntity(final String categoryId);
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link IncomeCategoryEntity}.
     */
    Observable<List<IncomeCategoryEntity>> getListIncomeEntities();

    Observable<IncomeCategoryEntity> put(IncomeCategoryEntity IncomeCategoryEntity);
    Observable<List<IncomeCategoryEntity>> put(List<IncomeCategoryEntity> collection);
}
