package com.denis.data.repository;

import com.denis.data.entity.WalletEntity;
import com.denis.data.entity.mapper.WalletDataMapper;
import com.denis.data.repository.datasource.interfaces.WalletDataStore;
import com.denis.domain.models.Wallet;
import com.denis.domain.repository.WalletRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class WalletDataRepository implements WalletRepository {
    private WalletDataMapper walletDataMapper;
    private WalletDataStore walletDataStore;

    @Inject
    public WalletDataRepository(WalletDataMapper walletDataMapper,
                                WalletDataStore walletDataStore) {
        this.walletDataMapper = walletDataMapper;
        this.walletDataStore = walletDataStore;
    }

    @Override
    public Observable<List<Wallet>> getWalletList() {
        return walletDataStore.getListWalletEntities()
                .map(walletDataMapper::transform);
    }

    @Override
    public Observable<Wallet> getWallet(int userId) {
        return walletDataStore.getWalletEntity(userId)
                .map(walletDataMapper::transform);
    }

    @Override
    public Observable<Wallet> addWallet(Wallet wallet) {
        WalletEntity walletEntity = walletDataMapper.transform(wallet);
        return walletDataStore.put(walletEntity)
                .map(walletDataMapper::transform);
    }

    @Override
    public Observable<List<Wallet>> addWallet(List<Wallet> wallets) {
        throw new UnsupportedOperationException("You can't add a list of wallets");
    }

    @Override
    public Observable<Wallet> update(Wallet item) {
        return walletDataStore.update(walletDataMapper.transform(item))
                .map(walletDataMapper::transform);
    }
}
