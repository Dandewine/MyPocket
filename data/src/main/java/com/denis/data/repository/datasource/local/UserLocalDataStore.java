package com.denis.data.repository.datasource.local;

import com.denis.data.entity.LoginResponseEntity;
import com.denis.data.entity.UserEntity;
import com.denis.data.local_store.RealmStore;
import com.denis.data.repository.datasource.interfaces.UserDataStore;


import java.util.List;

import javax.inject.Inject;

import rx.Observable;


public class UserLocalDataStore implements UserDataStore {
    private RealmStore<UserEntity> realmStore;

    @Inject
    public UserLocalDataStore(RealmStore<UserEntity> realmStore) {
        this.realmStore = realmStore;
    }


    @Override
    public Observable<LoginResponseEntity> getUserEntity(String body) {
        throw new UnsupportedOperationException("This method can be useful only from CloudUserDataStore.class");
    }

    @Override
    public Observable<UserEntity> put(UserEntity userEntity) {
        return realmStore.put(userEntity);
    }

    @Override
    public Observable<UserEntity> update() {
        throw new UnsupportedOperationException("This method can be useful only from CloudUserDataStore.class");
    }

    @Override
    public Observable<UserEntity> getUserEntityByID(String id) {
        return realmStore.get(id);
    }

    @Override
    public Observable<List<UserEntity>> getAll() {
        return realmStore.getList();
    }

    @Override
    public Observable deleteUser() {
        return realmStore.delete(null);
    }
}
