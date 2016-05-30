package com.denis.mypocket.internal.di.modules.wallets;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.wallets.SaveWalletList;
import com.denis.domain.models.Wallet;
import com.denis.domain.repository.WalletRepository;
import com.denis.mypocket.internal.di.PerActivity;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author Denis_Zinkovskiy at 5/30/16.
 */

@Module(includes = WalletFromLocalModule.class)
public class WalletSaveModule {
    @Provides @PerActivity @Named("save_wallets")
    UseCase<List<Wallet>> provideWalletSaveList(ThreadExecutor executor,
                                                PostExecutionThread postExecutionThread,
                                                @Named("local") WalletRepository repository){
        return new SaveWalletList(executor,postExecutionThread,repository);
    }

  /*  @Provides @PerActivity @Named("local")
    WalletRepository provideUserRepository(WalletDataMapper mapper, WalletDataStore walletDataStore) {
        return new WalletDataRepository(mapper,walletDataStore);
    }*/

  /*  @Provides @PerActivity
    WalletDataStore walletDataStore(@Named("walletStore") RealmStore store){
        return new WalletLocalDataStore(store);
    }

    @Provides @PerActivity @Named("walletStore")
    RealmStore getRealmStore(Realm realm){
        return new WalletRealmStore(realm);
    }*/
}
