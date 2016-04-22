package com.denis.data.repository.datasource.cloud;

import com.denis.data.entity.UserEntity;
import com.denis.data.entity.mapper.EntityMapper;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.data.rest.AuthService;
import com.denis.domain.models.User;
import com.google.gson.Gson;


import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by denis on 4/15/16.
 */
@Singleton
public class UserCloudDataStore implements UserDataStore {

    private AuthService authService;
    private EntityMapper<UserEntity, User> mapper;

    @Inject
    public UserCloudDataStore(AuthService authService, EntityMapper<UserEntity, User> mapper) {
        this.authService = authService;
        this.mapper = mapper;
    }

    @Override
    public Observable<UserEntity> getUserEntity(int categoryId) {
        return null;
    }

    @Override
    public Observable<UserEntity> put(UserEntity userEntity) {
        User user = mapper.fromEntity(userEntity);

        String body = new Gson().toJson(user);
        authService.registerUser(body).subscribe(new Subscriber<Call>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Call call) {
                try {
                    call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return Observable.just(userEntity);
    }
}
