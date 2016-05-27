package com.denis.mypocket.internal.di.modules;

import com.denis.data.entity.LoginResponseEntity;
import com.denis.data.entity.UserEntity;
import com.denis.data.entity.mapper.EntityMapper;
import com.denis.data.entity.mapper.LoginResponseMapper;
import com.denis.data.entity.mapper.UserDataMapper;
import com.denis.data.local_store.RealmStore;
import com.denis.data.local_store.TokenRealmStore;
import com.denis.data.local_store.UserRealmStore;
import com.denis.data.repository.UserDataStoreRepository;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.data.repository.datasource.local.UserLocalDataStore;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.auth.TokenGet;
import com.denis.domain.models.LoginResponse;
import com.denis.domain.repository.TokenRepository;
import com.denis.domain.repository.UserRepository;
import com.denis.domain.interactor.user.GetAllUsers;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.screens.splash_screen.viewmodel.SplashViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by denis on 4/27/16.
 */
@Module
public class SplashModule {
    @Provides @PerActivity
    SplashViewModel provideSplashViewModel(@Named("token_get") UseCase tokenGetUseCase, @Named("user_get") UseCase userGetUseCase){
        return new SplashViewModel(tokenGetUseCase,userGetUseCase);
    }

    @Provides @PerActivity @Named("token_get")
    UseCase provideTokenGetUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread, TokenRepository repository){

        return new TokenGet(executor,postExecutionThread,repository);
    }

    @Provides @PerActivity @Named("user_get")
    UseCase provideUserGetUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread, UserRepository repository){

        return new GetAllUsers(executor,postExecutionThread,repository);
    }

    @Provides @PerActivity UserRepository provideUserRepository(UserDataMapper userDataMapper, UserDataStore userDataStore,
                                                                EntityMapper<LoginResponseEntity, LoginResponse> loginMapper){
        return new UserDataStoreRepository(userDataMapper, userDataStore, loginMapper);
    }

    @Provides @PerActivity
    EntityMapper<LoginResponseEntity, LoginResponse> provideLoginMapper(){
        return new LoginResponseMapper();
    }

    @Provides @PerActivity RealmStore provideRealmStore(Realm realm){
        return new TokenRealmStore(realm);
    }

    @Provides @PerActivity UserDataMapper provideUserDataMapper(){
        return new UserDataMapper();
    }

    @Provides @PerActivity UserDataStore provideUserDataStore(RealmStore<UserEntity> realmStore){
        return new UserLocalDataStore(realmStore);
    }

    @Provides @PerActivity RealmStore<UserEntity> provideLocalUserDataStore(Realm realm){
        return new UserRealmStore(realm);
    }


}
