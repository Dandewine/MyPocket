package com.denis.mypocket.viewmodel.adding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.View;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.Wallet;
import com.denis.mypocket.StringUtils;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.utils.PLTags;
import com.denis.mypocket.view.activity.DrawerActivity;
import com.denis.mypocket.viewmodel.ViewModel;

import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class AddWalletViewModel implements ViewModel {
    private UseCase<Wallet> addWalletUseCase;
    private UseCase<Wallet> addWalletCloudUseCase;
    private Context context;
    public String walletName = "";

    @Inject
    public AddWalletViewModel(@Named("addWallet") UseCase<Wallet> addWalletUseCase,
                              @Named("createWallet_server") UseCase<Wallet> addWalletCloudUseCase,
                              @Named("activity") Context context) {
        this.addWalletUseCase = addWalletUseCase;
        this.addWalletCloudUseCase = addWalletCloudUseCase;
        this.context = context;
        Log.d(PLTags.INSTANCE_TAG, "AddWallet ViewModel, " + hashCode());
    }

    public View.OnClickListener addWallet = v -> execute();


    private void execute() {
        addWalletCloudUseCase.executeAsync(new AddWalletSubscriber(), generateNewWallet());
    }

    private Wallet generateNewWallet() {
        return new Wallet("", "WalletTest4", "rub", 2056);
    }

    @Override
    public void destroy() {
        addWalletUseCase.unSubscribe();
    }

    private class AddWalletSubscriber extends DefaultSubscriber<Wallet> {
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
            if (wallet != null)
                addWalletUseCase.executeSync(new AddToLocalStorageSubscriber(), wallet);
            // TODO: 5/19/16 create case if wallet was null
        }
    }

    private class AddToLocalStorageSubscriber extends DefaultSubscriber<Wallet> {
        @Override
        public void onNext(Wallet wallet) {
            startDrawerActivity();
        }
    }

    private void startDrawerActivity() {
        Intent intent = DrawerActivity.getCallingIntent(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        ((Activity) context).finish();
    }


    public void setWalletName(Editable s) {
        if (!Objects.equals(walletName, s.toString()))
            walletName = s.toString();
    }

}
