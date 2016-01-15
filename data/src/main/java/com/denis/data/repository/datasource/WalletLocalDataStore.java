package com.denis.data.repository.datasource;

import com.denis.data.entity.WalletEntity;
import com.denis.data.local_store.RealmStore;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class WalletLocalDataStore implements WalletDataStore {
    public RealmStore<WalletEntity> storage;

    @Inject
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

    @Override
    public Observable<WalletEntity> put(WalletEntity walletEntity) {
       return storage.put(walletEntity);
    }

    @Override
    public Observable<WalletEntity> put(Collection<WalletEntity> collection) {
      return storage.put(collection);
    }
}
