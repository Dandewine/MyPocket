package com.denis.data.entity.mapper;

import com.denis.data.entity.WalletEntity;
import com.denis.domain.models.Wallet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Singleton;

@Singleton
public class WalletEntityDataMapper {

    /**
     * Transform a {@link WalletEntity} into an {@link Wallet}.
     *
     * @param walletEntity Object to be transformed.
     * @return {@link Wallet} if valid {@link WalletEntity} otherwise null.
     */
    public Wallet transform(WalletEntity walletEntity) {
        Wallet wallet = null;
        if (walletEntity != null) {
            wallet = new Wallet(walletEntity.getId());
            wallet.setBalance(walletEntity.getBalance());
            wallet.setCurrency(walletEntity.getCurrency());
            wallet.setName(walletEntity.getName());
        }
        return wallet;
    }
    /**
     * Transform a List of {@link WalletEntity} into a Collection of {@link Wallet}.
     *
     * @param wallets Object Collection to be transformed.
     * @return {@link Wallet} if valid {@link WalletEntity} otherwise null.
     */
    public List<Wallet> transform(Collection<WalletEntity> wallets) {
        List<Wallet> walletList = null;
        if (wallets != null) {
            walletList = new ArrayList<>();
            Wallet wallet;
            for (WalletEntity we:wallets){
                wallet = transform(we);
                if(wallet!=null)
                    walletList.add(wallet);
            }
        }
        return walletList;
    }
}
