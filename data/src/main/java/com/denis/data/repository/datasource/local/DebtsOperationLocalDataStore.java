package com.denis.data.repository.datasource.local;

import com.denis.data.entity.DebtEntity;
import com.denis.data.local_store.RealmStore;
import com.denis.data.repository.datasource.interfaces.DebtsDataStore;
import com.denis.domain.models.categories.Category;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by denis on 2/20/16.
 */
public class DebtsOperationLocalDataStore implements DebtsDataStore {

    private RealmStore<DebtEntity> realmStore;

    @Inject
    public DebtsOperationLocalDataStore(RealmStore<DebtEntity> realmStore) {
        this.realmStore = realmStore;
    }

    @Override
    public Observable<DebtEntity> getDebtEntity(String categoryId) {
        return realmStore.get(categoryId);
    }

    @Override
    public Observable<List<DebtEntity>> getListExpenseEntities() {
        return realmStore.getList();
    }

    @Override
    public Observable<DebtEntity> put(DebtEntity debtEntity) {
        return realmStore.put(debtEntity);
    }

    @Override
    public Observable<List<DebtEntity>> put(List<DebtEntity> list) {
        return realmStore.put(list);
    }
}
