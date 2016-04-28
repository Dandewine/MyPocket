package com.denis.data.repository.datasource.cloud;


import com.denis.data.entity.LoginResponseEntity;
import com.denis.data.entity.UserEntity;
import com.denis.data.entity.mapper.EntityMapper;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.data.rest.AuthService;
import com.denis.domain.models.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
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
        throw new UnsupportedOperationException("Can't do this");
    }

    @Override
    public Observable<LoginResponseEntity> getUserEntity(String body) { //getUser
        LoginResponseEntity loginResponseEntity = null;

        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse(body), body);
            Response<LoginResponseEntity> response = authService.loginUser(requestBody).execute();

            loginResponseEntity = response.body() != null ? response.body() : null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Observable.just(loginResponseEntity);
    }

    @Override
    public Observable<UserEntity> put(UserEntity userEntity) {//registration
        Response response = null;
        User user = mapper.fromEntity(userEntity);
        String body = new Gson().toJson(user);
        RequestBody requestBody = RequestBody.create(MediaType.parse(body), body);
        Call call = authService.registerUser(requestBody);

        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Observable.just(
                (response != null ? response.code() : 0) == HttpURLConnection.HTTP_CREATED ? userEntity : null
        );
    }

    @Override
    public Observable<UserEntity> update() {
        throw new UnsupportedOperationException("Can't update user yet");
    }


}
