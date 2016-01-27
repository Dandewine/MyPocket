package com.denis.mypocket.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.Transaction;
import com.denis.domain.models.Wallet;
import com.denis.mypocket.R;
import com.denis.mypocket.internal.di.PerFragment;
import com.denis.mypocket.utils.PLTags;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@PerFragment
public class AddTransactionViewModel implements ViewModel {
    public ObservableField<String> amount = new ObservableField<>();

    public UseCase<Transaction> addTransactionUseCase;
    private UseCase<Wallet> getWalletsUseCase;

    private ArrayAdapter categoriesAdapter;
    private ArrayAdapter walletsAdapter;


    @Inject
    public AddTransactionViewModel(@Named("addTransaction") UseCase<Transaction> addTransactionUseCase,
                                   @Named("getWallets") UseCase<Wallet> getWalletsUseCase,
                                   @Named("activity") Context context) {
        this.addTransactionUseCase = addTransactionUseCase;
        this.getWalletsUseCase = getWalletsUseCase;

        categoriesAdapter = ArrayAdapter.createFromResource(context,R.array.mock_array2,android.R.layout.simple_list_item_1);
        walletsAdapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1);

        Log.d(PLTags.INSTANCE_TAG,"Add Transaction ViewModel, "+hashCode());

        getWalletsUseCase.executeSync(new GetAllWalletsSubscriber());

    }



    @Override
    public void destroy() {
        addTransactionUseCase.unsubscribe();
    }

    private class GetAllWalletsSubscriber extends DefaultSubscriber<List<Wallet>>{
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<Wallet> wallet) {
            for (int i = 0; i < wallet.size(); i++) {
                walletsAdapter.add(wallet.get(i).getName());
            }
            walletsAdapter.notifyDataSetChanged();
        }
    }

    private static class AddTransactionSubscriber extends DefaultSubscriber<Transaction>{
        @Override
        public void onCompleted() {
            Log.d(PLTags.TRANSACTIONS_TAG,"Transaction was added");
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(Transaction transaction) {
            Log.d(PLTags.TRANSACTIONS_TAG,"onNext");
        }
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        amount.set(s.toString());
    }

    public View.OnClickListener addOnClick =
            v -> addTransactionUseCase
                    .executeSync(new AddTransactionSubscriber(),new Transaction(0,0,345f,1,234L));

    public ArrayAdapter getCategoriesAdapter() {
        return categoriesAdapter;
    }

    public ArrayAdapter getWalletsAdapter() {
        return walletsAdapter;
    }
}
