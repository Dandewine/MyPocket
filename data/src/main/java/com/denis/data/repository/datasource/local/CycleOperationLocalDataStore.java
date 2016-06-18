package com.denis.data.repository.datasource.local;

import com.denis.data.entity.CycleOperationEntity;
import com.denis.data.local_store.CircleOperationRealmStore;
import com.denis.data.repository.datasource.interfaces.CycleOperationDataStore;
import com.denis.domain.models.categories.Category;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class CycleOperationLocalDataStore implements CycleOperationDataStore {

    private CircleOperationRealmStore dataStore;

    @Inject
    public CycleOperationLocalDataStore(CircleOperationRealmStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Observable<CycleOperationEntity> getCircleOperationEntity(String id) {
        return dataStore.get(id);
    }

    @Override
    public Observable<List<CycleOperationEntity>> getListExpenseEntities() {
        return dataStore.getList();
    }

    @Override
    public Observable<CycleOperationEntity> put(CycleOperationEntity cycleOperationEntity) {
        return dataStore.put(cycleOperationEntity);
    }

    @Override
    public Observable<List<CycleOperationEntity>> put(List<CycleOperationEntity> list) {
        return dataStore.put(list);
    }
}
