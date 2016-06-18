package com.denis.data.repository.datasource.local;

import com.denis.data.entity.IncomeCategoryEntity;
import com.denis.data.local_store.RealmStore;
import com.denis.data.repository.datasource.interfaces.IncomeCategoryDataStore;
import com.denis.domain.models.categories.Category;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;

public class IncomeCateforyLocalDataStore implements IncomeCategoryDataStore {
    private RealmStore<IncomeCategoryEntity> realmStore;

    @Inject
    public IncomeCateforyLocalDataStore(@Named("categories") RealmStore realmStore) {
        this.realmStore = realmStore;
    }

    @Override
    public Observable<IncomeCategoryEntity> getIncomeCategoryEntity(String categoryId) {
        return realmStore.get(categoryId);
    }

    @Override
    public Observable<List<IncomeCategoryEntity>> getListIncomeEntities() {
        return realmStore.getList();
    }

    @Override
    public Observable<IncomeCategoryEntity> put(IncomeCategoryEntity incomeCategoryEntity) {
        return realmStore.put(incomeCategoryEntity);
    }

    @Override
    public Observable<List<IncomeCategoryEntity>> put(List<IncomeCategoryEntity> collection) {
        return realmStore.put(collection);
    }
}
