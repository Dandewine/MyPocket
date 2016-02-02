package com.denis.domain.repository;

import com.denis.domain.models.CycleOperation;

import java.util.List;

import rx.Observable;

public interface CycleOperationRepository {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link CycleOperation}.
     */
    Observable<List<CycleOperation>> getCircleOperationList();

    /**
     * Get an {@link rx.Observable} which will emit a {@link CycleOperation}.
     *
     * @param userId The getCircleOperation id used to retrieve getCircleOperation data.
     */
    Observable<CycleOperation> getCircleOperation(final int userId);

    /**
     * Add CycleOperation into our local storage
     *
     * @param CycleOperation
     * @return
     */

    Observable<CycleOperation> addCircleOperation(CycleOperation CycleOperation);

    /**
     * Add CycleOperation's list to our local storage
     *
     * @param cycleOperations
     * @return
     */
    Observable<List<CycleOperation>> addCircleOperation(List<CycleOperation> cycleOperations);
}
