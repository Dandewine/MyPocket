package com.denis.mypocket.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.denis.domain.interactor.AddWalletUseCase;
import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.models.Wallet;
import com.denis.mypocket.PresentationConstants;
import com.denis.mypocket.StringUtils;

public class AddWalletViewModel implements ViewModel {
    public AddWalletUseCase addWalletUseCase;
    public ObservableField<String> walletName = new ObservableField<>();


    public AddWalletViewModel(AddWalletUseCase addWalletUseCase) {
        this.addWalletUseCase = addWalletUseCase;
    }

    @Override
    public void destroy() {
        addWalletUseCase.unsubscribe();
    }

    private static class AddWalletSubscriber extends DefaultSubscriber<Wallet>{
        @Override
        public void onCompleted() {
            Log.d(PresentationConstants.WALLET_TAG,"Completed");
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(Wallet wallet) {
            Log.d(PresentationConstants.WALLET_TAG, StringUtils.concat(wallet.getName()," was created"));
        }
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        walletName.set(s.toString());
    }

    public View.OnClickListener addWalletOnClick = v ->
            addWalletUseCase.executeSync(new AddWalletSubscriber(),new Wallet(0,walletName.get(),"",3.f));


}
