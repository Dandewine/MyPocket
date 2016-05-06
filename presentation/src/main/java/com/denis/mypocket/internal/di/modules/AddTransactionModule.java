package com.denis.mypocket.internal.di.modules;

import com.denis.data.entity.mapper.TransactionDataMapper;
import com.denis.data.entity.mapper.WalletDataMapper;
import com.denis.data.local_store.RealmStore;
import com.denis.data.local_store.TransactionRealmStore;
import com.denis.data.repository.TransactionDataRepository;
import com.denis.data.repository.datasource.interfaces.TransactionDataStore;
import com.denis.data.repository.datasource.local.TransactionLocalDataStore;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.AddTransactionUseCasesFacade;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.transactions.AddTransactionUseCase;
import com.denis.domain.interactor.transactions.GetTransactions;
import com.denis.domain.models.ExpenseCategory;
import com.denis.domain.models.IncomeCategory;
import com.denis.domain.models.Transaction;
import com.denis.domain.models.Wallet;
import com.denis.domain.repository.TransactionRepository;
import com.denis.domain.repository.WalletRepository;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.modules.categories.ExpenseCategoryModule;
import com.denis.mypocket.internal.di.modules.categories.IncomeCategoryModule;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module(includes = {IncomeCategoryModule.class,
        ExpenseCategoryModule.class, AddWalletModule.class, ActivityModule.class})
public class AddTransactionModule {

    public AddTransactionModule() {
    }

    @Provides @PerActivity
    AddTransactionUseCasesFacade provideUseCaseFacade(@Named("addTransaction") UseCase<Transaction> addTransactionUseCase,
                                                      @Named("getWallets") UseCase<Wallet> walletsUseCase,
                                                      @Named("incomeUC") UseCase<IncomeCategory> getCategoriesUseCase,
                                                      @Named("expenseUC") UseCase<ExpenseCategory> expenseCategoryUseCase
                                        ){
        return new AddTransactionUseCasesFacade(addTransactionUseCase,walletsUseCase,getCategoriesUseCase,expenseCategoryUseCase);
    }


    @Provides @PerActivity @Named("addTransaction")
    UseCase<Transaction> provideAddTransactionUseCase(ThreadExecutor threadExecutor,
                                                      PostExecutionThread postExecutionThread,
                                                      TransactionRepository repository,
                                                   @Named("local")  WalletRepository walletRepo){
        return new AddTransactionUseCase(threadExecutor,postExecutionThread,repository, walletRepo);
    }

    @Provides @PerActivity @Named("getTransactions")
    UseCase<Transaction> provideGetTransactionUseCase(ThreadExecutor threadExecutor,
                                                     PostExecutionThread postExecutionThread,
                                                     TransactionRepository repository){
        return new GetTransactions(threadExecutor,postExecutionThread,repository);
    }

    @Provides @PerActivity @Named("transactions") RealmStore provideTransactionEntityRealmStore(Realm realm){
        return new TransactionRealmStore(realm);
    }

    @Provides @PerActivity TransactionDataStore provideTransactionDataStore(@Named("transactions") RealmStore store){
        return new TransactionLocalDataStore(store);
    }

    @Provides @PerActivity TransactionDataMapper provideTransactionDataMapper(WalletDataMapper dataMapper){
        return new TransactionDataMapper(dataMapper);
    }

    @Provides @PerActivity TransactionRepository provideTransactionRepository(TransactionDataStore dataStore, TransactionDataMapper dataMapper){
        return new TransactionDataRepository(dataStore,dataMapper);
    }
    //endregion



}
