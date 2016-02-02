package com.denis.domain.repository;

import com.denis.domain.models.CircleOperation;

import java.util.List;

import rx.Observable;

public interface CycleOperationRepository {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link CircleOperation}.
     */
    Observable<List<CircleOperation>> getCircleOperationList();

    /**
     * Get an {@link rx.Observable} which will emit a {@link CircleOperation}.
     *
     * @param userId The getCircleOperation id used to retrieve getCircleOperation data.
     */
    Observable<CircleOperation> getCircleOperation(final int userId);

    /**
     * Add CircleOperation into our local storage
     *
     * @param CircleOperation
     * @return
     */

    Observable<CircleOperation> addCircleOperation(CircleOperation CircleOperation);

    /**
     * Add CircleOperation's list to our local storage
     *
     * @param CircleOperations
     * @return
     */
    Observable<List<CircleOperation>> addCircleOperation(List<CircleOperation> CircleOperations);
}
