package com.denis.data.local_store;

import com.denis.data.entity.ProfitCategoryEntity;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;


public class ProfitCategoriesStore implements RealmStore<ProfitCategoryEntity>{

    private Realm mRealm;

    @Inject
    public ProfitCategoriesStore(Realm realm) {
        this.mRealm = realm;
    }

    @Override
    public Observable<ProfitCategoryEntity> get(int id) {
       return Observable.just(mRealm.where(ProfitCategoryEntity.class).equalTo("id",id).findFirst());
    }

    @Override
    public Observable<List<ProfitCategoryEntity>> getList() {
        return Observable.just(mRealm.where(ProfitCategoryEntity.class).findAllSortedAsync("id"));
    }

    @Override
    public Observable<ProfitCategoryEntity> put(ProfitCategoryEntity item) {
        final ProfitCategoryEntity[] entity = {null};
        mRealm.executeTransaction(realm -> entity[0] = realm.copyToRealmOrUpdate(item));
        return Observable.just(entity[0]);
    }

    @Override
    public Observable<ProfitCategoryEntity> put(Collection<ProfitCategoryEntity> collection) {
        List<ProfitCategoryEntity> entities;
        mRealm.beginTransaction();
        entities = mRealm.copyToRealmOrUpdate(collection);
        mRealm.commitTransaction();
        return Observable.from(entities);
    }


}
