package com.denis.mypocket.internal.di.modules.transactions;

import android.content.Context;

import com.denis.data.entity.ExpenseCategoryEntity;
import com.denis.data.entity.IncomeCategoryEntity;
import com.denis.data.entity.mapper.ExpenseCategoryDataMapper;
import com.denis.data.entity.mapper.IncomeCategoryDataMapper;
import com.denis.data.local_store.RealmStore;
import com.denis.data.local_store.categories.ExpenseCategoriesStore;
import com.denis.data.local_store.categories.IncomeCategoriesStore;
import com.denis.data.repository.ExpenseCategoryDataRepository;
import com.denis.data.repository.IncomeCategoriesDataRepository;
import com.denis.data.repository.datasource.interfaces.ExpenseCategoryDataStore;
import com.denis.data.repository.datasource.interfaces.IncomeCategoryDataStore;
import com.denis.data.repository.datasource.local.ExpenseCategoryLocalDataStore;
import com.denis.data.repository.datasource.local.IncomeCateforyLocalDataStore;
import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.facades.AddTransactionUseCasesFacade;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.categories.GetExpenseCategoriesUseCase;
import com.denis.domain.interactor.categories.GetIncomeCategoriesUseCase;
import com.denis.domain.models.ExpenseCategory;
import com.denis.domain.models.IncomeCategory;
import com.denis.domain.models.Transaction;
import com.denis.domain.models.Wallet;
import com.denis.domain.repository.IncomeCategoriesRepository;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.modules.ActivityModule;
import com.denis.mypocket.internal.di.modules.wallets.WalletFromLocalModule;
import com.denis.mypocket.screens.add_transaction_screen.viewmodel.TransactionViewModel;
import io.realm.Realm;
import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author denis at 5/28/16.
 */

@Module(includes = {AddCloudTransaction.class, ActivityModule.class, WalletFromLocalModule.class})
public class TransactionModule {

    private boolean isIncome;

    public TransactionModule(boolean isIncome) {
        this.isIncome = isIncome;
    }

    @Provides @PerActivity
    TransactionViewModel provideTransactionViewModel(AddTransactionUseCasesFacade facade,
                                                     @Named("activity") Context context) {
        return new TransactionViewModel(facade, context, isIncome);
    }


    @Provides @PerActivity
    AddTransactionUseCasesFacade provideAddTransactionUseCasesFacade(@Named("addTransaction") UseCase<Transaction> addTransactionUseCase,
                                                                     @Named("incomeUC") UseCase<IncomeCategory> incomeCategoriesUseCase,
                                                                     @Named("expenseUC") UseCase<ExpenseCategory> expenseCategoryUseCase,
                                                                     UseCase<Wallet> walletUseCase){

        return new AddTransactionUseCasesFacade(addTransactionUseCase, walletUseCase,incomeCategoriesUseCase, expenseCategoryUseCase);
    }

    //region Income

    @Provides @PerActivity @Named("incomeUC")
    UseCase<IncomeCategory> provideIncomeCategoryUseCase(ThreadExecutor executor, PostExecutionThread thread,
                                                         IncomeCategoriesRepository repository){
        return new GetIncomeCategoriesUseCase(executor,thread,repository);
    }

    @Provides @PerActivity IncomeCategoriesRepository provideIncomeCategoriesRepository(IncomeCategoryDataStore dataStore, IncomeCategoryDataMapper dataMapper){
        return new IncomeCategoriesDataRepository(dataMapper,dataStore);
    }

    @Provides @PerActivity IncomeCategoryDataStore provideIncomeCategoryDataStore(RealmStore<IncomeCategoryEntity>  realmStore){
        return new IncomeCateforyLocalDataStore(realmStore);
    }

    @Provides @PerActivity
    RealmStore<IncomeCategoryEntity> provideIncomeCategoryEntityRealmStore(Realm realm){
        return new IncomeCategoriesStore(realm);
    }

    //endregion

    //region Expense

    @Provides @PerActivity @Named("expenseUC")
    UseCase<ExpenseCategory> provideExpenseCategoryUseCase(ThreadExecutor executor, PostExecutionThread thread,
                                                          ExpenseCategoryDataRepository repository){
        return new GetExpenseCategoriesUseCase(executor,thread,repository);
    }

    @Provides @PerActivity ExpenseCategoryDataRepository provideExpenseCategoryDataRepository(ExpenseCategoryDataStore dataStore, ExpenseCategoryDataMapper dataMapper){
        return new ExpenseCategoryDataRepository(dataMapper,dataStore);
    }

    @Provides @PerActivity
    ExpenseCategoryDataStore provideExpenseCategoryDataStore(RealmStore<ExpenseCategoryEntity>  realmStore){
        return new ExpenseCategoryLocalDataStore(realmStore);
    }

    @Provides @PerActivity
    RealmStore<ExpenseCategoryEntity> provideExpenseCategoryEntityRealmStore(Realm realm){
        return new ExpenseCategoriesStore(realm);
    }

    //endregion
}
