package com.denis.mypocket.internal.di.modules.transactions;

import com.denis.data.entity.ExpenseCategoryEntity;
import com.denis.data.entity.IncomeCategoryEntity;
import com.denis.data.entity.mapper.TransactionDataMapper;
import com.denis.data.local_store.RealmStore;
import com.denis.data.repository.TransactionDataRepository;
import com.denis.data.repository.datasource.cloud.TransactionCloudDataStore;
import com.denis.data.repository.datasource.interfaces.TransactionDataStore;
import com.denis.data.repository.datasource.interfaces.WalletDataStore;
import com.denis.data.rest.TransactionService;
import com.denis.domain.RestClient;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.transactions.AddTransactionCloudUseCase;
import com.denis.domain.models.Transaction;
import com.denis.domain.repository.TransactionRepository;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.modules.wallets.WalletFromLocalModule;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author denis at 5/28/16.
 */

@Module(includes = WalletFromLocalModule.class)
public class AddCloudTransaction {

    @Provides @PerActivity @Named("addTransaction")
    UseCase<Transaction> provideTransactionCloudUseCase(ThreadExecutor executor, PostExecutionThread thread, TransactionRepository repository){
        return new AddTransactionCloudUseCase(executor,thread,repository);
    }

    @Provides @PerActivity TransactionRepository provideRepository(TransactionDataStore dataStore, TransactionDataMapper dataMapper){
        return new TransactionDataRepository(dataStore,dataMapper);
    }

    @Provides @PerActivity TransactionDataStore provideDataStore(RestClient restClient, WalletDataStore dataStore){
        return new TransactionCloudDataStore(restClient.create(TransactionService.class), dataStore);
    }

    @Provides @PerActivity
    TransactionDataMapper provideTransactionDataMapper(@Named("expenseRS") RealmStore<ExpenseCategoryEntity> expenseStore,
                                                       @Named("incomeRS") RealmStore<IncomeCategoryEntity> incomeStore){
        return new TransactionDataMapper(incomeStore, expenseStore);
    }

   /* @Provides @PerActivity @Named("expenseRS")
    RealmStore<ExpenseCategoryEntity> provideExpenseCategoryEntityRealmStoreStore(Realm realm){
        return new ExpenseCategoriesStore(realm);
    }

    @Provides @PerActivity @Named("incomeRS")
    RealmStore<IncomeCategoryEntity>  provideIncomeCategoryEntityRealmStore(Realm realm){
        return new IncomeCategoriesStore(realm);
    }*/
}
