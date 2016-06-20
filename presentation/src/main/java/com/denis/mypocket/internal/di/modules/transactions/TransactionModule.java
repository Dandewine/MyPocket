package com.denis.mypocket.internal.di.modules.transactions;

import android.content.Context;

import com.denis.domain.interactor.UseCase;
import com.denis.domain.interactor.facades.AddTransactionUseCasesFacade;
import com.denis.domain.models.categories.ExpenseCategory;
import com.denis.domain.models.categories.IncomeCategory;
import com.denis.domain.models.Transaction;
import com.denis.domain.models.Wallet;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.internal.di.modules.ActivityModule;
import com.denis.mypocket.internal.di.modules.categories.ExpenseCategoryModule;
import com.denis.mypocket.internal.di.modules.categories.IncomeCategoryModule;
import com.denis.mypocket.internal.di.modules.wallets.WalletFromLocalModule;
import com.denis.mypocket.screens.add_transaction_screen.viewmodel.AddTransactionViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author denis at 5/28/16.
 */

@Module(includes = {AddCloudTransaction.class, ActivityModule.class, WalletFromLocalModule.class,ExpenseCategoryModule.class, IncomeCategoryModule.class})
public class TransactionModule {

    private boolean isIncome;

    public TransactionModule(boolean isIncome) {
        this.isIncome = isIncome;
    }

    @Provides @PerActivity
    AddTransactionViewModel provideTransactionViewModel(AddTransactionUseCasesFacade facade,
                                                        @Named("activity") Context context) {
        return new AddTransactionViewModel(facade, context, isIncome);
    }


    @Provides @PerActivity
    AddTransactionUseCasesFacade provideAddTransactionUseCasesFacade(@Named("addTransaction") UseCase<Transaction> addTransactionUseCase,
                                                                     @Named("incomeUC") UseCase<IncomeCategory> incomeCategoriesUseCase,
                                                                     @Named("expenseUC") UseCase<ExpenseCategory> expenseCategoryUseCase,
                                                                     @Named("getWallets_local") UseCase<Wallet> walletUseCase,
                                                                     @Named("updWallet") UseCase<Wallet> updateWalletUseCase){

        return new AddTransactionUseCasesFacade(addTransactionUseCase, walletUseCase,incomeCategoriesUseCase,
                expenseCategoryUseCase, updateWalletUseCase);
    }
}
