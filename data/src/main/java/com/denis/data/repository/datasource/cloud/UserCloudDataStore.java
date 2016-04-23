package com.denis.data.repository.datasource.cloud;

import com.denis.data.entity.UserEntity;
import com.denis.data.entity.mapper.EntityMapper;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.data.rest.AuthService;
import com.denis.domain.models.User;
import com.google.gson.Gson;


import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
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
        throw new UnsupportedOperationException("Can't do this");
    }

    @Override
    public Observable<String> getUserEntity(String body) {
        Response response;

        try {
            response = authService.loginUser(body).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Observable.just(null);
    }

    @Override @SuppressWarnings("null")
    public Observable<UserEntity> put(UserEntity userEntity) {
        Response response = null;
        User user = mapper.fromEntity(userEntity);
        String body = new Gson().toJson(user);
        Call call = authService.registerUser(body);

        try {
           response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Observable.just(
                (response != null ? response.code() : 0) == 201 ? userEntity : null
        );
    }
}
