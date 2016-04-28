package com.denis.data.repository;

import com.denis.data.entity.LoginResponseEntity;
import com.denis.data.entity.UserEntity;
import com.denis.data.entity.mapper.EntityMapper;
import com.denis.data.entity.mapper.UserDataMapper;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.domain.models.LoginResponse;
import com.denis.domain.models.User;
import com.denis.domain.repository.UserRepository;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by denis on 4/10/16.
 */
public class UserDataStoreRepository implements UserRepository {

    private UserDataMapper userDataMapper;
    private UserDataStore userDataStore;
    private EntityMapper<LoginResponseEntity,LoginResponse> loginResponseMapper;

    public UserDataStoreRepository(UserDataMapper userDataMapper, UserDataStore userDataStore,
                                   EntityMapper<LoginResponseEntity,LoginResponse> loginResponseMapper) {
        this.userDataMapper = userDataMapper;
        this.userDataStore = userDataStore;
        this.loginResponseMapper = loginResponseMapper;
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
    public Observable<LoginResponse> login(String body) {
        return userDataStore.getUserEntity(body)
                .map(loginResponseMapper::fromEntity);
    }
}
