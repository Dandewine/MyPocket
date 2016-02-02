package com.denis.data.repository;

import com.denis.data.entity.mapper.CircleOperationDataMapper;
import com.denis.data.repository.datasource.interfaces.CycleOperationDataStore;
import com.denis.domain.models.CycleOperation;
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
    public Observable<List<CycleOperation>> getCircleOperationList() {
        return dataStore.getListExpenseEntities()
                .map(dataMapper::transform);
    }

    @Override
    public Observable<CycleOperation> getCircleOperation(int userId) {
        return dataStore.getCircleOperationEntity(userId)
                .map(dataMapper::transform);
    }

    @Override
    public Observable<CycleOperation> addCircleOperation(CycleOperation cycleOperation) {
        return dataStore.put(dataMapper.transform(cycleOperation))
                .map(dataMapper::transform);
    }

    @Override
    public Observable<List<CycleOperation>> addCircleOperation(List<CycleOperation> cycleOperations) {
        return dataStore.put(dataMapper.transform(cycleOperations))
                .map(dataMapper::transform);
    }
}
