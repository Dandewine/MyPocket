package com.denis.data.local_store.categories;

import com.denis.data.entity.ExpenseCategoryEntity;
import com.denis.data.local_store.RealmStore;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;

public class ExpenseCategoriesStore implements RealmStore<ExpenseCategoryEntity> {
    private Realm mRealm;

    @Inject
    public ExpenseCategoriesStore(Realm mRealm) {
        this.mRealm = mRealm;
    }

    @Override
    public Observable<ExpenseCategoryEntity> get(String id) {
        return Observable.just(mRealm.where(ExpenseCategoryEntity.class).equalTo("id",id).findFirst());
    }

    @Override
    public Observable<ExpenseCategoryEntity> put(ExpenseCategoryEntity item) {
        Number max = mRealm.where(ExpenseCategoryEntity.class).max("id");
        int nextId = max == null ? 0 : max.intValue() + 1;
        item.setId(nextId);
        final ExpenseCategoryEntity[] entity = {null};
        mRealm.executeTransaction(realm -> entity[0] = realm.copyToRealmOrUpdate(item));
        return Observable.just(entity[0]);
    }

    @Override
    public Observable<List<ExpenseCategoryEntity>> put(Collection<ExpenseCategoryEntity> collection) {
        throw new UnsupportedOperationException("I can't do this, yet");
    }

    @Override
    public Observable<List<ExpenseCategoryEntity>> getList() {
        return Observable.just(mRealm.where(ExpenseCategoryEntity.class).findAllSorted("id"));
    }

    @Override
    public Observable<ExpenseCategoryEntity> update(ExpenseCategoryEntity item) {
        return Observable.just(mRealm.copyToRealmOrUpdate(item));
    }
}
