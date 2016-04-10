package com.denis.data.repository.datasource.interfaces;

import com.denis.data.entity.UserEntity;


import rx.Observable;

/**
 * Created by denis on 4/10/16.
 */
public interface UserDataStore {
    /**
     *Get an {@link rx.Observable} which will emit a {@link UserEntity} by its id.
     * @param categoryId The id to retrieve wallet data.
     */
    Observable<UserEntity> getUserEntity(final int categoryId);
    /**
     * Get an {@link rx.Observable} which will emit a {@link UserEntity}.
     */
    Observable<UserEntity> put(UserEntity UserEntity);
}