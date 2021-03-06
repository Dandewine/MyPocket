package com.denis.mypocket.internal.di.modules;

import android.content.Context;

import com.denis.data.entity.LoginResponseEntity;
import com.denis.data.entity.UserEntity;
import com.denis.data.entity.mapper.EntityMapper;
import com.denis.data.entity.mapper.LoginResponseMapper;
import com.denis.data.entity.mapper.UserDataMapper;
import com.denis.data.repository.UserDataStoreRepository;
import com.denis.data.repository.datasource.cloud.UserCloudDataStore;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.data.rest.AuthService;
import com.denis.domain.RestClient;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.auth.RegistrationUseCase;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.LoginResponse;
import com.denis.domain.models.User;
import com.denis.domain.repository.UserRepository;
import com.denis.mypocket.screens.signup_screen.viewmodel.RegistrationViewModel;

import com.denis.mypocket.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by denis on 4/15/16.
 */
@Module(includes = ActivityModule.class)
public class RegistrationModule {
    @Provides @PerActivity
    RegistrationViewModel registrationViewModel(UseCase<User> regUserCase, @Named("activity") Context context){
        return new RegistrationViewModel(regUserCase, context);
    }

    @Provides @PerActivity
    UseCase<User> provideRegUseCase(ThreadExecutor threadExecutor, PostExecutionThread thread, UserRepository repository){
        return new RegistrationUseCase(threadExecutor, thread, repository);
    }

    @Provides @PerActivity
    UserRepository provideUserRepository(UserDataMapper dataMapper, UserDataStore dataStore,
                                         EntityMapper<LoginResponseEntity, LoginResponse> loginMapper){
        return new UserDataStoreRepository(dataMapper, dataStore, loginMapper);
    }

    @Provides @PerActivity EntityMapper<LoginResponseEntity, LoginResponse> provideLoginMapper(){
        return new LoginResponseMapper();
    }

    @Provides @PerActivity
    UserDataMapper provideUserDataMapper(){
        return new UserDataMapper();
    }

    @Provides @PerActivity
    UserDataStore provideUserDataStore(RestClient restClient, EntityMapper<UserEntity,User> mapper){
        return new UserCloudDataStore(restClient.create(AuthService.class),mapper);
    }

    @Provides @PerActivity EntityMapper<UserEntity, User> provideMapper(){
        return new UserDataMapper();
    }
}
