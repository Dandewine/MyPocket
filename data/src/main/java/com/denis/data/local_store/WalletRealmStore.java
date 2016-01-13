package com.denis.data.local_store;

import com.denis.data.entity.WalletEntity;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import rx.Observable;

@Singleton
public class WalletRealmStore implements RealmStore<WalletEntity> {
    private Realm mRealm;

    @Inject
    public WalletRealmStore(Realm mRealm) {
        this.mRealm = mRealm;
    }

    @Override
    public Observable<WalletEntity> get(int id) {
       return Observable.just(mRealm.where(WalletEntity.class).equalTo("id",id).findFirst());
    }

    @Override
    public Observable<WalletEntity> put(WalletEntity item) {
        final WalletEntity[] walletEntity = new WalletEntity[1];
        mRealm.executeTransaction(realm -> walletEntity[0] = realm.copyToRealmOrUpdate(item));
        return Observable.just(walletEntity[0]);
    }

    @Override
    public Observable<List<WalletEntity>> getList() {
       return Observable.just(mRealm.where(WalletEntity.class).findAllAsync());
    }

    @Override
    public Observable<WalletEntity> put(Collection<WalletEntity> collection) {
        mRealm.executeTransaction(realm -> realm.copyToRealmOrUpdate(collection));
        return null;
    }
}
