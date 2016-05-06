package com.denis.data.entity.mapper;

import com.denis.data.entity.WalletEntity;
import com.denis.domain.models.Wallet;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class WalletDataMapper implements EntityMapper<WalletEntity, Wallet> {

    @Inject
    public WalletDataMapper() {
    }

    /**
     * Transform a {@link WalletEntity} into an {@link Wallet}.
     *
     * @param walletEntity Object to be transformed.
     * @return {@link Wallet} if valid {@link WalletEntity} otherwise null.
     */
    @Override
    public Wallet fromEntity(WalletEntity walletEntity) {
        Wallet wallet = null;
        if (walletEntity != null) {
            wallet = new Wallet(walletEntity.getId());
            wallet.setBalance(walletEntity.getBalance());
            wallet.setCurrency(walletEntity.getCurrency());
            wallet.setWalletName(walletEntity.getName());
        }
        return wallet;
    }

    /**
     * Transform a List of {@link WalletEntity} into a Collection of {@link Wallet}.
     *
     * @param wallets Object Collection to be transformed.
     * @return {@link Wallet} if valid {@link WalletEntity} otherwise null.
     */
    @Override
    public List<Wallet> fromEntity(List<WalletEntity> wallets) {
        List<Wallet> walletList = null;
        if (wallets != null) {
            walletList = new ArrayList<>();
            Wallet wallet;
            for (WalletEntity we : wallets) {
                wallet = fromEntity(we);
                if (wallet != null)
                    walletList.add(wallet);
            }
        }
        return walletList;
    }

    @Override
    public List<WalletEntity> toEntity(List<Wallet> wallets) {
        List<WalletEntity> walletEntities = null;
        if (wallets != null && !wallets.isEmpty()) {
            walletEntities = new ArrayList<>();
            for (Wallet w : wallets) {
                WalletEntity entity = toEntity(w);
                walletEntities.add(entity);
            }
        }
        return walletEntities;
    }

    @Override
    public WalletEntity toEntity(Wallet wallet) {
        WalletEntity entity = null;
        if (wallet != null) {
            entity = new WalletEntity(wallet.getId(), wallet.getWalletName(), wallet.getCurrency(), wallet.getBalance());
        }
        return entity;
    }
}
