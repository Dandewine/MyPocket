package com.denis.mypocket.model.mapper;

import android.support.annotation.Nullable;

import com.denis.domain.models.Wallet;
import com.denis.mypocket.internal.di.PerActivity;
import com.denis.mypocket.model.WalletModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class WalletModelDataMapper implements ModelMapper<Wallet, WalletModel> {

    @Inject
    public WalletModelDataMapper() {
    }

    public WalletModel toModel(Wallet wallet) {
        if (wallet == null)
            throw new NullPointerException("Cannot toEntity a null value");
        WalletModel walletModel = new WalletModel(wallet.getId());
        walletModel.setName(wallet.getWalletName());
        walletModel.setCurrency(wallet.getCurrency());
        walletModel.setBalance(wallet.getBalance());
        walletModel.setActive(wallet.isActive());
        return walletModel;
    }

    @Nullable @Override
    public List<WalletModel> toModel(List<Wallet> wallets) {
        List<WalletModel> walletModels = null;
        if (wallets != null && !wallets.isEmpty()) {
            walletModels = new ArrayList<>();
            for (Wallet w : wallets) walletModels.add(toModel(w));
        }
        return walletModels;
    }


    @Override
    public List<Wallet> fromModel(List<WalletModel> walletModels) {
        List<Wallet> walletList = null;
        if (walletModels != null && walletModels.isEmpty()) {
            walletList = new ArrayList<>();
            for (WalletModel walletModel : walletModels) {
                Wallet wallet = fromModel(walletModel);
                walletList.add(wallet);
            }
        }
        return walletList;
    }

    @Override
    public Wallet fromModel(WalletModel model) {
        Wallet wallet = null;
        if (model != null) {
            wallet = new Wallet(model.getId());
            wallet.setActive(model.isActive());
            wallet.setBalance(model.getBalance());
            wallet.setCurrency(model.getCurrency());
            wallet.setWalletName(model.getName());
        }
        return wallet;
    }
}
