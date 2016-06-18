package com.denis.mypocket.screens.transactions_tab_screen.viewmodel;

import android.util.Log;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.Transaction;
import com.denis.mypocket.internal.di.PerFragment;
import com.denis.mypocket.model.TransactionModel;
import com.denis.mypocket.model.WalletModel;
import com.denis.mypocket.model.mapper.TransactionModelDataMapper;
import com.denis.mypocket.screens.transactions_tab_screen.view.TransactionAdapter;
import com.denis.mypocket.screens.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Denis_Zinkovskiy at 6/6/16.
 */

@PerFragment
public class TransactionViewModel implements ViewModel {

    private UseCase<Transaction> transactionUseCase;
    private TransactionAdapter transactionAdapter;
    private TransactionModelDataMapper modelDataMapper = new TransactionModelDataMapper();


    @Inject
    public TransactionViewModel(UseCase<Transaction> transactionUseCase, ArrayList<WalletModel> walletModels) {
        this.transactionUseCase = transactionUseCase;
        transactionAdapter = new TransactionAdapter(walletModels);

        Log.d("myTag","transaction hashcode = "+hashCode());
        transactionUseCase.executeAsync(new TransactionSubscriber());
    }

    @Override
    public void destroy() {
        transactionUseCase.unSubscribe();
    }

    private class TransactionSubscriber extends DefaultSubscriber<List<Transaction>>{
        @Override
        public void onNext(List<Transaction> transactions) {
            Log.d("myTag","size = "+ (transactions != null ? transactions.size() : null));
            if(transactions != null && !transactions.isEmpty()) {
                List<TransactionModel> transactionModels = modelDataMapper.toModel(transactions);
                transactionAdapter.addAll(transactionModels);
            }
        }
    }

    public TransactionAdapter getTransactionAdapter() {
        return transactionAdapter;
    }

    public void updateDataSet(TransactionModel model){
        transactionAdapter.addToTop(model);
    }

}
