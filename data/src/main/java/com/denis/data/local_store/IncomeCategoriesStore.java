package com.denis.data.local_store;

import com.denis.data.entity.IncomeCategoryEntity;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;


public class IncomeCategoriesStore implements RealmStore<IncomeCategoryEntity> {

    private Realm mRealm;

    @Inject
    public IncomeCategoriesStore(Realm realm) {
        this.mRealm = realm;
    }

    @Override
    public Observable<IncomeCategoryEntity> get(int id) {
        return Observable.just(mRealm.where(IncomeCategoryEntity.class).equalTo("id", id).findFirst());
    }

    @Override
    public Observable<List<IncomeCategoryEntity>> getList() {
        return Observable.just(mRealm.where(IncomeCategoryEntity.class).findAllSortedAsync("id"));
    }

    @Override
    public Observable<IncomeCategoryEntity> put(IncomeCategoryEntity item) {
        Number max = mRealm.where(IncomeCategoryEntity.class).max("id");
        int nextId = max == null ? 0 : max.intValue() + 1;
        item.setId(nextId);
        final IncomeCategoryEntity[] entity = {null};
        mRealm.executeTransaction(realm -> entity[0] = realm.copyToRealmOrUpdate(item));
        return Observable.just(entity[0]);
    }

    @Override
    public Observable<List<IncomeCategoryEntity>> put(Collection<IncomeCategoryEntity> collection) {
      /*  List<IncomeCategoryEntity> entities;
        mRealm.beginTransaction();
        entities = mRealm.copyToRealmOrUpdate(collection);
        mRealm.commitTransaction();
        return Observable.from(entities);*/
        throw new UnsupportedOperationException("Can't do that");
    }


}
