package com.denis.data.repository.datasource.interfaces;

import com.denis.data.entity.CycleOperationEntity;

import java.util.Collection;
import java.util.List;

import rx.Observable;

public interface CycleOperationDataStore {
    /**
     * Get an {@link rx.Observable} which will emit a {@link CycleOperationEntity} by its id.
     *
     * @param categoryId The id to retrieve wallet data.
     */
    Observable<CycleOperationEntity> getCircleOperationEntity(final int categoryId);

    /**
     * Get an {@link rx.Observable} which will emit a List of {@link CycleOperationEntity}.
     */
    Observable<List<CycleOperationEntity>> getListExpenseEntities();

    /**
     * Put the {@link CycleOperationEntity} in the store
     *
     * @param CycleOperationEntity
     * @return {@link rx.Observable which will emit entity that was stored }
     */
    Observable<CycleOperationEntity> put(CycleOperationEntity CycleOperationEntity);

    /**
     * Put the {@link Collection<CycleOperationEntity>} in the store
     *
     * @param collection
     * @return {@link rx.Observable<List<CycleOperationEntity>>}
     */
    Observable<List<CycleOperationEntity>> put(Collection<CycleOperationEntity> collection);
}
