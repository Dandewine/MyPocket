package com.denis.data.local_store;

import java.util.List;

import rx.Observable;

/**
 * Created by denis on 1/5/16.
 */
public interface RealmStore<T>  {
    Observable<T> get(final int id);
    void put(T item);

    Observable<List<T>> getList();
}
