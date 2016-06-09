package com.denis.mypocket.internal.di.modules.transactions;

import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.Transaction;
import com.denis.mypocket.internal.di.PerFragment;
import com.denis.mypocket.model.WalletModel;
import com.denis.mypocket.screens.transactions_tab_screen.viewmodel.TransactionViewModel;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * @author Denis_Zinkovskiy at 6/6/16.
 */

@Module(includes = TransactionsGetModule.class)
public class TransactionTabModule {

    private ArrayList<WalletModel> walletModels;

    public TransactionTabModule(ArrayList<WalletModel> walletModels) {
        this.walletModels = walletModels;
    }

    @Provides @PerFragment
    TransactionViewModel provideViewModel(UseCase<Transaction> transactionUseCase){
        return new TransactionViewModel(transactionUseCase,walletModels);
    }

}
