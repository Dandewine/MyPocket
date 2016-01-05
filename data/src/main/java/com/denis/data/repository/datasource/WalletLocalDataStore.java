package com.denis.data.repository.datasource;

import com.denis.data.entity.WalletEntity;
import com.denis.data.local_store.RealmStore;

import java.util.List;

import rx.Observable;


public class WalletLocalDataStore implements WalletDataStore {
    public RealmStore storage;

    public WalletLocalDataStore(RealmStore storage) {
        this.storage = storage;
    }

    @Override
    public Observable<WalletEntity> getWalletEntity(int walletId) {
        return storage.get(walletId);
    }

    @Override
    public Observable<List<WalletEntity>> getListWalletEntities() {
        return storage.getList();
    }
}
