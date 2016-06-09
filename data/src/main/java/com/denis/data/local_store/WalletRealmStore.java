package com.denis.data.local_store;

import com.denis.data.entity.WalletEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

@Singleton
public class WalletRealmStore implements RealmStore<WalletEntity> {
    private Realm realm;

    @Inject
    public WalletRealmStore(Realm realm) {
        this.realm = realm;
    }

    @Override
    public Observable<WalletEntity> get(String id) {
        return Observable.just(realm.where(WalletEntity.class).equalTo("id", id).findFirst());
    }

    @Override
    public Observable<WalletEntity> put(WalletEntity item) {

        final WalletEntity[] walletEntity = new WalletEntity[1];
        realm.executeTransaction(realm -> walletEntity[0] = realm.copyToRealmOrUpdate(item));
        return Observable.just(walletEntity[0]);
    }

    @Override
    public Observable<List<WalletEntity>> getList() {
        if(!Thread.currentThread().getName().equals("main")){
            Realm myRealm = Realm.getDefaultInstance();
            RealmResults<WalletEntity> walletEntities = myRealm.where(WalletEntity.class).findAll();
            List<WalletEntity> entities = mapWalletList(walletEntities);
            myRealm.close();
            return Observable.just(entities);
        }else{
            return Observable.just(realm.where(WalletEntity.class).findAllSorted("id"));
        }
    }

    private List<WalletEntity> mapWalletList(RealmResults<WalletEntity> entities){
        List<WalletEntity> walletEntities = null;
        if(entities != null && !entities.isEmpty()){
            walletEntities = new ArrayList<>();
            for (WalletEntity we:entities) {
                WalletEntity walletEntity = mapWalletEntity(we);
                walletEntities.add(walletEntity);
            }
        }
        return walletEntities;
    }

    private WalletEntity mapWalletEntity(WalletEntity walletEntity){
        WalletEntity entity = null;
        if(walletEntity != null){
            entity = new WalletEntity();
            entity.setId(walletEntity.getId());
            entity.setBalance(walletEntity.getBalance());
            entity.setName(walletEntity.getName());
            entity.setCurrency(walletEntity.getCurrency());
            entity.setActive(walletEntity.isActive());
        }
        return entity;
    }

    @Override
    public Observable<List<WalletEntity>> put(List<WalletEntity> list) {
        realm.beginTransaction();
        List<WalletEntity> entities = realm.copyToRealmOrUpdate(list);
        realm.commitTransaction();
        return Observable.just(entities);
    }

    @Override
    public Observable<WalletEntity> update(WalletEntity item) {
        realm.beginTransaction();
        WalletEntity update = realm.copyToRealmOrUpdate(item);
        realm.commitTransaction();
        return Observable.just(update);
    }

    @Override
    public Observable delete(WalletEntity item) {
        throw new UnsupportedOperationException("I can't do thi yet");
    }
}
