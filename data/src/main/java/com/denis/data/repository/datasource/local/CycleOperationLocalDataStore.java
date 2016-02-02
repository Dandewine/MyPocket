package com.denis.data.repository.datasource.local;

import com.denis.data.entity.CycleOperationEntity;
import com.denis.data.repository.datasource.interfaces.CycleOperationDataStore;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class CycleOperationLocalDataStore implements CycleOperationDataStore {

    private CycleOperationDataStore dataStore;

    @Inject
    public CycleOperationLocalDataStore(CycleOperationDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Observable<CycleOperationEntity> getCircleOperationEntity(int categoryId) {
        return dataStore.getCircleOperationEntity(categoryId);
    }

    @Override
    public Observable<List<CycleOperationEntity>> getListExpenseEntities() {
        return dataStore.getListExpenseEntities();
    }

    @Override
    public Observable<CycleOperationEntity> put(CycleOperationEntity cycleOperationEntity) {
        return dataStore.put(cycleOperationEntity);
    }

    @Override
    public Observable<List<CycleOperationEntity>> put(Collection<CycleOperationEntity> collection) {
        return dataStore.put(collection);
    }
}
