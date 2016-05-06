package com.denis.mypocket.viewmodel.adding;

import android.text.Editable;
import android.util.Log;
import android.view.View;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.Wallet;
import com.denis.mypocket.StringUtils;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.utils.PLTags;
import com.denis.mypocket.viewmodel.ViewModel;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class AddWalletViewModel implements ViewModel {
    public UseCase<Wallet> addWalletUseCase;
    public String walletName = "";


    @Inject
    public AddWalletViewModel(@Named("addWallet") UseCase<Wallet> addWalletUseCase) {
        this.addWalletUseCase = addWalletUseCase;
        Log.d(PLTags.INSTANCE_TAG, "AddWallet ViewModel, " + hashCode());
    }

    @Override
    public void destroy() {
        addWalletUseCase.unSubscribe();
    }

    private static class AddWalletSubscriber extends DefaultSubscriber<Wallet> {
        @Override
        public void onCompleted() {
            Log.d(PLTags.WALLET_TAG, "Completed");
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
        }

        @Override
        public void onNext(Wallet wallet) {
            Log.d(PLTags.WALLET_TAG, StringUtils.concat(wallet.getName(), " was created"));
        }
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!Objects.equals(walletName, s.toString()))
            walletName = s.toString();
    }

    public void setWalletName(Editable s) {
        if (!Objects.equals(walletName, s.toString()))
            walletName = s.toString();
    }

    public View.OnClickListener addWalletOnClick = v ->
            addWalletUseCase.executeSync(new AddWalletSubscriber(), new Wallet(0, walletName, "", 3.f));


}
