package com.denis.mypocket.internal.di.modules;

import android.content.Context;
import android.util.Log;

import com.denis.data.entity.WalletEntity;
import com.denis.data.entity.mapper.TransactionDataMapper;
import com.denis.data.entity.mapper.WalletDataMapper;
import com.denis.data.local_store.RealmStore;
import com.denis.data.local_store.TransactionRealmStore;
import com.denis.data.local_store.WalletRealmStore;
import com.denis.data.repository.TransactionDataRepository;
import com.denis.data.repository.WalletDataRepository;
import com.denis.data.repository.datasource.interfaces.TransactionDataStore;
import com.denis.data.repository.datasource.interfaces.WalletDataStore;
import com.denis.data.repository.datasource.local.TransactionLocalDataStore;
import com.denis.data.repository.datasource.local.WalletLocalDataStore;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.transactions.AddTransactionUseCase;
import com.denis.domain.interactor.wallets.GetWalletsUseCase;
import com.denis.domain.models.ExpenseCategory;
import com.denis.domain.models.IncomeCategory;
import com.denis.domain.models.Transaction;
import com.denis.domain.models.Wallet;
import com.denis.domain.repository.TransactionRepository;
import com.denis.domain.repository.WalletRepository;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.utils.PLTags;
import com.denis.mypocket.viewmodel.AddTransactionViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module(includes = {ActivityModule.class, IncomeCategoryModule.class, ExpenseCategoryModule.class})
public class AddTransactionModule {

    public AddTransactionModule() {
        Log.d(PLTags.INSTANCE_TAG,"AddTransactionModule, "+hashCode());
    }

    //region transactions
    @Provides @PerActivity
    AddTransactionViewModel provideAddTransactionViewModel(@Named("addTransaction") UseCase<Transaction> addTransactionUseCase,
                                                           @Named("getWallets") UseCase<Wallet> walletsUseCase,
                                                           @Named("incomeUC") UseCase<IncomeCategory> getCategoriesUseCase,
                                                           @Named("expenseUC") UseCase<ExpenseCategory> expenseCategoryUseCase,
                                                           @Named("activity") Context context){
        return new AddTransactionViewModel(addTransactionUseCase,walletsUseCase,getCategoriesUseCase, expenseCategoryUseCase,context);
    }

    @Provides @PerActivity @Named("addTransaction")
    UseCase<Transaction> provideAddTransactionUseCase(ThreadExecutor threadExecutor,
                                         PostExecutionThread postExecutionThread,
                                         TransactionRepository repository){
        return new AddTransactionUseCase(threadExecutor,postExecutionThread,repository);
    }

    @Provides @PerActivity @Named("transactions") RealmStore provideTransactionEntityRealmStore(Realm realm){
        return new TransactionRealmStore(realm);
    }

    @Provides @PerActivity TransactionDataStore provideTransactionDataStore(@Named("transactions") RealmStore store){
        return new TransactionLocalDataStore(store);
    }

    @Provides @PerActivity TransactionDataMapper provideTransactionDataMapper(){
        return new TransactionDataMapper();
    }

    @Provides @PerActivity TransactionRepository provideTransactionRepository(TransactionDataStore dataStore, TransactionDataMapper dataMapper){
        return new TransactionDataRepository(dataStore,dataMapper);
    }
    //endregion

    //region wallets

    @Provides @PerActivity WalletDataMapper provideWalletDataMapper(){
        return new WalletDataMapper();
    }

    @Provides @PerActivity @Named("wallets") RealmStore<WalletEntity> provideWalletRealmStore(Realm realm){
        return new WalletRealmStore(realm);
    }

    @Provides @PerActivity WalletDataStore provideWalletDataStore(@Named("wallets") RealmStore<WalletEntity> store){
        return new WalletLocalDataStore(store);
    }

    @Provides @PerActivity WalletRepository provideWalletRepository(WalletDataMapper mapper, WalletDataStore dataStore){
        return new WalletDataRepository(mapper,dataStore);
    }

    @Provides @PerActivity @Named("getWallets") UseCase<Wallet> providerGetWalletsUseCase(ThreadExecutor threadExecutor,
                                                                                          PostExecutionThread postExecutionThread,
                                                                                          WalletRepository repository){
        return new GetWalletsUseCase(threadExecutor,postExecutionThread,repository);
    }


    //endregion


}
