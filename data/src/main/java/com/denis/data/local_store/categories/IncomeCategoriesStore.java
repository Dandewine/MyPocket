package com.denis.data.local_store.categories;

import com.denis.data.entity.IncomeCategoryEntity;
import com.denis.data.local_store.RealmStore;
import com.denis.domain.models.categories.Category;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;


public class IncomeCategoriesStore implements RealmStore<IncomeCategoryEntity> {

    private Realm realm;

    @Inject
    public IncomeCategoriesStore(Realm realm) {
        this.realm = realm;
    }

    @Override
    public Observable<IncomeCategoryEntity> get(String id) {
        return Observable.just(realm.where(IncomeCategoryEntity.class).equalTo("id", id).findFirst());
    }

    @Override
    public Observable<List<IncomeCategoryEntity>> getList() {
        return Observable.just(realm.where(IncomeCategoryEntity.class).findAllSorted("id"));
    }

    @Override
    public Observable<IncomeCategoryEntity> put(IncomeCategoryEntity item) {
        realm.beginTransaction();
        IncomeCategoryEntity entity = realm.copyToRealmOrUpdate(item);
        realm.commitTransaction();
        return Observable.just(entity);
    }

    @Override
    public Observable<List<IncomeCategoryEntity>> put(List<IncomeCategoryEntity> list) {
        realm.beginTransaction();
        List<IncomeCategoryEntity> entities = realm.copyToRealmOrUpdate(list);
        realm.commitTransaction();
        return Observable.just(entities);
    }

    @Override
    public Observable<IncomeCategoryEntity> update(IncomeCategoryEntity item) {
        return Observable.just(realm.copyToRealmOrUpdate(item));
    }

    @Override
    public Observable delete(IncomeCategoryEntity item) {
        return null;
    }
}
