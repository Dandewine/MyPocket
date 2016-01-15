package com.denis.data.local_store;

import java.util.Collection;
import java.util.List;

import rx.Observable;

/**
 * Created by denis on 1/5/16.
 */
public interface RealmStore<T>  {
    Observable<T> get(final int id);
    Observable<T> put(T item);
    Observable<T> put(Collection<T> collection);

    Observable<List<T>> getList();
}