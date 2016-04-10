package com.denis.data.local_store;

import com.denis.data.entity.UserEntity;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import rx.Observable;

/**
 * Created by denis on 4/10/16.
 */

@Singleton
public class UserRealmStore implements RealmStore<UserEntity> {

    private Realm realm;

    @Inject
    public UserRealmStore(Realm realm) {
        this.realm = realm;
    }

    @Override
    public Observable<UserEntity> get(int id) {
        return Observable.just(realm.where(UserEntity.class).equalTo("id", id).findFirst());
    }

    @Override
    public Observable<UserEntity> put(UserEntity item) {
        realm.beginTransaction();
        UserEntity entity = realm.copyToRealm(item);
        realm.commitTransaction();
        return Observable.just(entity);
    }

    @Override
    public Observable<List<UserEntity>> put(Collection<UserEntity> collection) {
        throw new UnsupportedOperationException("You can't add a bunch of users.");
    }

    @Override
    public Observable<UserEntity> update(UserEntity item) {
        realm.beginTransaction();
        UserEntity entity = realm.copyToRealmOrUpdate(item);
        realm.commitTransaction();
        return Observable.just(entity);
    }

    @Override
    public Observable<List<UserEntity>> getList() {
        throw new UnsupportedOperationException("You can't get a list of users");
    }
}
