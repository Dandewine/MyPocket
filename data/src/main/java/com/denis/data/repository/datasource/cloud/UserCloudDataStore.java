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
import java.util.List;

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
    public Observable<UserEntity> getUserEntityByID(String userID) {
        throw new UnsupportedOperationException("Can't do this");
    }

    /**
     * Use this when you need to login
     *
     * @param body - json
     * @return
     */
    @Override
    public Observable<LoginResponseEntity> getUserEntity(String body) { //login
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

    /** Use this when you need registration
     * @param userEntity
     * @return
     */
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

    /**
     * Use this when you need to update
     * @return
     */
    @Override
    public Observable update() {
        Call<Void> logout = authService.logout();
        int code = 0;
        try {
            code = logout.execute().code();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Observable.just(code);
    }

    @Override
    public Observable<List<UserEntity>> getAll() {
        throw new UnsupportedOperationException("Can't do this");
    }

    @Override
    public Observable deleteUser() {
        throw new UnsupportedOperationException("Can't do this");
    }
}
