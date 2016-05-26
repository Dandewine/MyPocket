package com.denis.data.repository.datasource.local;

import com.denis.data.entity.ExpenseCategoryEntity;
import com.denis.data.local_store.RealmStore;
import com.denis.data.repository.datasource.interfaces.ExpenseCategoryDataStore;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class ExpenseCategoryLocalDataStore implements ExpenseCategoryDataStore {
    private RealmStore<ExpenseCategoryEntity> realmStore;

    @Inject
    public ExpenseCategoryLocalDataStore(RealmStore<ExpenseCategoryEntity> realmStore) {
        this.realmStore = realmStore;
    }

    @Override
    public Observable<ExpenseCategoryEntity> getExpenseCategoryEntity(String categoryId) {
        return realmStore.get(categoryId);
    }

    @Override
    public Observable<List<ExpenseCategoryEntity>> getListExpenseEntities() {
        return realmStore.getList();
    }

    @Override
    public Observable<ExpenseCategoryEntity> put(ExpenseCategoryEntity expenseCategoryEntity) {
        return realmStore.put(expenseCategoryEntity);
    }

    @Override
    public Observable<List<ExpenseCategoryEntity>> put(List<ExpenseCategoryEntity> list) {
        return realmStore.put(list);
    }
}
