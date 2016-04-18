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
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;

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
        Call<Void> call = authService.registerUser(body);


            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });

        return Observable.just(userEntity);
    }
}
