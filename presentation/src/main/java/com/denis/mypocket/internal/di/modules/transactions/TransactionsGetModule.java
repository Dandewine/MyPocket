package com.denis.mypocket.internal.di.modules.transactions;

import com.denis.data.entity.TransactionEntity;
import com.denis.data.entity.WalletEntity;
import com.denis.data.entity.mapper.TransactionDataMapper;
import com.denis.data.local_store.RealmStore;
import com.denis.data.local_store.TransactionRealmStore;
import com.denis.data.local_store.WalletRealmStore;
import com.denis.data.repository.TransactionDataRepository;
import com.denis.data.repository.datasource.cloud.TransactionCloudDataStore;
import com.denis.data.repository.datasource.interfaces.TransactionDataStore;
import com.denis.data.repository.datasource.interfaces.WalletDataStore;
import com.denis.data.repository.datasource.local.TransactionLocalDataStore;
import com.denis.data.repository.datasource.local.WalletLocalDataStore;
import com.denis.data.rest.TransactionService;
import com.denis.domain.RestClient;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.transactions.GetTransactions;
import com.denis.domain.models.Transaction;
import com.denis.domain.repository.TransactionRepository;
import com.denis.mypocket.internal.di.PerFragment;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * @author Denis_Zinkovskiy at 6/6/16.
 */

@Module
public class TransactionsGetModule {

    @Provides @PerFragment
    UseCase<Transaction> provideTransactionUseCase(ThreadExecutor executor, PostExecutionThread thread, TransactionRepository repository){
        return new GetTransactions(executor,thread,repository);
    }

    @Provides @PerFragment TransactionRepository providTransactionRepository(@Named("cloud") TransactionDataStore dataStore, TransactionDataMapper dataMapper){
        return new TransactionDataRepository(dataStore, dataMapper);
    }

    @Provides @PerFragment @Named("cloud") TransactionDataStore provideDataStore(RestClient restClient, WalletDataStore dataStore){
        return new TransactionCloudDataStore(restClient.create(TransactionService.class),dataStore);
    }

    @Provides @PerFragment @Named("local") TransactionDataStore providesTransactionDataStore(RealmStore<TransactionEntity> realmStore){
        return new TransactionLocalDataStore(realmStore);
    }

    @Provides @PerFragment RealmStore<TransactionEntity> provideTransactionEntityRealmStore(Realm realm){
        return new TransactionRealmStore(realm);
    }

    //region Wallets

    @Provides @PerFragment WalletDataStore provideWalletDataStore(RealmStore<WalletEntity> realmStore){
        return new WalletLocalDataStore(realmStore);
    }

    @Provides @PerFragment RealmStore<WalletEntity> provideRealmStore(Realm realm){
        return new WalletRealmStore(realm);
    }

    //endregion


}
