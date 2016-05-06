package com.denis.data.repository.datasource.cloud;

import com.denis.data.entity.WalletEntity;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.data.repository.datasource.interfaces.WalletDataStore;
import com.denis.data.rest.WalletService;
import com.fernandocejas.frodo.annotation.RxLogObservable;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import rx.Observable;

/**
 * Created by denis on 5/4/16.
 */
public class WalletCloudDataStore implements WalletDataStore {

    private WalletService walletService;
    private UserDataStore userDataStore;


    @Inject
    public WalletCloudDataStore(WalletService walletService, UserDataStore userDataStore) {
        this.walletService = walletService;
        this.userDataStore = userDataStore;
    }

    @Override
    public Observable<WalletEntity> getWalletEntity(String walletId) {
        return null;
    }

    @Override @RxLogObservable
    public Observable<List<WalletEntity>> getListWalletEntities() {
        return userDataStore.getUserEntityByID(null)
                .flatMap(user -> walletService.getAllWallets(user.getId()));
    }

    @Override @RxLogObservable
    public Observable<WalletEntity> put(WalletEntity walletEntity) {
        return userDataStore.getUserEntityByID(null)
                .map(user -> walletService.createWallet(user.getId()))
                .flatMap(call -> {
                    try {
                        return Observable.just(call.execute().code() == HttpsURLConnection.HTTP_CREATED ? walletEntity : null);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return Observable.just(null);
                    }
                });
    }

    @Override
    public Observable<List<WalletEntity>> put(Collection<WalletEntity> collection) {
        return null;
    }

    @Override
    public Observable<WalletEntity> update(WalletEntity entity) {
        return null;
    }
}
