package com.denis.data.repository.datasource.cloud;

import com.denis.data.entity.UserEntity;
import com.denis.data.entity.WalletEntity;
import com.denis.data.entity.mapper.WalletDataMapper;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.data.repository.datasource.interfaces.WalletDataStore;
import com.denis.data.rest.WalletService;
import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by denis on 5/4/16.
 */
public class WalletCloudDataStore implements WalletDataStore {

    private WalletService walletService;
    private UserDataStore userDataStore;
    private WalletDataMapper mapper;


    @Inject
    public WalletCloudDataStore(WalletService walletService, UserDataStore userDataStore, WalletDataMapper mapper) {
        this.walletService = walletService;
        this.userDataStore = userDataStore;
        this.mapper = mapper;
    }

    @Override
    public Observable<WalletEntity> getWalletEntity(String walletId) {
        return null;
    }

    @Override
    @RxLogObservable
    public Observable<List<WalletEntity>> getListWalletEntities() {
        return userDataStore.getUserEntityByID(null)
                .flatMap(user -> walletService.getAllWallets(user.getId()));
    }

    @Override
    @RxLogObservable
    public Observable<WalletEntity> put(WalletEntity walletEntity) {
        return userDataStore.getUserEntityByID(null)
                .map(UserEntity::getId)
                .map(id -> {
                    String json = new Gson().toJson(mapper.fromEntity(walletEntity));
                    RequestBody body = RequestBody.create(MediaType.parse(json), json);
                    return walletService.createWallet(id, body);
                })
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
