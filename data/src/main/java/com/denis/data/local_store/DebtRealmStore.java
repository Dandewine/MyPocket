package com.denis.data.local_store;

import com.denis.data.entity.DebtEntity;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;

/**
 * Created by denis on 2/20/16.
 */
public class DebtRealmStore implements RealmStore<DebtEntity> {

    private Realm mRealm;

    @Inject
    public DebtRealmStore(Realm realm) {
        this.mRealm = realm;
    }

    @Override
    public Observable<DebtEntity> get(String id) {
        return Observable.just(mRealm.where(DebtEntity.class).equalTo("id",id).findFirst());
    }

    @Override
    public Observable<DebtEntity> put(DebtEntity item) {
      /*  Number max = mRealm.where(DebtEntity.class).max("id");
        String nextId = max == null ? 0 : max.intValue() + 1;
        item.setId(nextId);*/

        mRealm.beginTransaction();
        DebtEntity debtEntity = mRealm.copyToRealmOrUpdate(item);
        mRealm.commitTransaction();
        return Observable.just(debtEntity);
    }

    @Override
    public Observable<List<DebtEntity>> put(Collection<DebtEntity> collection) {
       throw  new UnsupportedOperationException("This can't be done!");
    }

    @Override
    public Observable<DebtEntity> update(DebtEntity item) {
        mRealm.beginTransaction();
        DebtEntity debtEntity = mRealm.copyToRealmOrUpdate(item);
        mRealm.commitTransaction();
        return Observable.just(debtEntity);
    }

    @Override
    public Observable<List<DebtEntity>> getList() {
        return Observable.just(mRealm.where(DebtEntity.class).findAllSorted("id"));
    }

    @Override
    public Observable delete(DebtEntity item) {
        return null;
    }
}
