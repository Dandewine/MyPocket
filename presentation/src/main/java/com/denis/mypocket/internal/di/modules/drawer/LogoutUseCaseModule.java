package com.denis.mypocket.internal.di.modules.drawer;

import com.denis.data.entity.LoginResponseEntity;
import com.denis.data.entity.UserEntity;
import com.denis.data.entity.mapper.EntityMapper;
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

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author denis at 5/29/16.
 */

@Module
public class LogoutUseCaseModule {
    @Provides
    @PerActivity
    @Named("logout")
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
}
