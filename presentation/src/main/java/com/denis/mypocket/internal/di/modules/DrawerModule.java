package com.denis.mypocket.internal.di.modules;

import android.content.Context;

import com.denis.data.entity.LoginResponseEntity;
import com.denis.data.entity.UserEntity;
import com.denis.data.entity.mapper.EntityMapper;
import com.denis.data.entity.mapper.LoginResponseMapper;
import com.denis.data.entity.mapper.UserDataMapper;
import com.denis.data.local_store.RealmStore;
import com.denis.data.local_store.UserRealmStore;
import com.denis.data.repository.UserDataStoreRepository;
import com.denis.data.repository.datasource.cloud.UserCloudDataStore;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.data.repository.datasource.local.UserLocalDataStore;
import com.denis.data.rest.AuthService;
import com.denis.domain.RestClient;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.auth.DeleteTokenUseCase;
import com.denis.domain.interactor.auth.LogoutUseCase;
import com.denis.domain.interactor.user.DeleteUserUseCase;
import com.denis.domain.models.LoginResponse;
import com.denis.domain.models.User;
import com.denis.domain.repository.TokenRepository;
import com.denis.domain.repository.UserRepository;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.viewmodel.DrawerNavViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by denis on 4/29/16.
 */
@Module(includes = ActivityModule.class)
public class DrawerModule {

    @Provides @PerActivity
    DrawerNavViewModel provideViewModel (@Named("logout") UseCase logoutUseCase,
                                         @Named("delete_user") UseCase deleteUser,
                                         @Named("delete_token") UseCase deleteTokenUseCase,
                                         @Named("activity") Context context) {
        return new DrawerNavViewModel(logoutUseCase, deleteUser, deleteTokenUseCase, context);
    }

    //region provide LogoutUseCase
    @Provides @PerActivity @Named("logout")
    UseCase provideLogout(ThreadExecutor executor, PostExecutionThread postExecutionThread,
                          UserRepository repository){
        return new LogoutUseCase(executor, postExecutionThread, repository);
    }

    @Provides @PerActivity
    UserRepository provideRepository(EntityMapper<UserEntity, User> mapper,
                                     UserDataStore userDataStore,
                                     EntityMapper<LoginResponseEntity,LoginResponse> mapper2) {
        return new UserDataStoreRepository(mapper,userDataStore,mapper2);
    }

    @Provides @PerActivity UserDataStore provideUserDataStore(AuthService authService, EntityMapper<UserEntity, User> mapper){
        return new UserCloudDataStore(authService,mapper);
    }

    @Provides @PerActivity AuthService provideAuthService(RestClient restClient) {
        return restClient.create(AuthService.class);
    }
    //endregion

    //region provide DeleteUserUseCase
    @Provides @PerActivity @Named("delete_user")
    UseCase provideDeleteUserUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread,
                                     @Named("local") UserRepository repository){
        return new DeleteUserUseCase(executor,postExecutionThread, repository);
    }

    @Provides @PerActivity @Named("local")
    UserRepository provideRepositoryLocal(EntityMapper<UserEntity, User> mapper,
                                          @Named("local_store") UserDataStore userDataStore,
                                          EntityMapper<LoginResponseEntity,LoginResponse> mapper2) {
        return new UserDataStoreRepository(mapper,userDataStore,mapper2);
    }

    @Provides @PerActivity @Named("local_store")
    UserDataStore provideUserDataStoreLocal(RealmStore<UserEntity> realmStore){
        return new UserLocalDataStore(realmStore);
    }

    @Provides @PerActivity RealmStore<UserEntity> provideRealmStore(Realm realm){
        return new UserRealmStore(realm);
    }
    //endregion

    //region provide DeleteTokenUseCase
    @Provides @PerActivity @Named("delete_token")
    UseCase provideDeleteTokenUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread, TokenRepository repository){
        return new DeleteTokenUseCase(executor,postExecutionThread,repository);
    }
    //endregion

    @Provides @PerActivity EntityMapper<LoginResponseEntity, LoginResponse> provideEntityMapper(){
        return new LoginResponseMapper();
    }

    @Provides @PerActivity EntityMapper<UserEntity, User> provideMapper(){
        return new UserDataMapper();
    }



}
