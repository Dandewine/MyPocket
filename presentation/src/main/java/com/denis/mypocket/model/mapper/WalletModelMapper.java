package com.denis.mypocket.model.mapper;

import android.support.annotation.Nullable;

import com.denis.domain.models.Wallet;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.model.WalletModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class WalletModelMapper {

    @Inject
    public WalletModelMapper() {
    }

    public WalletModel transform(Wallet wallet) {
        if (wallet == null)
            throw new NullPointerException("Cannot transform a null value");
        WalletModel walletModel = new WalletModel(wallet.getId());
        walletModel.setName(wallet.getName());
        walletModel.setCurrency(wallet.getCurrency());
        walletModel.setBalance(wallet.getBalance());
        return walletModel;
    }

    @Nullable
    public List<WalletModel> transform(List<Wallet> wallets) {
        List<WalletModel> walletModels = null;
        if (wallets != null && !wallets.isEmpty()) {
            walletModels = new ArrayList<>();
            for (Wallet w : wallets) walletModels.add(transform(w));
        }
        return walletModels;
    }
}
