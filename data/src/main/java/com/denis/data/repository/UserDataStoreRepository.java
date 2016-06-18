package com.denis.data.repository;

import com.denis.data.entity.LoginResponseEntity;
import com.denis.data.entity.UserEntity;
import com.denis.data.entity.mapper.EntityMapper;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.domain.models.LoginResponse;
import com.denis.domain.models.User;
import com.denis.domain.models.categories.Category;
import com.denis.domain.repository.UserRepository;

import java.util.List;

import rx.Observable;

/**
 * Created by denis on 4/10/16.
 */
public class UserDataStoreRepository implements UserRepository {

    private EntityMapper<UserEntity, User> userDataMapper;
    private UserDataStore userDataStore;
    private EntityMapper<LoginResponseEntity,LoginResponse> loginResponseMapper;

    public UserDataStoreRepository(EntityMapper<UserEntity, User> mapper, UserDataStore userDataStore,
                                   EntityMapper<LoginResponseEntity,LoginResponse> loginResponseMapper) {
        this.userDataMapper = mapper;
        this.userDataStore = userDataStore;
        this.loginResponseMapper = loginResponseMapper;
    }

    @Override
    public Observable<User> getUser(String userId) {
        return userDataStore.getUserEntityByID(userId)
                .map(userDataMapper::fromEntity);
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

    @Override
    public Observable<List<User>> getAll() {
        return userDataStore.getAll().map(userDataMapper::fromEntity);
    }

    @Override
    public Observable update() {
        return userDataStore.update();
    }

    @Override
    public Observable deleteUser() {
        return userDataStore.deleteUser();
    }
}
