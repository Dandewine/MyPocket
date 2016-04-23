package com.denis.data.repository;

import com.denis.data.entity.UserEntity;
import com.denis.data.entity.mapper.UserDataMapper;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.domain.models.User;
import com.denis.domain.repository.UserRepository;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by denis on 4/10/16.
 */
public class UserDataStoreRepository implements UserRepository {

    private UserDataMapper userDataMapper;
    private UserDataStore userDataStore;

    public UserDataStoreRepository(UserDataMapper userDataMapper, UserDataStore userDataStore) {
        this.userDataMapper = userDataMapper;
        this.userDataStore = userDataStore;
    }


    @Override
    public Observable<User> getUser(int userId) {
        return userDataStore.getUserEntity(userId).map(userDataMapper::fromEntity);
    }

    @Override
    public Observable<User> addUser(User user) {
        UserEntity entity = userDataMapper.toEntity(user);
        return userDataStore.put(entity)
                .map(userDataMapper::fromEntity);
    }

    @Override
    public Observable<String> getUser(String body) {
        return userDataStore.getUserEntity(body);
    }
}
