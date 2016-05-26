package com.denis.data.local_store;

import com.denis.data.entity.CycleOperationEntity;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;

public class CircleOperationRealmStore implements RealmStore<CycleOperationEntity> {

    private Realm mRealm;

    @Inject
    public CircleOperationRealmStore(Realm mRealm) {
        this.mRealm = mRealm;
    }

    @Override
    public Observable<CycleOperationEntity> get(String id) {
        return Observable.just(mRealm.where(CycleOperationEntity.class).equalTo("id", id).findFirst());
    }

    @Override
    public Observable<CycleOperationEntity> put(CycleOperationEntity item) {
        Number max = mRealm.where(CycleOperationEntity.class).max("id");
        int nextId = max == null ? 0 : max.intValue() + 1;
        item.setId(nextId);

        final CycleOperationEntity[] entity = new CycleOperationEntity[1];
        mRealm.executeTransaction(realm -> {
            entity[0] = realm.copyToRealmOrUpdate(item);
        });

        return Observable.just(entity[0]);
    }

    @Override
    public Observable<List<CycleOperationEntity>> put(List<CycleOperationEntity> list) {
        throw new UnsupportedOperationException("This is unsupported now");
    }

    @Override
    public Observable<List<CycleOperationEntity>> getList() {
        return Observable.just(mRealm.where(CycleOperationEntity.class).findAllSorted("id"));
    }

    @Override
    public Observable<CycleOperationEntity> update(CycleOperationEntity item) {
        return Observable.just(mRealm.copyToRealmOrUpdate(item));
    }

    @Override
    public Observable delete(CycleOperationEntity item) {
        return null;
    }
}
