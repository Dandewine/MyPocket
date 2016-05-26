package com.denis.data.local_store;

import com.denis.data.entity.WalletEntity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import rx.Observable;

@Singleton
public class WalletRealmStore implements RealmStore<WalletEntity> {
    private Realm realm;

    @Inject
    public WalletRealmStore(Realm realm) {
        this.realm = realm;
    }

    @Override
    public Observable<WalletEntity> get(String id) {
        return Observable.just(realm.where(WalletEntity.class).equalTo("id", id).findFirst());
    }

    @Override
    public Observable<WalletEntity> put(WalletEntity item) {

        final WalletEntity[] walletEntity = new WalletEntity[1];
        realm.executeTransaction(realm -> walletEntity[0] = realm.copyToRealmOrUpdate(item));
        return Observable.just(walletEntity[0]);
    }

    @Override
    public Observable<List<WalletEntity>> getList() {
        return Observable.just(realm.where(WalletEntity.class).findAllSorted("id"));
    }

    @Override
    public Observable<List<WalletEntity>> put(List<WalletEntity> list) {
        realm.beginTransaction();
        List<WalletEntity> entities = realm.copyToRealmOrUpdate(list);
        realm.commitTransaction();
        return Observable.just(entities);
    }

    @Override
    public Observable<WalletEntity> update(WalletEntity item) {
        realm.beginTransaction();
        WalletEntity update = realm.copyToRealmOrUpdate(item);
        realm.commitTransaction();
        return Observable.just(update);
    }

    @Override
    public Observable delete(WalletEntity item) {
        throw new UnsupportedOperationException("I can't do thi yet");
    }
}
