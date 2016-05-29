package com.denis.mypocket.internal.di.modules.drawer;

import com.denis.data.entity.LoginResponseEntity;
import com.denis.data.entity.UserEntity;
import com.denis.data.entity.mapper.EntityMapper;
import com.denis.data.entity.mapper.LoginResponseMapper;
import com.denis.data.entity.mapper.UserDataMapper;
import com.denis.data.local_store.RealmStore;
import com.denis.data.local_store.UserRealmStore;
import com.denis.data.repository.UserDataStoreRepository;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.data.repository.datasource.local.UserLocalDataStore;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.auth.DeleteTokenUseCase;
import com.denis.domain.interactor.user.DeleteUserUseCase;
import com.denis.domain.interactor.user.GetAllUsers;
import com.denis.domain.models.LoginResponse;
import com.denis.domain.models.User;
import com.denis.domain.repository.TokenRepository;
import com.denis.domain.repository.UserRepository;
import com.denis.mypocket.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * @author denis at 5/29/16.
 */
@Module
public class DeleteUserUseCaseModule {

    //region UseCases
    @Provides @PerActivity @Named("delete_user") //need to delete user on logout
    UseCase provideDeleteUserUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread,
                                     @Named("local") UserRepository repository){
        return new DeleteUserUseCase(executor,postExecutionThread, repository);
    }

    @Provides @PerActivity @Named("delete_token") //need to delete token on logout
    UseCase provideDeleteTokenUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread, TokenRepository repository){
        return new DeleteTokenUseCase(executor,postExecutionThread,repository);
    }

    @Provides @PerActivity @Named("user_get_local") //need to display info about user in drawer header
    UseCase<User> provideGetUserUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread,
                                        @Named("local") UserRepository repository){
        return new GetAllUsers(executor,postExecutionThread,repository);
    }
    //endregion

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

    //region Mappers
    @Provides @PerActivity EntityMapper<LoginResponseEntity, LoginResponse> provideEntityMapper(){
        return new LoginResponseMapper();
    }

    @Provides @PerActivity EntityMapper<UserEntity, User> provideMapper(){
        return new UserDataMapper();
    }
    //endregion



}
