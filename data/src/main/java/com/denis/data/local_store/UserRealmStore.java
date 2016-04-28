package com.denis.data.local_store;

import com.denis.data.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;
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
    public Observable<UserEntity> get(String id) {
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
        return realm.where(UserEntity.class).findAllAsync().asObservable().map(this::parseList);
    }

    private List<UserEntity> parseList(RealmResults<UserEntity> data) {
        List<UserEntity> itemList = null;
        if (data != null) {
            itemList = new ArrayList<>();
            for (UserEntity ue : data) {
                itemList.add(ue);
            }
        }
        return itemList;
    }
}
