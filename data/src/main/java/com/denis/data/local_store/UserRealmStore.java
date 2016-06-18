package com.denis.data.local_store;

import android.text.TextUtils;

import com.denis.data.entity.UserEntity;
import com.denis.domain.models.categories.Category;
import com.fernandocejas.frodo.annotation.RxLogObservable;

import java.util.ArrayList;
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
    @RxLogObservable
    public Observable<UserEntity> get(String id) {
        if (TextUtils.equals(Thread.currentThread().getName(), "main")) {
            return Observable.just(realm.where(UserEntity.class).findAll().get(0));
        } else {
            Realm realm = Realm.getDefaultInstance();
            UserEntity entity = realm.where(UserEntity.class).findAll().get(0);
            Observable<UserEntity> observable = mapUserEntity(entity);
            realm.close();
            return observable;
        }
    }

    private Observable<UserEntity> mapUserEntity(UserEntity user) {
        UserEntity userEntity = new UserEntity(user.getId());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userEntity.setName(user.getName());
        return Observable.just(userEntity);
    }

    @Override
    public Observable<UserEntity> put(UserEntity item) {
        realm.beginTransaction();
        UserEntity entity = realm.copyToRealmOrUpdate(item);
        realm.commitTransaction();
        return Observable.just(entity);
    }

    @Override
    public Observable<List<UserEntity>> put(List<UserEntity> list) {
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
        if (TextUtils.equals(Thread.currentThread().getName(), "main")) {
            return realm.where(UserEntity.class).findAll().asObservable().map(this::parseList);
        } else {
            Realm realm = Realm.getDefaultInstance();
            Observable<List<UserEntity>> response = realm.where(UserEntity.class)
                    .findAll()
                    .asObservable()
                    .map(this::parseList);
            realm.close();
            return response;
        }
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

    @Override
    public Observable delete(UserEntity item) {
        realm.executeTransaction(realm1 -> realm.deleteAll());
        return Observable.just(true);
    }
}
