package com.denis.data.local_store.categories;

import com.denis.data.entity.ExpenseCategoryEntity;
import com.denis.data.local_store.RealmStore;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;

public class ExpenseCategoriesStore implements RealmStore<ExpenseCategoryEntity> {
    private Realm realm;

    @Inject
    public ExpenseCategoriesStore(Realm mRealm) {
        this.realm = mRealm;
    }

    @Override
    public Observable<ExpenseCategoryEntity> get(String id) {
        return Observable.just(realm.where(ExpenseCategoryEntity.class).equalTo("id",id).findFirst());
    }

    @Override
    public Observable<ExpenseCategoryEntity> put(ExpenseCategoryEntity item) {
        Number max = realm.where(ExpenseCategoryEntity.class).max("id");
        int nextId = max == null ? 0 : max.intValue() + 1;
        item.setId(nextId);
        final ExpenseCategoryEntity[] entity = {null};
        realm.executeTransaction(realm -> entity[0] = realm.copyToRealmOrUpdate(item));
        return Observable.just(entity[0]);
    }

    @Override
    public Observable<List<ExpenseCategoryEntity>> put(List<ExpenseCategoryEntity> list) {
        throw new UnsupportedOperationException("I can't do this, yet");
    }

    @Override
    public Observable<List<ExpenseCategoryEntity>> getList() {
        return Observable.just(realm.where(ExpenseCategoryEntity.class).findAllSorted("id"));
    }

    @Override
    public Observable<ExpenseCategoryEntity> update(ExpenseCategoryEntity item) {
        return Observable.just(realm.copyToRealmOrUpdate(item));
    }

    @Override
    public Observable delete(ExpenseCategoryEntity item) {
        return null;
    }
}
