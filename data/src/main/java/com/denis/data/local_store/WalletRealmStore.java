package com.denis.data.local_store;

import com.denis.data.entity.WalletEntity;

import java.util.List;

import io.realm.Realm;
import rx.Observable;

public class WalletRealmStore implements RealmStore<WalletEntity> {
    private Realm mRealm;

    public WalletRealmStore(Realm mRealm) {
        this.mRealm = mRealm;
    }

    @Override
    public Observable<WalletEntity> get(int id) {
       return Observable.just(mRealm.where(WalletEntity.class).equalTo("id",id).findFirst());
    }

    @Override
    public void put(WalletEntity item) {
        mRealm.executeTransaction(realm -> realm.copyToRealmOrUpdate(item));
    }

    @Override
    public Observable<List<WalletEntity>> getList() {
       return Observable.just(mRealm.where(WalletEntity.class).findAllAsync());
    }
}
