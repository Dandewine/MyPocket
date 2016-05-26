package com.denis.mypocket.internal.di.modules;

import android.content.Context;
import android.util.Log;

import com.denis.data.entity.UserEntity;
import com.denis.data.entity.mapper.WalletDataMapper;
import com.denis.data.local_store.RealmStore;
import com.denis.data.local_store.UserRealmStore;
import com.denis.data.local_store.WalletRealmStore;
import com.denis.data.repository.WalletDataRepository;
import com.denis.data.repository.datasource.cloud.WalletCloudDataStore;
import com.denis.data.repository.datasource.interfaces.UserDataStore;
import com.denis.data.repository.datasource.interfaces.WalletDataStore;
import com.denis.data.repository.datasource.local.UserLocalDataStore;
import com.denis.data.repository.datasource.local.WalletLocalDataStore;
import com.denis.data.rest.WalletService;
import com.denis.domain.RestClient;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.wallets.AddWalletUseCase;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.wallets.CloudCreateWalletUseCase;
import com.denis.domain.interactor.wallets.GetWalletsUseCase;
import com.denis.domain.interactor.wallets.SaveWalletList;
import com.denis.domain.models.Wallet;
import com.denis.domain.repository.WalletRepository;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.utils.PLTags;
import com.denis.mypocket.viewmodel.adding.WalletsViewModel;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class AddWalletModule {
    private boolean isNewUser;

    public AddWalletModule(boolean isNewUser) {
        this.isNewUser = isNewUser;
        Log.d(PLTags.INSTANCE_TAG,"Wallet Module, "+hashCode());
    }

    @Provides @PerActivity
    WalletsViewModel  provideWalletsViewModel(@Named("addWallet") UseCase<Wallet> addWalletUseCase,
                                              @Named("getWallets") UseCase<Wallet> getWalletUseCase,
                                              @Named("createWallet_server") UseCase<Wallet> addToCloudWalletUseCase,
                                              @Named("activity") Context context){
        return new WalletsViewModel(addWalletUseCase,addToCloudWalletUseCase,getWalletUseCase,context, isNewUser);
    }

    @Provides @PerActivity @Named("createWallet_server")
    UseCase<Wallet> provideCreateWalletUseCase(ThreadExecutor executor,
                                               PostExecutionThread postExecutionThread,
                                               @Named("net") WalletRepository repository){
        return new CloudCreateWalletUseCase(executor,postExecutionThread,repository);
    }

    @Provides @PerActivity
    UseCase<Wallet> provideWalletSaveList(ThreadExecutor executor,
                                                PostExecutionThread postExecutionThread,
                                                @Named("local") WalletRepository repository){
        return new GetWalletsUseCase(executor,postExecutionThread,repository);
    }




    //region Wallet local
    @Provides @PerActivity @Named("getWallets")
    UseCase<Wallet> providerGetWalletsUseCase(ThreadExecutor threadExecutor,
                                              PostExecutionThread postExecutionThread,
                                              @Named("local")  WalletRepository repository){
        return new GetWalletsUseCase(threadExecutor,postExecutionThread,repository);
    }

    @Provides @PerActivity @Named("local")
    WalletRepository provideUserRepository(WalletDataMapper mapper, WalletDataStore walletDataStore) {
        return new WalletDataRepository(mapper,walletDataStore);
    }

    @Provides @PerActivity
    WalletDataStore walletDataStore(@Named("walletStore") RealmStore store){
        return new WalletLocalDataStore(store);
    }

    @Provides @PerActivity
    WalletDataMapper getWalletEntityDataMapper(){
        return new WalletDataMapper();
    }

    @Provides @PerActivity @Named("walletStore")
    RealmStore getRealmStore(Realm realm){
        return new WalletRealmStore(realm);
    }

    //endregion

    //region add wallet cloud

    @Provides @PerActivity @Named("net")
    WalletRepository provideUserRepositoryCloud(WalletDataMapper mapper, @Named("walletStore_net") WalletDataStore walletDataStore) {
        return new WalletDataRepository(mapper,walletDataStore);
    }

    @Provides @PerActivity @Named("walletStore_net")
    WalletDataStore provideWalletDataStore(RestClient client, UserDataStore dataStore, WalletDataMapper mapper){
        return new WalletCloudDataStore(client.create(WalletService.class),dataStore, mapper);
    }

    @Provides @PerActivity UserDataStore provideUserDataStore(RealmStore<UserEntity> realmStore){
        return new UserLocalDataStore(realmStore);
    }

    @Provides @PerActivity RealmStore<UserEntity> provideUserEntityRealmStore(Realm realm){
        return new UserRealmStore(realm);
    }

    @Provides @PerActivity @Named("addWallet")
    UseCase<Wallet> getAddWalletUseCase(ThreadExecutor threadExecutor,
                                        PostExecutionThread postExecutionThread,
                                        @Named("local")  WalletRepository repository){
        return new AddWalletUseCase(threadExecutor,postExecutionThread,repository);
    }

    //endregion

}
