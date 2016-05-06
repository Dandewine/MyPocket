package com.denis.data.repository.datasource.interfaces;

import android.support.annotation.Nullable;

import com.denis.data.entity.LoginResponseEntity;
import com.denis.data.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by denis on 4/10/16.
 */
public interface UserDataStore {
    /**
     *Get an {@link rx.Observable} which will emit a {@link UserEntity} by its id.
     * @param id The id to retrieve wallet data.
     */
    Observable<UserEntity> getUserEntityByID(@Nullable final String id);


    Observable<LoginResponseEntity> getUserEntity(String body);
    /**
     * Get an {@link rx.Observable} which will emit a {@link UserEntity}.
     */
    Observable<UserEntity> put(UserEntity userEntity);

    Observable<UserEntity> update();


    Observable<List<UserEntity>> getAll();

    Observable deleteUser();
}
