package com.denis.data.repository.datasource.local;

import com.denis.data.entity.UserEntity;
import com.denis.data.local_store.RealmStore;
import com.denis.data.repository.datasource.interfaces.UserDataStore;


import javax.inject.Inject;

import rx.Observable;


public class UserLocalDataStore implements UserDataStore {
    private RealmStore<UserEntity> realmStore;

    @Inject
    public UserLocalDataStore(RealmStore<UserEntity> realmStore) {
        this.realmStore = realmStore;
    }

    @Override
    public Observable<UserEntity> getUserEntity(int categoryId) {
        return realmStore.get(categoryId);
    }

    @Override
    public Observable<String> getUserEntity(String body) {
        return null;
    }


    @Override
    public Observable<UserEntity> put(UserEntity userEntity) {
        return realmStore.put(userEntity);
    }

    @Override
    public Observable<UserEntity> update() {
        return null;
    }
}
