package com.denis.data.local_store;

import com.denis.data.entity.TransactionEntity;

import java.util.Collection;
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
    public Observable<TransactionEntity> get(int id) {
        return Observable.just(realm.where(TransactionEntity.class).equalTo("id", id).findFirst());
    }

    @Override
    public Observable<TransactionEntity> put(TransactionEntity item) {

        Number max = realm.where(TransactionEntity.class).max("id");
        int nextId = max == null ? 0 : max.intValue() + 1;
        item.setId(nextId);

        final TransactionEntity[] entity = new TransactionEntity[1];
        realm.executeTransaction(realm -> entity[0] = realm.copyToRealmOrUpdate(item));
        return Observable.just(entity[0]);
    }

    @Override
    public Observable<List<TransactionEntity>> put(Collection<TransactionEntity> collection) {
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
}
