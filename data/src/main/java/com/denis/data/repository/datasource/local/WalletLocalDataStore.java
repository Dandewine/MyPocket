package com.denis.data.repository.datasource.local;

import com.denis.data.entity.WalletEntity;
import com.denis.data.local_store.RealmStore;
import com.denis.data.repository.datasource.interfaces.WalletDataStore;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;

public class WalletLocalDataStore implements WalletDataStore {
    public RealmStore<WalletEntity> storage;

    @Inject
    public WalletLocalDataStore(@Named("wallets") RealmStore<WalletEntity> storage) {
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
    public Observable<List<WalletEntity>> put(Collection<WalletEntity> collection) {
        return storage.put(collection);
    }

}
