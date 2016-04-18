package com.denis.data.repository.datasource.cloud;

import com.denis.data.entity.UserEntity;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.data.rest.AuthService;
import com.google.gson.Gson;


import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import rx.Observable;

/**
 * Created by denis on 4/15/16.
 */
@Singleton
public class UserCloudDataStore implements UserDataStore {

    private AuthService authService;

    @Inject
    public UserCloudDataStore(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public Observable<UserEntity> getUserEntity(int categoryId) {
        return null;
    }

    @Override
    public Observable<UserEntity> put(UserEntity userEntity){
        Gson gson = new Gson();
        String body = gson.toJson(userEntity);
        Call<Void> call = authService.registerUser(body);

        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Observable.just(userEntity);
    }
}
