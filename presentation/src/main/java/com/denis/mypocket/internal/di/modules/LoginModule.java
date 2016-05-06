package com.denis.mypocket.internal.di.modules;

import android.content.Context;

import com.denis.data.entity.LoginResponseEntity;
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
import com.denis.domain.interactor.auth.LoginUseCase;
import com.denis.domain.interactor.auth.TokenSave;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.LoginResponse;
import com.denis.domain.models.User;
import com.denis.domain.models.Wallet;
import com.denis.domain.repository.TokenRepository;
import com.denis.domain.repository.UserRepository;
import com.denis.domain.interactor.user.AddUserUseCase;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.viewmodel.auth.LoginViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by denis on 4/23/16.
 */
@Module(includes = {ActivityModule.class,WalletModuleGET.class})
public class LoginModule {

    @Provides @PerActivity
    LoginViewModel provideViewModel(@Named("getUser") UseCase<String> loginUseCase,
                                    @Named("token-save") UseCase<String> tokenSaveUseCase,
                                    @Named("activity") Context context,
                                    @Named("getWallets") UseCase<Wallet> getWalletUseCase,
                                    @Named("add user") UseCase<User> userSaveUseCase) {
        return new LoginViewModel(loginUseCase, tokenSaveUseCase, userSaveUseCase, getWalletUseCase, context);
    }


    @Provides @PerActivity @Named("getUser")
    UseCase<String> providUseCase(ThreadExecutor executor, PostExecutionThread thread, UserRepository repository) {
        return new LoginUseCase(executor, thread, repository);
    }

    @Provides @PerActivity @Named("token-save")
    UseCase<String> provideUTokenSaveUseCase(ThreadExecutor executor, PostExecutionThread thread, TokenRepository repository) {
        return new TokenSave(executor, thread, repository);
    }

    @Provides @PerActivity
    UserDataMapper provideMapper() {
        return new UserDataMapper();
    }

    @Provides @PerActivity
    EntityMapper<LoginResponseEntity, LoginResponse> provideLoginMapper(){
        return new LoginResponseMapper();
    }


    //region provide LocalUserDataStore
    @Provides @PerActivity @Named("add user")
    UseCase<User> provideUserSave(ThreadExecutor threadExecutor,
                                  PostExecutionThread postExecutionThread, @Named("local_Repo") UserRepository repository) {
        return new AddUserUseCase(threadExecutor, postExecutionThread, repository);
    }

    @Provides @PerActivity @Named("local_Repo")
    UserRepository provideLocalUserRepo(UserDataMapper dataMapper, @Named("local_Store") UserDataStore dataStore,
                                        EntityMapper<LoginResponseEntity, LoginResponse> loginMapper) {
        return new UserDataStoreRepository(dataMapper, dataStore, loginMapper);
    }

    @Provides @PerActivity @Named("local_Store")
    UserDataStore provideLocalUserDataStore(RealmStore realmStore) {
        return new UserLocalDataStore(realmStore);
    }

    @Provides @PerActivity
    RealmStore provideRealmStore(Realm realm) {
        return new UserRealmStore(realm);
    }

    //endregion

    //region provide CloudUserDataStore
    @Provides @PerActivity
    UserRepository provideUserCloudRepository(UserDataMapper dataMapper, @Named("cloud") UserDataStore dataStore,
                                              EntityMapper<LoginResponseEntity, LoginResponse> loginMapper) {
        return new UserDataStoreRepository(dataMapper, dataStore, loginMapper);
    }

    @Provides @PerActivity @Named("cloud")
    UserDataStore provideUserCloudDataStore(AuthService service, UserDataMapper mapper) {
        return new UserCloudDataStore(service, mapper);
    }

    @Provides
    @PerActivity AuthService provideAuthService(RestClient restClient) {
        return restClient.create(AuthService.class);
    }
    //endregion


}
