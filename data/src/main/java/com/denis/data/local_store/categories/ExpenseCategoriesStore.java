package com.denis.data.local_store.categories;

import com.denis.data.entity.ExpenseCategoryEntity;
import com.denis.data.local_store.RealmStore;
import com.denis.domain.models.categories.Category;

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
        return Observable.just(realm.where(ExpenseCategoryEntity.class).equalTo("id", id).findFirst());
    }

    @Override
    public Observable<ExpenseCategoryEntity> put(ExpenseCategoryEntity item) {
        realm.beginTransaction();
        ExpenseCategoryEntity entity = realm.copyToRealmOrUpdate(item);
        realm.commitTransaction();
        return Observable.just(entity);
    }

    @Override
    public Observable<List<ExpenseCategoryEntity>> put(List<ExpenseCategoryEntity> list) {
        realm.beginTransaction();
        List<ExpenseCategoryEntity> entities = realm.copyToRealmOrUpdate(list);
        realm.commitTransaction();
        return Observable.just(entities);
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
