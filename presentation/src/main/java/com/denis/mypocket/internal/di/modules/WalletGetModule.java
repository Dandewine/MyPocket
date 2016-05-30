package com.denis.mypocket.internal.di.modules;

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
import com.denis.mypocket.internal.di.PerFragment;
import com.denis.mypocket.model.mapper.WalletModelDataMapper;
import com.denis.mypocket.viewmodel.getting.GetAllWalletViewModel;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class WalletGetModule {

    @Provides @PerFragment
    WalletDataMapper getWalletEntityDataMapper(){
        return new WalletDataMapper();
    }

    @Provides @PerFragment
    RealmStore getRealmStore(Realm realm){
        return new WalletRealmStore(realm);
    }

    @Provides @PerFragment
    WalletDataStore walletDataStore(RealmStore store){
        return new WalletLocalDataStore(store);
    }

    @Provides @PerFragment
    WalletRepository provideUserRepository(WalletDataMapper mapper, WalletDataStore walletDataStore) {
        return new WalletDataRepository(mapper,walletDataStore);
    }

    @Provides @PerFragment
    UseCase<Wallet> getAddWalletUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, WalletRepository repository) {
        return new GetWalletsUseCase(threadExecutor, postExecutionThread, repository);
    }

    @Provides @PerFragment
    WalletModelDataMapper provideMapper(){
        return new WalletModelDataMapper();
    }

    @Provides @PerFragment
    GetAllWalletViewModel provideViewModel(UseCase<Wallet> useCase, WalletModelDataMapper mapper){
        return new GetAllWalletViewModel(useCase,mapper);
    }
}
