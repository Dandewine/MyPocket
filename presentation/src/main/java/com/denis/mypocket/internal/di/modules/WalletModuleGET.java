package com.denis.mypocket.internal.di.modules;

import com.denis.data.entity.UserEntity;
import com.denis.data.entity.mapper.WalletDataMapper;
import com.denis.data.local_store.RealmStore;
import com.denis.data.local_store.UserRealmStore;
import com.denis.data.repository.WalletDataRepository;
import com.denis.data.repository.datasource.cloud.WalletCloudDataStore;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.data.repository.datasource.interfaces.WalletDataStore;
import com.denis.data.repository.datasource.local.UserLocalDataStore;
import com.denis.data.rest.WalletService;
import com.denis.domain.RestClient;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.wallets.GetWalletsUseCase;
import com.denis.domain.models.Wallet;
import com.denis.domain.repository.WalletRepository;
import com.denis.mypocket.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by denis on 5/5/16.
 */
@Module
public class WalletModuleGET {
    @Provides @PerActivity @Named("getWallets")
    UseCase<Wallet> provideWalletGetUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread, WalletRepository repository){
        return new GetWalletsUseCase(executor,postExecutionThread,repository);
    }

    @Provides @PerActivity WalletRepository provideWalletRepo(WalletDataMapper walletDataMapper, WalletDataStore dataStore){
        return new WalletDataRepository(walletDataMapper,dataStore);
    }

    @Provides @PerActivity WalletDataMapper provideWalletDataMapper(){
        return new WalletDataMapper();
    }

    @Provides @PerActivity WalletDataStore provideWalletDataStore(WalletService walletService,
                                                                  UserDataStore dataStore,
                                                                  WalletDataMapper mapper){
        return new WalletCloudDataStore(walletService,dataStore, mapper);
    }

    @Provides @PerActivity WalletService provideWalletService(RestClient restClient){
        return restClient.create(WalletService.class);
    }

    @Provides @PerActivity UserDataStore provideUserDataStore(RealmStore<UserEntity> realmStore){
        return new UserLocalDataStore(realmStore);
    }

    @Provides @PerActivity RealmStore<UserEntity> provideRealmStore(Realm realm){
        return new UserRealmStore(realm);
    }

}
