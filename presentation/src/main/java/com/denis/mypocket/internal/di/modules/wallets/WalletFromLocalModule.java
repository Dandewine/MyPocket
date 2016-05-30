package com.denis.mypocket.internal.di.modules.wallets;

import com.denis.data.entity.WalletEntity;
import com.denis.data.entity.mapper.WalletDataMapper;
import com.denis.data.local_store.RealmStore;
import com.denis.data.local_store.WalletRealmStore;
import com.denis.data.repository.WalletDataRepository;
import com.denis.data.repository.datasource.interfaces.WalletDataStore;
import com.denis.data.repository.datasource.local.WalletLocalDataStore;
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
 * @author denis at 5/28/16.
 */
@Module
public class WalletFromLocalModule {
    @Provides @PerActivity @Named("getWallets_local")
    UseCase<Wallet> provideWalletGetUseCase(ThreadExecutor executor, PostExecutionThread postExecutionThread, @Named("local") WalletRepository repository){
        return new GetWalletsUseCase(executor,postExecutionThread,repository);
    }

    @Provides @PerActivity @Named("local")
    WalletRepository provideWalletRepo(WalletDataMapper walletDataMapper,  WalletDataStore dataStore){
        return new WalletDataRepository(walletDataMapper,dataStore);
    }

    @Provides @PerActivity
    WalletDataStore provideWalletDataStore(RealmStore<WalletEntity> realmStore){
        return new WalletLocalDataStore(realmStore);
    }

    @Provides @PerActivity RealmStore<WalletEntity> provideRealmStore(Realm realm){
        return new WalletRealmStore(realm);
    }
}
