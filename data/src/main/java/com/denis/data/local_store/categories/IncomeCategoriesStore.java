package com.denis.data.local_store.categories;

import com.denis.data.entity.ExpenseCategoryEntity;
import com.denis.data.entity.IncomeCategoryEntity;
import com.denis.data.local_store.RealmStore;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;


public class IncomeCategoriesStore implements RealmStore<IncomeCategoryEntity> {

    private Realm mRealm;

    @Inject
    public IncomeCategoriesStore(Realm realm) {
        this.mRealm = realm;
    }

    @Override
    public Observable<IncomeCategoryEntity> get(String id) {
        return Observable.just(mRealm.where(IncomeCategoryEntity.class).equalTo("id", id).findFirst());
    }

    @Override
    public Observable<List<IncomeCategoryEntity>> getList() {
        List<IncomeCategoryEntity> list = Arrays.asList(new IncomeCategoryEntity(0, "Salary"),
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
        mRealm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(list2));

        return Observable.just(mRealm.where(IncomeCategoryEntity.class).findAllSorted("id"));
    }

    @Override
    public Observable<IncomeCategoryEntity> put(IncomeCategoryEntity item) {
        Number max = mRealm.where(IncomeCategoryEntity.class).max("id");
        int nextId = max == null ? 0 : max.intValue() + 1;
        item.setId(nextId);
        final IncomeCategoryEntity[] entity = {null};
        mRealm.executeTransaction(realm -> entity[0] = realm.copyToRealmOrUpdate(item));
        return Observable.just(entity[0]);
    }

    @Override
    public Observable<List<IncomeCategoryEntity>> put(List<IncomeCategoryEntity> list) {
        throw new UnsupportedOperationException("Can't do that");
    }

    @Override
    public Observable<IncomeCategoryEntity> update(IncomeCategoryEntity item) {
        return Observable.just(mRealm.copyToRealmOrUpdate(item));
    }

    @Override
    public Observable delete(IncomeCategoryEntity item) {
        return null;
    }
}
