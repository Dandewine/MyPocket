package com.denis.data.repository.datasource;

import com.denis.data.entity.IncomeCategoryEntity;
import com.denis.data.local_store.RealmStore;
import com.denis.data.repository.datasource.interfaces.ProfitCategoryDataStore;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class ProfitCateforyLocalDataStore implements ProfitCategoryDataStore {
    private RealmStore realmStore;

    @Inject
    public ProfitCateforyLocalDataStore(RealmStore realmStore) {
        this.realmStore = realmStore;
    }

    @Override
    public Observable<IncomeCategoryEntity> getProfitCategoryEntity(int categoryId) {
        return realmStore.get(categoryId);
    }

    @Override
    public Observable<List<IncomeCategoryEntity>> getListWalletEntities() {
        return realmStore.getList();
    }

    @Override
    public Observable<IncomeCategoryEntity> put(IncomeCategoryEntity incomeCategoryEntity) {
        return realmStore.put(incomeCategoryEntity);
    }

    @Override
    public Observable<IncomeCategoryEntity> put(Collection<IncomeCategoryEntity> collection) {
        return realmStore.put(collection);
    }
}
