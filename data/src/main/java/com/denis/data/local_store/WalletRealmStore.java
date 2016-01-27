package com.denis.data.local_store;

import com.denis.data.entity.WalletEntity;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import rx.Observable;

@Singleton
public class WalletRealmStore implements RealmStore<WalletEntity> {
    private Realm mRealm;

    @Inject
    public WalletRealmStore(Realm mRealm) {
        this.mRealm = mRealm;
    }

    @Override
    public Observable<WalletEntity> get(int id) {
        return Observable.just(mRealm.where(WalletEntity.class).equalTo("id", id).findFirst());
    }

    @Override
    public Observable<WalletEntity> put(WalletEntity item) {
      /*  List<IncomeCategoryEntity> list = Arrays.asList(new IncomeCategoryEntity(0, "Salary"),
                new IncomeCategoryEntity(1, "Selling"),
                new IncomeCategoryEntity(2, "Gifts"),
                new IncomeCategoryEntity(3, "Award"),
                new IncomeCategoryEntity(4, "Interest Money"),
                new IncomeCategoryEntity(5, "Other"));

        List<ExpenseCategoryEntity> list2 = Arrays.asList(
                new ExpenseCategoryEntity(0, "Food & Beverage"),
                new ExpenseCategoryEntity(1, "Shopping/Clothes"),
                new ExpenseCategoryEntity(2, "Health/Medicine"),
                new ExpenseCategoryEntity(3, "Gym"),
                new ExpenseCategoryEntity(4, "Fees and Charges"),
                new ExpenseCategoryEntity(5, "Insurance"),
                new ExpenseCategoryEntity(6, "Education"),
                new ExpenseCategoryEntity(7, "Gifts and presents"),
                new ExpenseCategoryEntity(8, "Travel"),
                new ExpenseCategoryEntity(9, "Love"),
                new ExpenseCategoryEntity(10, "Entertainment"),
                new ExpenseCategoryEntity(11, "Investment"),
                new ExpenseCategoryEntity(12, "Repairs"),
                new ExpenseCategoryEntity(13, "Transport"),
                new ExpenseCategoryEntity(14, "Bills and Utilities"),
                new ExpenseCategoryEntity(15, "Rent"),
                new ExpenseCategoryEntity(16, "Family"),
                new ExpenseCategoryEntity(17, "Other")
        );


        mRealm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(list));
        mRealm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(list2));*/


        Number id = mRealm.where(WalletEntity.class).max("id");
        int nextId = id == null ? 0 : id.intValue() + 1;

        item.setId(nextId);
        final WalletEntity[] walletEntity = new WalletEntity[1];
        mRealm.executeTransaction(realm -> walletEntity[0] = realm.copyToRealmOrUpdate(item));
        return Observable.just(walletEntity[0]);
    }

    @Override
    public Observable<List<WalletEntity>> getList() {
        return Observable.just(mRealm.where(WalletEntity.class).findAllSorted("id"));
    }

    @Override
    public Observable<List<WalletEntity>> put(Collection<WalletEntity> collection) {
        mRealm.executeTransaction(realm -> realm.copyToRealmOrUpdate(collection));
        throw new UnsupportedOperationException("I can't do this");
    }
}
