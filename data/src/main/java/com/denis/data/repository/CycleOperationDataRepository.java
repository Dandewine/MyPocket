package com.denis.data.repository;

import com.denis.data.entity.mapper.CircleOperationDataMapper;
import com.denis.data.repository.datasource.interfaces.CycleOperationDataStore;
import com.denis.domain.models.CircleOperation;
import com.denis.domain.repository.CycleOperationRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class CycleOperationDataRepository implements CycleOperationRepository {

    private CircleOperationDataMapper dataMapper;
    private CycleOperationDataStore dataStore;

    @Inject
    public CycleOperationDataRepository(CycleOperationDataStore dataStore, CircleOperationDataMapper dataMapper) {
        this.dataStore = dataStore;
        this.dataMapper = dataMapper;
    }

    @Override
    public Observable<List<CircleOperation>> getCircleOperationList() {
        return dataStore.getListExpenseEntities()
                .map(dataMapper::transform);
    }

    @Override
    public Observable<CircleOperation> getCircleOperation(int userId) {
        return dataStore.getCircleOperationEntity(userId)
                .map(dataMapper::transform);
    }

    @Override
    public Observable<CircleOperation> addCircleOperation(CircleOperation circleOperation) {
        return dataStore.put(dataMapper.transform(circleOperation))
                .map(dataMapper::transform);
    }

    @Override
    public Observable<List<CircleOperation>> addCircleOperation(List<CircleOperation> circleOperations) {
        return dataStore.put(dataMapper.transform(circleOperations))
                .map(dataMapper::transform);
    }
}
