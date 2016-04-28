package com.denis.data.local_store;

import com.denis.data.entity.WalletEntity;

import java.util.Arrays;
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
    public Observable<WalletEntity> get(String id) {
        return Observable.just(mRealm.where(WalletEntity.class).equalTo("id", id).findFirst());
    }

    @Override
    public Observable<WalletEntity> put(WalletEntity item) {
        Number id = mRealm.where(WalletEntity.class).max("id");
        int nextId = id == null ? 0 : id.intValue() + 1;

        item.setId(nextId);
        final WalletEntity[] walletEntity = new WalletEntity[1];
        mRealm.executeTransaction(realm -> walletEntity[0] = realm.copyToRealmOrUpdate(item));
        return Observable.just(walletEntity[0]);
    }

    @Override
    public Observable<List<WalletEntity>> getList() {
        // TODO: 2/5/16 remove these lines
        WalletEntity entity = new WalletEntity(0, "Fake_Wallet 1","$",1000);
        WalletEntity entity1 = new WalletEntity(1, "Fake_Wallet 2","$",500);
        WalletEntity entity2 = new WalletEntity(2, "Fake_Wallet 3","$",100);

        mRealm.executeTransaction(realm -> realm.copyToRealmOrUpdate(Arrays.asList(entity,entity1,entity2)));

        return Observable.just(mRealm.where(WalletEntity.class).findAllSorted("id"));
    }

    @Override
    public Observable<List<WalletEntity>> put(Collection<WalletEntity> collection) {
        throw new UnsupportedOperationException("I can't do this");
    }

    @Override
    public Observable<WalletEntity> update(WalletEntity item) {
        mRealm.beginTransaction();
        WalletEntity update = mRealm.copyToRealmOrUpdate(item);
        mRealm.commitTransaction();
        return Observable.just(update);
    }
}
