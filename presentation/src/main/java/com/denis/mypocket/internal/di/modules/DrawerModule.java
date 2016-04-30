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
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.auth.LogoutUseCase;
import com.denis.domain.models.LoginResponse;
import com.denis.domain.models.User;
import com.denis.domain.repository.UserRepository;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.viewmodel.DrawerNavViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by denis on 4/29/16.
 */
@Module
public class DrawerModule {

    @Provides @PerActivity DrawerNavViewModel provideViewModel(UseCase logoutUseCase, Context context){
        return new DrawerNavViewModel(logoutUseCase, context);
    }

    @Provides @PerActivity UseCase provideLogout(ThreadExecutor executor, PostExecutionThread postExecutionThread,
                                                 UserRepository repository){
        return new LogoutUseCase(executor, postExecutionThread, repository);
    }

    @Provides @PerActivity UserRepository provideRepository(UserDataMapper mapper, UserDataStore userDataStore,
                                                            EntityMapper<LoginResponseEntity,LoginResponse> mapper2) {
        return new UserDataStoreRepository(mapper,userDataStore,mapper2);
    }

    @Provides @PerActivity UserDataStore provideUserDataStore(AuthService authService, EntityMapper<UserEntity, User> mapper){
        return new UserCloudDataStore(authService,mapper);
    }

    @Provides @PerActivity EntityMapper<LoginResponseEntity, LoginResponse> provideEntityMapper(){
        return new LoginResponseMapper();
    }

    @Provides @PerActivity EntityMapper<UserEntity, User> provideMapper(){
        return new UserDataMapper();
    }

    @Provides @PerActivity UserDataMapper provideDataMapper(){
        return new UserDataMapper();
    }

   /* @Provides @PerActivity RealmStore<UserEntity> provideRealmStore(Realm realm){
        return new UserRealmStore(realm);
    }*/

    @Provides @PerActivity AuthService provideAuthService(RestClient restClient) {
        return restClient.create(AuthService.class);
    }

}
