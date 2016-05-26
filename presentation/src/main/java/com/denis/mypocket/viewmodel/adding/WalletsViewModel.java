package com.denis.mypocket.viewmodel.adding;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.Wallet;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.model.WalletModel;
import com.denis.mypocket.model.mapper.ModelMapper;
import com.denis.mypocket.model.mapper.WalletModelDataMapper;
import com.denis.mypocket.utils.PLTags;
import com.denis.mypocket.view.activity.DrawerActivity;
import com.denis.mypocket.view.add_wallet.adapter.WalletAdapter;
import com.denis.mypocket.viewmodel.ViewModel;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Named;

@PerActivity
public class WalletsViewModel implements ViewModel {
    private UseCase<Wallet> addWalletLocalUseCase;
    private UseCase<Wallet> addWalletCloudUseCase;
    private UseCase<Wallet> getWalletUseCase;

    private ModelMapper<Wallet, WalletModel> modelMapper = new WalletModelDataMapper();
    private WalletAdapter walletAdapter;


    private Context context;
    private String walletName = "";
    private float balance = 0f;


    @Inject
    WalletsViewModel(@Named("addWallet") UseCase<Wallet> addWalletLocalUseCase,
                     @Named("createWallet_server") UseCase<Wallet> addWalletCloudUseCase,
                     UseCase<Wallet> allWalletUseCase, @Named("activity") Context context) {
        this.addWalletLocalUseCase = addWalletLocalUseCase;
        this.addWalletCloudUseCase = addWalletCloudUseCase;
        this.getWalletUseCase = allWalletUseCase;
        this.context = context;

        walletAdapter = new WalletAdapter();

        this.getWalletUseCase.executeSync(new GetAllWalletsFromLocalDB());
        Log.d(PLTags.INSTANCE_TAG, "AddWallet ViewModel, " + hashCode());
    }

    public View.OnClickListener addWallet = v -> execute();

    public void execute() {
        addWalletCloudUseCase.executeAsync(new AddWalletToServerSubscriber(), new Wallet(null, walletName, "usd", balance));
    }

    @Override
    public void destroy() {
        addWalletLocalUseCase.unSubscribe();
    }


    private class AddWalletToServerSubscriber extends DefaultSubscriber<Wallet> {
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
                addWalletLocalUseCase.executeSync(new AddToLocalStorageSubscriber(), wallet);
            // TODO: 5/19/16 create case if wallet was null
        }
    }


    private class AddToLocalStorageSubscriber extends DefaultSubscriber<Wallet> {
        @Override
        public void onNext(Wallet wallet) {
            startDrawerActivity();
        }
    }


    private class GetAllWalletsFromLocalDB extends DefaultSubscriber<List<Wallet>> {
        @Override
        public void onNext(List<Wallet> wallets) {
            if (wallets != null && !wallets.isEmpty()) {
                List<WalletModel> items = modelMapper.transform(wallets);
                walletAdapter.addAll(items);
            }
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

    public WalletAdapter getWalletAdapter() {
        return walletAdapter;
    }
}
