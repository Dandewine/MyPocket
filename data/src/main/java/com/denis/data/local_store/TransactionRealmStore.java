package com.denis.data.local_store;

import com.denis.data.entity.TransactionEntity;
import com.denis.domain.models.categories.Category;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import rx.Observable;

@Singleton
public class TransactionRealmStore implements RealmStore<TransactionEntity> {

    private Realm realm;

    @Inject
    public TransactionRealmStore(Realm realm) {
        this.realm = realm;
    }

    @Override
    public Observable<TransactionEntity> get(String id) {
        return Observable.just(realm.where(TransactionEntity.class).equalTo("id", id).findFirst());
    }

    @Override
    public Observable<TransactionEntity> put(TransactionEntity item) {
        realm.beginTransaction();
        TransactionEntity entity = realm.copyToRealmOrUpdate(item);
        realm.commitTransaction();
        return entity.asObservable();
    }

    @Override
    public Observable<List<TransactionEntity>> put(List<TransactionEntity> list) {
        throw new UnsupportedOperationException("I cant do this");
    }

    @Override
    public Observable<List<TransactionEntity>> getList() {
        return Observable.just(realm.where(TransactionEntity.class).findAll());
    }

    @Override
    public Observable<TransactionEntity> update(TransactionEntity item) {
        return Observable.just(realm.copyToRealmOrUpdate(item));
    }

    @Override
    public Observable delete(TransactionEntity item) {
        return null;
    }
}
