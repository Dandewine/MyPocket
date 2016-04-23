package com.denis.mypocket.internal.di.modules;

import android.content.Context;

import com.denis.data.entity.mapper.UserDataMapper;
import com.denis.data.repository.UserDataStoreRepository;
import com.denis.data.repository.datasource.cloud.UserCloudDataStore;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.data.rest.AuthService;
import com.denis.domain.RestClient;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.LoginUseCase;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.repository.UserRepository;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.viewmodel.auth.LoginViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by denis on 4/23/16.
 */
@Module(includes = ActivityModule.class)
public class LoginModule {

    @Provides @PerActivity LoginViewModel provideViewModel(UseCase<String> loginUseCase, @Named("activity") Context context){
        return new LoginViewModel(loginUseCase,context);
    }

    @Provides @PerActivity UseCase<String> providUseCase(ThreadExecutor executor, PostExecutionThread thread, UserRepository repository){
        return new LoginUseCase(executor, thread, repository);
    }

    @Provides @PerActivity UserRepository provideUserRepository(UserDataMapper dataMapper, UserDataStore dataStore){
        return new UserDataStoreRepository(dataMapper, dataStore);
    }

    @Provides @PerActivity UserDataMapper provideMapper(){
        return new UserDataMapper();
    }

    @Provides @PerActivity UserDataStore provideUserDataStore(AuthService service, UserDataMapper mapper){
        return new UserCloudDataStore(service,mapper);
    }

    @Provides @PerActivity AuthService provideAuthService(RestClient restClient){
        return restClient.create(AuthService.class);
    }
}
