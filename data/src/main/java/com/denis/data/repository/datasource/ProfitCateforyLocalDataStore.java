package com.denis.data.repository.datasource;

import com.denis.data.entity.ProfitCategoryEntity;
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
    public Observable<ProfitCategoryEntity> getProfitCategoryEntity(int categoryId) {
        return realmStore.get(categoryId);
    }

    @Override
    public Observable<List<ProfitCategoryEntity>> getListWalletEntities() {
        return realmStore.getList();
    }

    @Override
    public Observable<ProfitCategoryEntity> put(ProfitCategoryEntity profitCategoryEntity) {
        return realmStore.put(profitCategoryEntity);
    }

    @Override
    public Observable<ProfitCategoryEntity> put(Collection<ProfitCategoryEntity> collection) {
        return realmStore.put(collection);
    }
}
