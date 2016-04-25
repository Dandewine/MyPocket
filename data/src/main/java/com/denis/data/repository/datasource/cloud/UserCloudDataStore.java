package com.denis.data.repository.datasource.cloud;


import com.denis.data.entity.UserEntity;
import com.denis.data.entity.mapper.EntityMapper;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.data.rest.AuthService;
import com.denis.domain.models.User;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

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
    public Observable<String> getUserEntity(String body) {
        String token = null;

        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse(body),body);
            Response<Token> response = authService.loginUser(requestBody).execute();

            token = response.body() != null ? response.body().getToken() : null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Observable.just(token);
    }

    @Override
    public Observable<UserEntity> put(UserEntity userEntity) {
        Response response = null;
        User user = mapper.fromEntity(userEntity);
        String body = new Gson().toJson(user);
        RequestBody requestBody = RequestBody.create(MediaType.parse(body),body);
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
        return null;
    }

    public static class Token {
        @SerializedName("token")
        private String token;

        public Token(String token) {
            this.token = token;
        }

        public Token() {
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }


}
