package com.denis.mypocket.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.denis.domain.interactor.AddTransactionUseCase;
import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.models.Transaction;
import com.denis.mypocket.PresentationConstants;
import com.denis.mypocket.internal.di.PerFragment;

import javax.inject.Inject;

@PerFragment
public class AddTransactionViewModel implements ViewModel {
    public ObservableField<String> amount = new ObservableField<>();
    public AddTransactionUseCase addTransactionUseCase;

    @Inject
    public AddTransactionViewModel(AddTransactionUseCase addTransactionUseCase) {
        this.addTransactionUseCase = addTransactionUseCase;
    }

    @Override
    public void destroy() {
        addTransactionUseCase.unsubscribe();
    }


    private static class AddTransactionSubscriber extends DefaultSubscriber<Transaction>{
        @Override
        public void onCompleted() {
            Log.d(PresentationConstants.TRANSACTIONS_TAG,"Transaction was added");
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(Transaction transaction) {
            Log.d(PresentationConstants.TRANSACTIONS_TAG,"onNext");
        }
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        amount.set(s.toString());
    }

    public View.OnClickListener addOnClick =
            v -> addTransactionUseCase.executeSync(new AddTransactionSubscriber(),new Transaction(0,0,345f,1,234L));
}
